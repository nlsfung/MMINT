/**
 * Copyright (c) 2012-2017 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
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
package edu.toronto.cs.se.modelepedia.safetycase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ASIL Decomposition Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getASILDecompositionStrategy()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ASILDecompositionIndependence ASILDecompositionComponents ASILDescendants'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ASILDecompositionIndependence='self.supportedBy.premise -&gt; selectByType(IndependenceGoal) -&gt; size() = 1' ASILDecompositionComponents='self.supportedBy.premise -&gt; selectByType(BasicGoal) -&gt; size() = 2' ASILDescendants='let goalSeq: Sequence(CoreElement) = self.supportedBy.premise -&gt; select(p | p.oclIsTypeOf(BasicGoal)), \n\t\t\tg1Descendants : Set(CoreElement) = goalSeq -&gt; at(1) -&gt; closure(c | \n\t\t\t\t\tif c.oclIsKindOf(DecomposableCoreElement) then c.oclAsType(DecomposableCoreElement).supportedBy.premise else null endif),\n\t\t\tg2Descendants : Set(CoreElement) = goalSeq -&gt; at(2) -&gt; closure(c | \n\t\t\t\t\tif c.oclIsKindOf(DecomposableCoreElement) then c.oclAsType(DecomposableCoreElement).supportedBy.premise else null endif) \n\t\t\tin g1Descendants -&gt; intersection(g2Descendants) = Set{}'"
 * @generated
 */
public interface ASILDecompositionStrategy extends Strategy {
} // ASILDecompositionStrategy
