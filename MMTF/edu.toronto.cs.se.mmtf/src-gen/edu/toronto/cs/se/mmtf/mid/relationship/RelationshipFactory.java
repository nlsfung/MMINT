/**
 * Copyright (c) 2013 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmtf.mid.relationship;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.se.mmtf.mid.relationship.RelationshipPackage
 * @generated
 */
public interface RelationshipFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RelationshipFactory eINSTANCE = edu.toronto.cs.se.mmtf.mid.relationship.impl.RelationshipFactoryImpl.init();

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
	 * Returns a new object of class '<em>Model Endpoint Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Endpoint Reference</em>'.
	 * @generated
	 */
	ModelEndpointReference createModelEndpointReference();

	/**
	 * Returns a new object of class '<em>Model Element Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Element Reference</em>'.
	 * @generated
	 */
	ModelElementReference createModelElementReference();

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
	 * Returns a new object of class '<em>Model Element Endpoint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Element Endpoint</em>'.
	 * @generated
	 */
	ModelElementEndpoint createModelElementEndpoint();

	/**
	 * Returns a new object of class '<em>Link Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link Reference</em>'.
	 * @generated
	 */
	LinkReference createLinkReference();

	/**
	 * Returns a new object of class '<em>Binary Link Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Link Reference</em>'.
	 * @generated
	 */
	BinaryLinkReference createBinaryLinkReference();

	/**
	 * Returns a new object of class '<em>Model Element Endpoint Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Element Endpoint Reference</em>'.
	 * @generated
	 */
	ModelElementEndpointReference createModelElementEndpointReference();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelationshipPackage getRelationshipPackage();

} //RelationshipFactory
