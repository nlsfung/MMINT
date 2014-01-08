/**
 * Copyright (c) 2012-2014 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmtf.mid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Extendible Element Constraint Language</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The language used to evaluate an extendible element constraint.
 * <!-- end-model-doc -->
 * @see edu.toronto.cs.se.mmtf.mid.MidPackage#getExtendibleElementConstraintLanguage()
 * @model
 * @generated
 */
public enum ExtendibleElementConstraintLanguage implements Enumerator {
	/**
	 * The '<em><b>OCL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OCL_VALUE
	 * @generated
	 * @ordered
	 */
	OCL(0, "OCL", "OCL"),

	/**
	 * The '<em><b>JAVA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVA_VALUE
	 * @generated
	 * @ordered
	 */
	JAVA(1, "JAVA", "JAVA"),

	/**
	 * The '<em><b>SMTLIB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMTLIB_VALUE
	 * @generated
	 * @ordered
	 */
	SMTLIB(2, "SMTLIB", "SMTLIB");

	/**
	 * The '<em><b>OCL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The OCL language.
	 * <!-- end-model-doc -->
	 * @see #OCL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OCL_VALUE = 0;

	/**
	 * The '<em><b>JAVA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The JAVA language.
	 * <!-- end-model-doc -->
	 * @see #JAVA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int JAVA_VALUE = 1;

	/**
	 * The '<em><b>SMTLIB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The SMTLIB language.
	 * <!-- end-model-doc -->
	 * @see #SMTLIB
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SMTLIB_VALUE = 2;

	/**
	 * An array of all the '<em><b>Extendible Element Constraint Language</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ExtendibleElementConstraintLanguage[] VALUES_ARRAY =
		new ExtendibleElementConstraintLanguage[] {
			OCL,
			JAVA,
			SMTLIB,
		};

	/**
	 * A public read-only list of all the '<em><b>Extendible Element Constraint Language</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ExtendibleElementConstraintLanguage> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Extendible Element Constraint Language</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtendibleElementConstraintLanguage get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ExtendibleElementConstraintLanguage result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Extendible Element Constraint Language</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtendibleElementConstraintLanguage getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ExtendibleElementConstraintLanguage result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Extendible Element Constraint Language</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtendibleElementConstraintLanguage get(int value) {
		switch (value) {
			case OCL_VALUE: return OCL;
			case JAVA_VALUE: return JAVA;
			case SMTLIB_VALUE: return SMTLIB;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ExtendibleElementConstraintLanguage(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ExtendibleElementConstraintLanguage