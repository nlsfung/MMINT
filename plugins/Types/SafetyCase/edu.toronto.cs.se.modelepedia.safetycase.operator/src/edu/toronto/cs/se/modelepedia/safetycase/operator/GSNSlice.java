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
	
	// Returns the set of impact sources for the input supportable given the set of 
	// all other impacted elements. Returns an empty set if the input is not impacted.
	// Note: Depending on the specific slicer, the change impact may be propagated 
	// through strategies even if they themselves are not impacted, in which case the 
	// flag should be set to true.
	public Set<CoreElement> getImpactSources(Supportable elem, Set<EObject> alreadyImpacted, boolean skipFlag) {
		
		// Get all children of the input element and their impact sources.
		HashMap<Supporter, Set<CoreElement>> impactMap = new HashMap<>();
		for (SupportedBy rel: elem.getSupportedBy()) {
			Supporter child = rel.getTarget();
			Set<CoreElement> childSources = new HashSet<>();
			
			// If child is a core element, then its impact source is itself (if it is impacted).
			if (child instanceof CoreElement && alreadyImpacted.contains(child)) {
				childSources.add((CoreElement) child);
				
			// Otherwise, the child is a support connector (or unimpacted strategy) 
			// and its impact sources must be determined (if any).
			} else if (child instanceof SupportConnector) {
				childSources.addAll(getImpactSources((SupportConnector) child, alreadyImpacted, skipFlag)); 
				
			} else if (skipFlag && child instanceof Strategy && !alreadyImpacted.contains(child)) {
				childSources.addAll(getImpactSources((Strategy) child, alreadyImpacted, skipFlag));			
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
