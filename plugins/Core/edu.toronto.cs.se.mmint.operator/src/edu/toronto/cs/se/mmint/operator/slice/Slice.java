/**
 * Copyright (c) 2012-2019 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Nick Fung.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 *    Nick Fung - Implementation.
 */
package edu.toronto.cs.se.mmint.operator.slice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MIDTypeHierarchy;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.java.reasoning.IJavaOperatorConstraint;
import edu.toronto.cs.se.mmint.mid.EMFInfo;
import edu.toronto.cs.se.mmint.mid.GenericElement;
import edu.toronto.cs.se.mmint.mid.MID;
import edu.toronto.cs.se.mmint.mid.MIDLevel;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.operator.impl.OperatorImpl;
import edu.toronto.cs.se.mmint.mid.relationship.MappingReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelElementReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelEndpointReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmint.mid.utils.FileUtils;
import edu.toronto.cs.se.mmint.mid.utils.MIDRegistry;

// The slice operator performs a slice on a model instance given the input
// slicing criteria, which is a unary model relation containing the model
// elements to slice.
public class Slice extends OperatorImpl {

    private Input input;
    private Output output;

    private static class Input {

        private final static @NonNull String IN_MODELREL = "criterion";
        private ModelRel critRel;
        private Model model;

        public Input(Map<String, Model> inputsByName) {

            this.critRel = (ModelRel) inputsByName.get(IN_MODELREL);
            if (this.critRel.getModelEndpoints().size() > 1) {
                // critRel must be unary
                throw new IllegalArgumentException();
            }
            this.model = this.critRel.getModelEndpoints().get(0).getTarget();
        }
    }

    private static class Output {

        private final static @NonNull String OUT_MODELREL = "slice";
        private ModelRel sliceRel;
        private MID mid;

        public Output(@NonNull Map<String, MID> outputMIDsByName) {

            this.mid = outputMIDsByName.get(OUT_MODELREL);
        }

        public @NonNull Map<String, Model> packed() {

            Map<String, Model> outputsByName = new HashMap<>();
            outputsByName.put(OUT_MODELREL, this.sliceRel);

            return outputsByName;
        }
    }

    public static class Constraint implements IJavaOperatorConstraint {

        @Override
        public boolean isAllowedInput(Map<String, Model> inputsByName) {

            try {
                new Input(inputsByName);
                return true;
            }
            catch (IllegalArgumentException e) {
                return false;
            }
        }

        @Override
        public Map<ModelRel, List<Model>> getAllowedOutputModelRelEndpoints(Map<String, Model> inputsByName, Map<String, Model> outputsByName) {

            Input input = new Input(inputsByName);
            ModelRel sliceRel = (ModelRel) outputsByName.get(Output.OUT_MODELREL);
            Map<ModelRel, List<Model>> validOutputs = new HashMap<>();
            List<Model> endpointModels = new ArrayList<>();
            endpointModels.add(input.model);
            validOutputs.put(sliceRel, endpointModels);

            return validOutputs;
        }
    }

    private void init(@NonNull Map<String, Model> inputsByName, @NonNull Map<String, MID> outputMIDsByName) {

        this.input = new Input(inputsByName);
        this.output = new Output(outputMIDsByName);
    }

    // Returns the set of model elements that may be directly impacted
    // by the input model element.
    // By default, the contained elements are assumed to be impacted.
    protected Set<EObject> getDirectlyImpactedElements(EObject modelObj) {

        return modelObj.eContents().stream().collect(Collectors.toSet());
    }

    // Returns the complete set of model elements that may be impacted
    // by the input model element.
    protected Set<EObject> getAllImpactedElements(EObject critModelObj) {

        Set<EObject> impactedAll = new HashSet<>();
        Set<EObject> impactedCur = new HashSet<>();
        impactedCur.add(critModelObj);

        // Iterate through the current set of newly added model elements
        // to identify all others that may be potentially impacted.
        while (!impactedCur.isEmpty()) {
            Set<EObject> impactedNext = new HashSet<>();
            for (EObject modelObj : impactedCur) {
                // Get all model elements directly impacted by the current
                // one without adding duplicates (to ensure termination).
                Set<EObject> impactedModelObjs = getDirectlyImpactedElements(modelObj);
                impactedModelObjs.removeAll(impactedAll);
                impactedNext.addAll(impactedModelObjs);
                impactedAll.addAll(impactedModelObjs);
            }
            
            // Prepare for next iteration.
            impactedCur = impactedNext;
        }

        return impactedAll;
    }

    protected void slice() throws MMINTException {

        this.output.sliceRel = this.input.critRel.getMetatype()
            .createInstanceAndEndpoints(null, Output.OUT_MODELREL, ECollections.asEList(this.input.model),
                                        this.output.mid);
        ModelEndpointReference sliceModelEndpointRef = this.output.sliceRel.getModelEndpointRefs().get(0);

        // retrieve resource corresponding to the model instance
        ModelEndpointReference critModelEndpointRef = this.input.critRel.getModelEndpointRefs().get(0);
        URI rUri = FileUtils.createEMFUri(critModelEndpointRef.getTargetUri(), true);
        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.getResource(rUri, true);      
        
        // loop through the model objects in the input criterion
        // initialise map for storing the impacted elements and their impact source(s)
        // initialise map for tracking whether an impact source refers to other source(s)
        Map<EObject, Set<EObject>> impactedFromCrit = new HashMap<>();        
        Map<EObject, String> prevImpacterMap = new HashMap<>();
        for (ModelElementReference critModelElemRef : critModelEndpointRef.getModelElemRefs()) {
            try {            	
                EObject critModelObj = critModelElemRef.getObject().getEMFInstanceObject(r);
                
                // Check if criterion contains info about previous slice steps
                if (critModelElemRef.getModelElemEndpointRefs().size() == 1) {
                    String prevImpacterName = ((MappingReference) critModelElemRef
                                           .getModelElemEndpointRefs().get(0).eContainer())
                                               .getObject().getName();
                    prevImpacterMap.put(critModelObj, prevImpacterName);
                }
                
                // add impacted elements to the impact map
                Set<EObject> impacted = getAllImpactedElements(critModelObj);
                for (EObject elem : impacted) {
                	if (!impactedFromCrit.containsKey(elem)) {
                		impactedFromCrit.put(elem, new HashSet<>());
                	}
                	
                	impactedFromCrit.get(elem).add(critModelObj);
                }
                
            } catch (MMINTException e) {
            	MMINTException.print(IStatus.WARNING,
            			"Skipping criterion model element " + critModelElemRef.getObject().getName(), e);
            }
        }
        
		// add impacted elements to the output model relation
		for (Entry<EObject, Set<EObject>> impactedFromCritEntry : impactedFromCrit.entrySet()) {
			String impacteeLabel = "";
			for (EObject impacter : impactedFromCritEntry.getValue()) {
				// Use previous impact source as label (if present)
				String impacterName = "";
				if (prevImpacterMap.containsKey(impacter)) {
					impacterName = prevImpacterMap.get(impacter);					
				} else {
					EMFInfo impacterEInfo = MIDRegistry.getModelElementEMFInfo(impacter, MIDLevel.INSTANCES);
					impacterName = this.input.model.getName() + "." + MIDRegistry.getModelElementName(impacterEInfo, impacter, MIDLevel.INSTANCES);
				}
				
				// Ensure there are no repeats in the label
				if (!impacteeLabel.contains(impacterName)) {
					if (impacteeLabel.contentEquals("")) {
						impacteeLabel = impacterName;
					} else {
						impacteeLabel = impacteeLabel + "+" + this.input.model.getName() + "." + impacterName;						
					}
				}
				
			}
			
			EObject impactee = impactedFromCritEntry.getKey();
			try {
				ModelElementReference impModelElemRef = sliceModelEndpointRef
						.createModelElementInstanceAndReference(impactee, null);
				MappingReference impMappingRef = MIDTypeHierarchy.getRootMappingType()
						.createInstanceAndReferenceAndEndpointsAndReferences(false,
								ECollections.asEList(impModelElemRef));
				impMappingRef.getObject().setName(impacteeLabel);

			} catch (MMINTException e) {
				MMINTException.print(IStatus.WARNING, "Skipping slice model element " + impactee, e);
			}
		}
                
    }

    @Override
    public Map<String, Model> run(Map<String, Model> inputsByName, Map<String, GenericElement> genericsByName,
                                  Map<String, MID> outputMIDsByName) throws Exception {

        init(inputsByName, outputMIDsByName);
        slice();

        return this.output.packed();
    }

}