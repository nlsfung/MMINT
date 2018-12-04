/**
 * Copyright (c) 2012-2018 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
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
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getGoal()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='GoalSupporter GoalContext ASILInheritance StateValidityInheritance'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot GoalSupporter='self.supportedBy -&gt; forAll(s | s.premise.oclIsKindOf(Goal) or s.premise.oclIsKindOf(Strategy) or s.premise.oclIsKindOf(Solution))' GoalContext='self.inContextOf.context -&gt; forAll(c | c.oclIsKindOf(Context) or c.oclIsKindOf(Assumption) or c.oclIsKindOf(Justification))' ASILInheritance='let directParents : Set(Goal) = self.supports.conclusion -&gt; select(d | d.oclIsKindOf(Goal)).oclAsType(Goal) -&gt; asSet(), \n\t\t\tindirectParents : Set(Goal) = self.supports.conclusion -&gt; select(d | d.oclIsTypeOf(BasicStrategy)).supports.conclusion -&gt; select(d | d.oclIsKindOf(Goal)).oclAsType(Goal) -&gt; asSet() \n\t\t\tin indirectParents -&gt; union(directParents) -&gt; forAll(g | if g.asil = null then true else if self.asil = null then false else g.asil.value = ASILLevel::QM or (g.asil.value.toString() &lt;= self.asil.value.toString() and self.asil.value &lt;&gt; ASILLevel::QM) endif endif)' StateValidityInheritance='self.stateValidity = ValidityValue::Valid implies \n\t\t\tlet directChildren : Set(StatefulElement) = self.supportedBy.premise -&gt; select(d | d.oclIsKindOf(StatefulElement)).oclAsType(StatefulElement) -&gt; asSet(), \n\t\t\t\tindirectChildren : Set(StatefulElement) = self.supportedBy.premise -&gt; select(d | d.oclIsKindOf(Strategy)).oclAsType(Strategy).supportedBy.premise.oclAsType(StatefulElement) -&gt; asSet() \n\t\t\tin indirectChildren -&gt; union(directChildren) -&gt; forAll(g | g.stateValidity = ValidityValue::Valid)'"
 * @generated
 */
public interface Goal extends DecomposableCoreElement, StatefulElement, ASILfulElement {
} // Goal
