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

public class GSNSliceRevise2State extends GSNSlice {
	
    // Get all model elements in a safety case that needs to be re-checked for 
	// state validity given the input element that requires revision.
    @Override
    protected Set<EObject> getAllImpactedElements(EObject critModelObj, Set<EObject> alreadyImpacted) {

        Set<EObject> impactedModelObjs = new HashSet<>();
        
        // Slice all elements iteratively until a fixed point is reached.
        Set<EObject> impactedCur = new HashSet<>();
        Set<EObject> impactedNext = new HashSet<>();
        
        impactedCur.add(critModelObj);
        while (!impactedCur.isEmpty()) {
        	for (EObject elem: impactedCur) {
        		impactedNext.addAll(getDirectlyImpactedElements(elem, alreadyImpacted));
        	}
        	
        	// Check if fixed point has been reached
        	impactedCur.clear();
        	if (!impactedModelObjs.containsAll(impactedNext)) {
        		impactedModelObjs.addAll(impactedNext);
        		alreadyImpacted.addAll(impactedNext);
        		impactedCur.addAll(impactedModelObjs);
        		impactedNext.clear();
        	}
        }

        return impactedModelObjs;
    }
	
	// Get impacted model elements directly reachable from the input element.
	@Override
	protected Set<EObject> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {

	    Set<EObject> impacted = new HashSet<>();
	    
	    // By default, the input element is impacted
        impacted.add(modelObj);

		// If input is a strategy, then the state validity should be rechecked for:
		// 1) All ancestor goals.
		if (modelObj instanceof Strategy) {
			Strategy s = (Strategy) modelObj;
			impacted.addAll(getAncestorGoals(s, alreadyImpacted));
		}

		return impacted;
	}


}
