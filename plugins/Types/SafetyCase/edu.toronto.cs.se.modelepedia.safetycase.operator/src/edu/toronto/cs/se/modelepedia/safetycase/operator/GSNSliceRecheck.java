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
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;

public class GSNSliceRecheck extends GSNSlice {

	// Get impacted model elements directly reachable from the input element.
	@Override
	protected Map<EObject, Set<EObject>> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {

		Map<EObject, Set<EObject>> impactedMap = new HashMap<>();
	    
		// If input is a goal, then the state validity of its ancestor goals should be rechecked.
		if (modelObj instanceof Goal) {
			Goal g = (Goal) modelObj;
			impactedMap.putAll(getImpactedAncestors(g, alreadyImpacted));
			
		// If input is a solution, then the state validity of its ancestor goals should be rechecked.
		} else if (modelObj instanceof Solution) {
			Solution s = (Solution) modelObj;
			impactedMap.putAll(getImpactedAncestors(s, alreadyImpacted));
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
}
