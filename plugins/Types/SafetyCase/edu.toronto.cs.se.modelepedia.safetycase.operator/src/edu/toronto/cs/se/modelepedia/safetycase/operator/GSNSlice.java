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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import edu.toronto.cs.se.mmint.operator.slice.Slice;
import edu.toronto.cs.se.modelepedia.safetycase.AndSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.CoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.OrSupporter;
import edu.toronto.cs.se.modelepedia.safetycase.Strategy;
import edu.toronto.cs.se.modelepedia.safetycase.SupportConnector;
import edu.toronto.cs.se.modelepedia.safetycase.Supportable;
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;
import edu.toronto.cs.se.modelepedia.safetycase.Supporter;
import edu.toronto.cs.se.modelepedia.safetycase.XorSupporter;

// Contains useful functions for slicing safety cases.
public class GSNSlice extends Slice {
	
	// Returns all child core elements of the input decomposable core element.
	public Set<CoreElement> getChildCoreElements(DecomposableCoreElement inputElem) {
		Set<CoreElement> children = new HashSet<>();
		
		Set<Supportable> supportablesCur = new HashSet<>();
		Set<Supportable> supportablesAll = new HashSet<>();
		Set<Supportable> supportablesNext = new HashSet<>();
		
		supportablesCur.add(inputElem);
		while (!supportablesCur.isEmpty()) {
			for (Supportable elem: supportablesCur) {
				for (SupportedBy rel : elem.getSupportedBy()) {
					Supporter s = rel.getTarget();
					
					if (s instanceof CoreElement) {
						children.add((CoreElement) s);
						
					} else if (s instanceof SupportConnector) {
						supportablesNext.add((SupportConnector) s);
					}						
				}				
			}
			
			supportablesCur.clear();
			for (Supportable s: supportablesNext) {
				if (!supportablesAll.contains(s)) {
					supportablesAll.add(s);
					supportablesCur.add(s);
				}
			}
			
			supportablesNext.clear();
		}

		return children;
	}
	
	// Returns all parent decomposable core elements of the input core element.
	public Set<DecomposableCoreElement> getParentCoreElements(CoreElement inputElem) {
		Set<DecomposableCoreElement> parents = new HashSet<>();
		
		Set<Supporter> supportersCur = new HashSet<>();
		Set<Supporter> supportersAll = new HashSet<>();
		Set<Supporter> supportersNext = new HashSet<>();
		
		supportersCur.add(inputElem);
		supportersAll.add(inputElem);
		while (!supportersCur.isEmpty()) {
			for (Supporter elem: supportersCur) {
				for (SupportedBy rel: elem.getSupports()) {
					Supportable src = rel.getSource();
					
					if (src instanceof DecomposableCoreElement) {
						parents.add((DecomposableCoreElement) src);
					} else if (src instanceof SupportConnector) {
						supportersNext.add(src);
					}
				}
			}
			
			supportersCur.clear();
			for (Supporter elem: supportersNext) {
				if (!supportersAll.contains(elem)) {
					supportersCur.add(elem);
					supportersAll.add(elem);
				}
			}
			
			supportersNext.clear();
		}
		
		return parents;
	}
	
	// Returns all ancestor decomposable core elements of the input core element.
	public Set<DecomposableCoreElement> getAncestorCoreElements(CoreElement inputElem) {
		Set<DecomposableCoreElement> ancestors = new HashSet<>();
		
		Set<Supporter> supportersCur = new HashSet<>();
		Set<Supporter> supportersAll = new HashSet<>();
		Set<Supporter> supportersNext = new HashSet<>();
		
		supportersCur.add(inputElem);
		supportersAll.add(inputElem);
		while (!supportersCur.isEmpty()) {
			for (Supporter elem: supportersCur) {
				for (SupportedBy rel: elem.getSupports()) {
					Supportable src = rel.getSource();
					supportersNext.add(src);
					
					if (src instanceof DecomposableCoreElement) {
						ancestors.add((DecomposableCoreElement) src);
					}
				}
			}
			
			supportersCur.clear();
			for (Supporter elem: supportersNext) {
				if (!supportersAll.contains(elem)) {
					supportersCur.add(elem);
					supportersAll.add(elem);
				}
			}
			
			supportersNext.clear();
		}
		
		return ancestors;
	}
	
	// Get all parents of the input core element that are impacted by said 
	// element and/or any of the elements that are already impacted.
	// Returns a map from the impact source to the impacted parents.
	Map<EObject, Set<EObject>> getImpactedParents(CoreElement modelObj, Set<EObject> alreadyImpacted) {
		HashMap<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);
		
		for (DecomposableCoreElement parent: getParentCoreElements(modelObj)) {
			for (CoreElement src: getImpactSources(parent, impactedAll)) {
				if (!impactedMap.containsKey(src)) {
					impactedMap.put(src, new HashSet<>());
				}
				
				impactedMap.get(src).add(parent);
			}
		}	
		
		return impactedMap;
		
	}
	
	// Get all ancestors of the input core element that are impacted by said 
	// element and/or any of the elements that are already impacted.
	// Returns a map from the impact source to the impacted ancestors.
	Map<EObject, Set<EObject>> getImpactedAncestors(CoreElement modelObj, Set<EObject> alreadyImpacted) {
		HashMap<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);
		
		for (DecomposableCoreElement ancestor: getAncestorCoreElements(modelObj)) {
			for (CoreElement src: getImpactSources(ancestor, impactedAll)) {
				if (!impactedMap.containsKey(src)) {
					impactedMap.put(src, new HashSet<>());
				}
				
				impactedMap.get(src).add(ancestor);
			}
		}	
		
		return impactedMap;
		
	}
	
	// Returns all ancestor goals of the input core element, stopping when one is already impacted.
//	public Set<Goal> getAncestorGoals(CoreElement elem, Set<EObject> alreadyImpacted) {
//		Set<Supportable> ancestorsCur = new HashSet<>();
//		Set<Supportable> ancestorsNext = new HashSet<>();
//		Set<Supportable> ancestorsAll = new HashSet<>();
//		Set<Goal> goalAncestors = new HashSet<>();
//
//		// Iterate through the current set of newly added ancestors
//		// to identify the next generation of ancestors.
//		for (SupportedBy rel : elem.getSupports()) {
//			ancestorsNext.add(rel.getSource());
//			if (rel.getSource() instanceof Goal) {
//				goalAncestors.add((Goal) rel.getSource());
//			}
//		}
//
//		ancestorsCur.addAll(ancestorsNext);
//		ancestorsAll.addAll(ancestorsNext);
//		ancestorsNext.clear();
//
//		while (!ancestorsCur.isEmpty()) {
//			Set<EObject> impactedAll = new HashSet<>();
//			impactedAll.addAll(alreadyImpacted);
//			impactedAll.addAll(ancestorsAll);
//			
//			for (Supportable curElem : ancestorsCur) {
//				if (isImpactPropagatedUp(curElem, impactedAll)) {
//					for (SupportedBy rel : curElem.getSupports()) {
//						ancestorsNext.add(rel.getSource());
//					}
//				}
//			}
//
//			ancestorsCur.clear();
//			for (Supportable newElem : ancestorsNext) {
//				if (!ancestorsAll.contains(newElem)) {
//					ancestorsAll.add(newElem);
//					ancestorsCur.add(newElem);
//				}
//				
//				if (newElem instanceof Goal) {
//					goalAncestors.add((Goal) newElem);
//				}
//			}
//
//			ancestorsNext.clear();
//		}
//				
//		return goalAncestors;
//	}
	
	// Returns the set of impact sources for the input supportable given the set of 
	// all other impacted elements. Returns an empty set if the input is not impacted.
	public Set<CoreElement> getImpactSources(Supportable elem, Set<EObject> alreadyImpacted) {
		
		// Get all children of the input element and their impact sources.
		HashMap<Supporter, Set<CoreElement>> impactMap = new HashMap<>();
		for (SupportedBy rel: elem.getSupportedBy()) {
			Supporter child = rel.getTarget();
			Set<CoreElement> childSources = new HashSet<>();
			
			// If child is a core element, then its impact source is itself
			// (Assuming it is already impacted)
			if (child instanceof CoreElement && alreadyImpacted.contains(child)) {
				childSources.add((CoreElement) child);
				
			// Otherwise, the child is a support connector and its impact sources
			// must be determined (if any).
			} else if (child instanceof SupportConnector) {
				childSources.addAll(getImpactSources((SupportConnector) child, alreadyImpacted)); 
			}
			
			impactMap.put(child, childSources);
		}
		
		// Count the number of children and the number of impacted ones.
		int impactCount = 0;
		int totalCount = 0;
		Set<CoreElement> impactSources = new HashSet<>();
		for (Entry<Supporter, Set<CoreElement>> entry: impactMap.entrySet()) {
			totalCount += 1;
			if (!entry.getValue().isEmpty()) {
				impactCount += 1;
				impactSources.addAll(entry.getValue());
			}
			
		}
		
		// If element is impacted, return its impact sources.
		if (!isImpactPropagatedUp(elem, totalCount, impactCount)) {
			impactSources.clear();
		}

		return impactSources;
	}
	
	
	// Determines whether a change impact is propagated up or not given the source element, 
	// the total number of its children and how	many of those are impacted.
	public boolean isImpactPropagatedUp(Supportable elem, int totalCount, int impactCount) {
		boolean isImpactPropagated = false;
		
		// If a core element is impacted, then its parents are also impacted.
		if (elem instanceof CoreElement) {
			isImpactPropagated = true;
		
		// If an AND-connector is impacted, then its parents are 
		// impacted if any of its children are impacted.
		} else if (elem instanceof AndSupporter) {
			if (impactCount >= 1) {
				isImpactPropagated = true;
			}
		
		// If an OR-connector is impacted, then its parents are 
		// impacted if all its children are impacted.
		} else if (elem instanceof OrSupporter) {
			if (impactCount == totalCount) {
				isImpactPropagated = true;
			}
		
		// If an XOR-connector is impacted, then its parents are 
		// impacted if any of its children are impacted.        		
		} else if (elem instanceof XorSupporter) {
			if (impactCount >= 1) {
				isImpactPropagated = true;
			}
		
		// If an M-of-N connector is impacted, then its parents are 
		// impacted if more than (N-M) children are impacted.
		} else if (elem instanceof MofNSupporter) {
			long target = ((MofNSupporter) elem).getTarget();
			
			if (impactCount > totalCount - target) {
				isImpactPropagated = true;
			}
		}		
		
		return isImpactPropagated;
	}
	
}
