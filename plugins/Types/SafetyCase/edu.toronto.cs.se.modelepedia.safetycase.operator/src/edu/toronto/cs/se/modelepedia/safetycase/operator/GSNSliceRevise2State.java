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

import edu.toronto.cs.se.modelepedia.safetycase.ASILDecompositionStrategy;
import edu.toronto.cs.se.modelepedia.safetycase.CoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement;
import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.IndependenceGoal;
import edu.toronto.cs.se.modelepedia.safetycase.Strategy;
import edu.toronto.cs.se.modelepedia.safetycase.SupportConnector;
import edu.toronto.cs.se.modelepedia.safetycase.Supportable;
import edu.toronto.cs.se.modelepedia.safetycase.SupportedBy;
import edu.toronto.cs.se.modelepedia.safetycase.Supporter;

public class GSNSliceRevise2State extends GSNSlice {
	
	// Get impacted model elements directly reachable from the input element.
	@Override
	protected Map<EObject, Set<EObject>> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {

	    Map<EObject, Set<EObject>> impactedMap = new HashMap<>();

		// If input is a strategy, then the state validity should be rechecked for:
		// 1) All ancestor goals.
		if (modelObj instanceof Strategy) {
			Strategy s = (Strategy) modelObj;
			impactedMap.putAll(getImpactedAncestorGoals(s, alreadyImpacted));
		}

	    // By default, the input element is impacted
		if (!impactedMap.containsKey(modelObj)) {
			impactedMap.put(modelObj, new HashSet<>());
		}
        impactedMap.get(modelObj).add(modelObj);
		
		return impactedMap;
	}
	
	// Returns all ancestor decomposable goals of the input core element.
	public Set<Goal> getAncestorGoals(CoreElement inputElem) {
		Set<Goal> ancestors = new HashSet<>();
		
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
					
					if (src instanceof Goal) {
						ancestors.add((Goal) src);
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
	
	// Get all ancestor goals of the input core element that are impacted by said 
	// element and/or any of the elements that are already impacted.
	// Returns a map from the impact source to the impacted ancestors.
	Map<EObject, Set<EObject>> getImpactedAncestorGoals(CoreElement modelObj, Set<EObject> alreadyImpacted) {
		HashMap<EObject, Set<EObject>> impactedMap = new HashMap<>();
		Set<EObject> impactedAll = new HashSet<>();
		impactedAll.addAll(alreadyImpacted);
		impactedAll.add(modelObj);
		
		for (DecomposableCoreElement ancestor: getAncestorGoals(modelObj)) {
			for (CoreElement src: getImpactSources(ancestor, impactedAll, true)) {
				if (!impactedMap.containsKey(src)) {
					impactedMap.put(src, new HashSet<>());
				}
				
				impactedMap.get(src).add(ancestor);
			}
		}	
		
		return impactedMap;
		
	}	
}
