/**
 * Copyright (c) 2012 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Vivien Suen.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmtf.mid.mapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.se.mmtf.mid.mapping.MappingPackage
 * @generated
 */
public interface MappingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MappingFactory eINSTANCE = edu.toronto.cs.se.mmtf.mid.mapping.impl.MappingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model Rel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Rel</em>'.
	 * @generated
	 */
	ModelRel createModelRel();

	/**
	 * Returns a new object of class '<em>Binary Model Rel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Model Rel</em>'.
	 * @generated
	 */
	BinaryModelRel createBinaryModelRel();

	/**
	 * Returns a new object of class '<em>Homomorphism Model Rel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Homomorphism Model Rel</em>'.
	 * @generated
	 */
	HomomorphismModelRel createHomomorphismModelRel();

	/**
	 * Returns a new object of class '<em>Model Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Container</em>'.
	 * @generated
	 */
	ModelContainer createModelContainer();

	/**
	 * Returns a new object of class '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Element</em>'.
	 * @generated
	 */
	ModelElement createModelElement();

	/**
	 * Returns a new object of class '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link</em>'.
	 * @generated
	 */
	Link createLink();

	/**
	 * Returns a new object of class '<em>Binary Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Link</em>'.
	 * @generated
	 */
	BinaryLink createBinaryLink();

	/**
	 * Returns a new object of class '<em>Homomorphism Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Homomorphism Link</em>'.
	 * @generated
	 */
	HomomorphismLink createHomomorphismLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MappingPackage getMappingPackage();

} //MappingFactory
