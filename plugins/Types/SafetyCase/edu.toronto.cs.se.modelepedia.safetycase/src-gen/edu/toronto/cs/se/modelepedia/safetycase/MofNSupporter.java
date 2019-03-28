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
 * A representation of the model object '<em><b>Mof NSupporter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter#getNumerator <em>Numerator</em>}</li>
 *   <li>{@link edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter#getDenominator <em>Denominator</em>}</li>
 * </ul>
 *
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getMofNSupporter()
 * @model
 * @generated
 */
public interface MofNSupporter extends SupportConnector {

	/**
	 * Returns the value of the '<em><b>Numerator</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Numerator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Numerator</em>' attribute.
	 * @see #setNumerator(long)
	 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getMofNSupporter_Numerator()
	 * @model default="1" required="true"
	 * @generated
	 */
	long getNumerator();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter#getNumerator <em>Numerator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numerator</em>' attribute.
	 * @see #getNumerator()
	 * @generated
	 */
	void setNumerator(long value);

	/**
	 * Returns the value of the '<em><b>Denominator</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Denominator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Denominator</em>' attribute.
	 * @see #setDenominator(long)
	 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getMofNSupporter_Denominator()
	 * @model default="1" required="true"
	 * @generated
	 */
	long getDenominator();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.se.modelepedia.safetycase.MofNSupporter#getDenominator <em>Denominator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Denominator</em>' attribute.
	 * @see #getDenominator()
	 * @generated
	 */
	void setDenominator(long value);
} // MofNSupporter
