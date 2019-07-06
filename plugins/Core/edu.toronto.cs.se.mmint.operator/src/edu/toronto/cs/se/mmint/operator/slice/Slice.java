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
import java.util.Collection;
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

    // Returns a map from the input model element to the set of model elements that may be directly impacted by it. 
    // By default, the contained elements are assumed to be impacted.
    // In some cases, the impacted elements also depend on what else is impacted.
    // In such cases, the map 
    protected Map<EObject, Set<EObject>> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {
    	Map<EObject, Set<EObject>> impactedMap = new HashMap<>();
    	impactedMap.put(modelObj, modelObj.eContents().stream().collect(Collectors.toSet()));
        return impactedMap;
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
        // initialise map for tracking the source of each criterion
        Map<EObject, String> critNameMap = new HashMap<>();
        for (ModelElementReference critModelElemRef : critModelEndpointRef.getModelElemRefs()) {
            try {
            	String impacterName = "Error Name";
                EObject critModelObj = critModelElemRef.getObject().getEMFInstanceObject(r);
                
                // Check if criterion contains info about previous slice steps
                if (critModelElemRef.getModelElemEndpointRefs().size() == 1) {
                    impacterName = ((MappingReference) critModelElemRef.getModelElemEndpointRefs()
                    					.get(0).eContainer()).getObject().getName();
                } else {
                	EMFInfo impacterEInfo = MIDRegistry.getModelElementEMFInfo(critModelObj, MIDLevel.INSTANCES);
					impacterName = this.input.model.getName() + "." + 
							MIDRegistry.getModelElementName(impacterEInfo, critModelObj, MIDLevel.INSTANCES);
                }
                
                critNameMap.put(critModelObj, impacterName);
                
            } catch (MMINTException e) {
            	MMINTException.print(IStatus.WARNING,
            			"Skipping criterion model element " + critModelElemRef.getObject().getName(), e);
            }
        }
        
        // Retrieve all elements dependent on the input criterion until fixed point is reached.
        // Useful for cases in which an element depends on multiple other elements.
        Map<EObject, Set<EObject>> allImpactedDependents = new HashMap<>();
        Map<EObject, Set<EObject>> nextImpactedDependents = new HashMap<>();
        
        boolean isFixedPoint = false;
        for (EObject elem: critNameMap.keySet()) {
        	allImpactedDependents.put(elem, new HashSet<>());
        }
        
        while (!isFixedPoint) {
        	// Identify all elements impacted by the current set of impacted elements.
        	// Merge with existing impacted map and check if fixed point is reached.
        	for (EObject elem: allImpactedDependents.keySet()) {
        		for (Entry<EObject, Set<EObject>> entry: getDirectlyImpactedElements(elem, allImpactedDependents.keySet()).entrySet()) {
        			if (!nextImpactedDependents.containsKey(entry.getKey())) {
        				nextImpactedDependents.put(entry.getKey(), new HashSet<>());
        			}
        			nextImpactedDependents.get(entry.getKey()).addAll(entry.getValue());
        		}
        	}
        	
        	// Merge with existing impacted map and check if fixed point is reach.
        	isFixedPoint = true;
        	for (Entry<EObject, Set<EObject>> entry: nextImpactedDependents.entrySet()) {
        		if (!allImpactedDependents.get(entry.getKey()).containsAll(entry.getValue())) {
        			isFixedPoint = false;
        			allImpactedDependents.get(entry.getKey()).addAll(entry.getValue());
        			for (EObject key: entry.getValue()) {
        				if (!allImpactedDependents.containsKey(key)) {
        					allImpactedDependents.put(key, new HashSet<>());
        				}
        			}
        		}
        	}
        	
        	// Prepare for next iteration.
        	nextImpactedDependents.clear();
        }
        
        // Iterate through all impacted elements to identify their root cause
        Map<EObject, Set<EObject>> allImpactedSource = new HashMap<>();
        Set<EObject> curImpacters = new HashSet<>();
        Set<EObject> nextImpacters = new HashSet<>();
        
        // Initialise set of impacted elements and their impact source.
        for (EObject crit: critNameMap.keySet()) {
        	for (EObject impactee: allImpactedDependents.get(crit)) {
        		allImpactedSource.put(impactee, new HashSet<>());
        		allImpactedSource.get(impactee).add(crit);
        		curImpacters.add(impactee);
        	}
        }
        
        // Iterate through the rest of the impacted elements.
        while (!curImpacters.isEmpty()) {
        	for (EObject impacter: curImpacters) {
        		for (EObject impactee: allImpactedDependents.get(impacter)) {
        			if (!allImpactedSource.containsKey(impactee)) {
        				nextImpacters.add(impactee);     
        				allImpactedSource.put(impactee, new HashSet<>());
        			}
        			
        			allImpactedSource.get(impactee).addAll(allImpactedSource.get(impacter));
        		}
        	}
        	
        	curImpacters.clear();
        	curImpacters.addAll(nextImpacters);
        	nextImpacters.clear();
        }
                
		// add impacted elements to the output model relation
		for (Entry<EObject, Set<EObject>> impactedFromCritEntry : allImpactedSource.entrySet()) {
			String impacteeLabel = "";
			for (EObject impacter : impactedFromCritEntry.getValue()) {
				if (impacteeLabel.isEmpty()) {
					impacteeLabel = critNameMap.get(impacter);
				} else if (!impacteeLabel.contains(critNameMap.get(impacter))) {
					impacteeLabel = impacteeLabel + "+" + critNameMap.get(impacter);
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