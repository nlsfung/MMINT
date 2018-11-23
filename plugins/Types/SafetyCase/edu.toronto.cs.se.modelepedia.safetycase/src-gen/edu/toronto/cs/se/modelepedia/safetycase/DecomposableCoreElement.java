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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decomposable Core Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement#getInContextOf <em>In Context Of</em>}</li>
 *   <li>{@link edu.toronto.cs.se.modelepedia.safetycase.DecomposableCoreElement#getSupportedBy <em>Supported By</em>}</li>
 * </ul>
 *
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getDecomposableCoreElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='SupportCycle NonDecomposableLeaves'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot SupportCycle='self.supportedBy.premise -&gt; closure(p | if p.oclIsKindOf(DecomposableCoreElement) then \n\t\t\tp.oclAsType(DecomposableCoreElement).supportedBy.premise else \n\t\t\tp.oclAsSet() endif) -&gt; excludes(self)' NonDecomposableLeaves='self.supportedBy.premise -&gt; size() &gt; 0 and self.supportedBy.premise -&gt; excludes(null)'"
 * @generated
 */
public interface DecomposableCoreElement extends CoreElement {
	/**
	 * Returns the value of the '<em><b>In Context Of</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.se.modelepedia.safetycase.InContextOf}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.se.modelepedia.safetycase.InContextOf#getContextOf <em>Context Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Context Of</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Context Of</em>' containment reference list.
	 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getDecomposableCoreElement_InContextOf()
	 * @see edu.toronto.cs.se.modelepedia.safetycase.InContextOf#getContextOf
	 * @model opposite="contextOf" containment="true"
	 * @generated
	 */
	EList<InContextOf> getInContextOf();

	/**
	 * Returns the value of the '<em><b>Supported By</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.se.modelepedia.safetycase.SupportedBy}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.se.modelepedia.safetycase.SupportedBy#getConclusion <em>Conclusion</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supported By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supported By</em>' containment reference list.
	 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getDecomposableCoreElement_SupportedBy()
	 * @see edu.toronto.cs.se.modelepedia.safetycase.SupportedBy#getConclusion
	 * @model opposite="conclusion" containment="true" required="true"
	 * @generated
	 */
	EList<SupportedBy> getSupportedBy();

} // DecomposableCoreElement
