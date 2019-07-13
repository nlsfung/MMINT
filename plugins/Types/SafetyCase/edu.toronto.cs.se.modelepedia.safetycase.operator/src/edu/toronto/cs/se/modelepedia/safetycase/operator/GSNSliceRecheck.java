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
import edu.toronto.cs.se.modelepedia.safetycase.CoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.Solution;
import edu.toronto.cs.se.modelepedia.safetycase.Supportable;
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;
import edu.toronto.cs.se.modelepedia.safetycase.Supporter;

public class GSNSliceRecheck extends GSNSlice {

	// Get impacted model elements directly reachable from the input element.
	@Override
	protected Map<EObject, Set<EObject>> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {

		Map<EObject, Set<EObject>> impactedMap = new HashMap<>();
	    
		// If input is a goal, then the state validity of its ancestor goals should be rechecked.
		if (modelObj instanceof Goal) {
			Goal g = (Goal) modelObj;
			impactedMap.putAll(getImpactedParentGoals(g, alreadyImpacted));
			
		// If input is a solution, then the state validity of its ancestor goals should be rechecked.
		} else if (modelObj instanceof Solution) {
			Solution s = (Solution) modelObj;
			impactedMap.putAll(getImpactedParentGoals(s, alreadyImpacted));
		}
		
		// Remove from the map any impacted elements that are not goals
		Map<EObject, Set<EObject>> filteredMap = new HashMap<>();
		for (Entry<EObject, Set<EObject>> entry: impactedMap.entrySet()) {
			entry.getValue().removeIf(elem -> !(elem instanceof Goal));
			if (!entry.getValue().isEmpty()) {
				filteredMap.put(entry.getKey(), entry.getValue());
			}
		}
	    
	    // By default, the input element is also impacted
		if (!filteredMap.containsKey(modelObj)) {
			filteredMap.put(modelObj, new HashSet<>());
		}
	    filteredMap.get(modelObj).add(modelObj);

		return filteredMap;
	}
	
	// Returns the first layer of parent goals of the input core element.
	// The parent goals may be indirectly connected to the input via other elements (viz. strategies).
	public Set<Goal> getParentGoalLayer(CoreElement inputElem) {
		Set<Goal> parents = new HashSet<>();
		
		Set<Supporter> supportersCur = new HashSet<>();
		Set<Supporter> supportersAll = new HashSet<>();
		Set<Supporter> supportersNext = new HashSet<>();
		
		supportersCur.add(inputElem);
		supportersAll.add(inputElem);
		while (!supportersCur.isEmpty()) {
			for (Supporter elem: supportersCur) {
				for (SupportedBy rel: elem.getSupports()) {
					Supportable src = rel.getSource();
					
					if (src instanceof Goal) {
						parents.add((Goal) src);
					} else if (src instanceof Supporter) {
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
	
	// Get all parent goals of the input core element that are impacted by said 
	// element and/or any of the elements that are already impacted.
	// Returns a map from the impact source to the impacted parents.
	Map<EObject, Set<EObject>> getImpactedParentGoals(CoreElement modelObj, Set<EObject> alreadyImpacted) {
		Map<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);
		
		for (Goal parent: getParentGoalLayer(modelObj)) {
			for (CoreElement src: getImpactSources(parent, impactedAll, true)) {
				if (!impactedMap.containsKey(src)) {
					impactedMap.put(src, new HashSet<>());
				}
				
				impactedMap.get(src).add(parent);
			}
		}	
		
		return impactedMap;
		
	}
}
