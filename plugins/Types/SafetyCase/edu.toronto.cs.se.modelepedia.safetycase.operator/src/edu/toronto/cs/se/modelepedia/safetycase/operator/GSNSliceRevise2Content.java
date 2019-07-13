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
package edu.toronto.cs.se.modelepedia.safetycase.operator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import edu.toronto.cs.se.modelepedia.safetycase.ASILDecompositionStrategy;
import edu.toronto.cs.se.modelepedia.safetycase.AndSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.ArgumentElement;
import edu.toronto.cs.se.modelepedia.safetycase.Assumption;
import edu.toronto.cs.se.modelepedia.safetycase.Context;
import edu.toronto.cs.se.modelepedia.safetycase.ContextualElement;
import edu.toronto.cs.se.modelepedia.safetycase.CoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.InContextOf;
import edu.toronto.cs.se.modelepedia.safetycase.IndependenceGoal;
import edu.toronto.cs.se.modelepedia.safetycase.Justification;
import edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.OrSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.Solution;
import edu.toronto.cs.se.modelepedia.safetycase.Strategy;
import edu.toronto.cs.se.modelepedia.safetycase.SupportConnector;
import edu.toronto.cs.se.modelepedia.safetycase.Supportable;
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;
import edu.toronto.cs.se.modelepedia.safetycase.Supporter;
import edu.toronto.cs.se.modelepedia.safetycase.XorSupporter;
import edu.toronto.cs.se.mmint.operator.slice.Slice;

public class GSNSliceRevise2Content extends GSNSlice {

    // Get all model elements in a safety case that needs to be re-checked for 
	// content validity given the input elements that require revision.
    @Override
    protected Map<EObject, Set<EObject>> getAllImpactedElements(Set<EObject> critSet) {

    	Map<EObject, Set<EObject>> impactedMap = new HashMap<>();
    	
        // Identify all elements (including supported-by connectors) that are 
        // dependent on the revised element.    	
    	for (EObject critModelObj: critSet) {
    		for (Entry<EObject, Set<EObject>> entry: getDirectlyImpactedElements(critModelObj, impactedMap.keySet()).entrySet()) {
    			for (EObject impacted: entry.getValue()) {
        			if (!impactedMap.containsKey(impacted)) {
        				impactedMap.put(impacted, new HashSet<>());
        			}
    			}
    			
    			if (!impactedMap.containsKey(entry.getKey())) {
    				impactedMap.put(entry.getKey(), new HashSet<>());
    			}    			

    			impactedMap.get(entry.getKey()).addAll(entry.getValue());
    		}
    	}
              
        // If an ASIL decomposition strategy is impacted, then its independence 
        // goal is also impacted.
    	Map<EObject, Set<EObject>> impactedGoals = new HashMap<>();
        for (Entry<EObject, Set<EObject>> entry: impactedMap.entrySet()) {
        	for (EObject impacted: entry.getValue()) {
            	if (impacted instanceof ASILDecompositionStrategy) {
            		for (CoreElement child: getChildCoreElements((ASILDecompositionStrategy) impacted)) {
            			if (child instanceof IndependenceGoal) {
            				if (!impactedGoals.containsKey(impacted)) {
            					impactedGoals.put(impacted, new HashSet<>());
            				}
            				
            				if (!impactedGoals.containsKey(child)) {
            					impactedGoals.put(child, new HashSet<>());
            				}
            				
            				impactedGoals.get(impacted).add(child);
            			}
            		}
            	}       		
        	}
        }
        
        for (Entry<EObject, Set<EObject>> entry: impactedGoals.entrySet()) {
        	if (!impactedMap.containsKey(entry.getKey())) {
        		impactedMap.put(entry.getKey(), new HashSet<>());
        	}
        	
        	impactedMap.get(entry.getKey()).addAll(entry.getValue());
        }

        return impactedMap;
    }
	
	// Get impacted model elements directly reachable from the input element.
	@Override
	protected Map<EObject, Set<EObject>> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {

		HashMap<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);
	    
	    // By default, the input model element is impacted.
		impactedMap.put(modelObj, new HashSet<>());
	    impactedMap.get(modelObj).add(modelObj);

		// If input is a goal, then the following are potentially impacted:
		// 1) Any parent core element (i.e. goal, strategy or solution).
	    // 2) Any child core element.
		if (modelObj instanceof Goal) {
			Goal g = (Goal) modelObj;
			impactedMap.get(modelObj).addAll(getChildCoreElements(g));
			
			for (Entry<EObject, Set<EObject>> entry: getImpactedParents(g, impactedAll).entrySet()) {
				if (impactedMap.containsKey(entry.getKey())) {
					impactedMap.get(entry.getKey()).addAll(entry.getValue());
				} else {
					impactedMap.put(entry.getKey(), entry.getValue());
				}
			}

		// If input is a strategy, then the content validity should be rechecked for:
		// 1) Any child core element.
		// 2) Any contexts and justifications connected to it.
		} else if (modelObj instanceof Strategy) {
			Strategy s = (Strategy) modelObj;
			impactedMap.get(modelObj).addAll(getChildCoreElements(s));
			
			Set<ContextualElement> contexts = new HashSet<>();			
			for (InContextOf rel : s.getInContextOf()) {
				contexts.add(rel.getContext());
			}
			
			impactedMap.get(modelObj).addAll(contexts);

		// If input is a solution, then the content validity should be rechecked for:
		// 1) Any parent supportable.
		} else if (modelObj instanceof Solution) {
			Solution s = (Solution) modelObj;

			for (Entry<EObject, Set<EObject>> entry: getImpactedParents(s, impactedAll).entrySet()) {
				if (impactedMap.containsKey(entry.getKey())) {
					impactedMap.get(entry.getKey()).addAll(entry.getValue());
				} else {
					impactedMap.put(entry.getKey(), entry.getValue());
				}
			}

		// If input is a context, then the content validity should be rechecked for:
		// 1) All argument elements that uses or inherits input context.
		} else if (modelObj instanceof Context) {
			Context c = (Context) modelObj;

			Set<ArgumentElement> inheritors = new HashSet<>();
			for (InContextOf rel : c.getContextOf()) {
				inheritors.addAll(getContextInheritors(rel.getContextOf()));
			}
			
			impactedMap.get(modelObj).addAll(inheritors);

		// If input is a justification, then nothing else requires rechecking.
		} else if (modelObj instanceof Justification) {

		// If input is an assumption, then the following are impacted: 
		// 1) All argument elements that uses or inherits input assumption.
		} else if (modelObj instanceof Assumption) {
			Assumption a = (Assumption) modelObj;
			Set<ArgumentElement> inheritors = new HashSet<>();
			for (InContextOf rel : a.getContextOf()) {
				inheritors.addAll(getContextInheritors(rel.getContextOf()));
			}
			
			impactedMap.get(modelObj).addAll(inheritors);
		}

		return impactedMap;
	}

	// Returns all the descendant argument elements (core and contextual 
	// elements) of the input decomposable core element.
	public Set<ArgumentElement> getContextInheritors(DecomposableCoreElement elem) {
		Set<ArgumentElement> descendantsAll = new HashSet<>();
		Set<ArgumentElement> descendantsCur = new HashSet<>();
		Set<ArgumentElement> descendantsNext = new HashSet<>();

		// Iterate through the current set of newly added descendants
		// to identify the next generation of descendants.
		descendantsAll.add(elem);
		descendantsCur.add(elem);
		while (!descendantsCur.isEmpty()) {
			for (ArgumentElement curElem : descendantsCur) {
				if (curElem instanceof DecomposableCoreElement) {
					DecomposableCoreElement d = (DecomposableCoreElement) curElem;
					if (d.getSupportedBy() != null) {
						descendantsNext.addAll(getChildCoreElements(d));
					}

					if (d.getInContextOf() != null) {
						for (InContextOf rel: d.getInContextOf()) {
							descendantsNext.add(rel.getContext());
						}
					}
				}
			}

			descendantsCur.clear();
			for (ArgumentElement newElem : descendantsNext) {
				if (!descendantsAll.contains(newElem)) {
					descendantsAll.add(newElem);
					descendantsCur.add(newElem);
				}
			}

			descendantsNext.clear();
		}

		return descendantsAll;
	}
	
	// Determine which parents of the input core element are potentially impacted.
	// Returns a map from the source of the impact to the corresponding set of impacted parents.
	public Map<EObject, Set<EObject>> getImpactedParents(CoreElement modelObj, Set<EObject> alreadyImpacted) {
		HashMap<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);

		for (DecomposableCoreElement parent: getParentCoreElements(modelObj)) {
			for (CoreElement src: getImpactSources(parent, impactedAll, false)) {
				if (!impactedMap.containsKey(src)) {
					impactedMap.put(src, new HashSet<>());
				}

				impactedMap.get(src).add(parent);
			}
		}

		return impactedMap;
	}	

}
