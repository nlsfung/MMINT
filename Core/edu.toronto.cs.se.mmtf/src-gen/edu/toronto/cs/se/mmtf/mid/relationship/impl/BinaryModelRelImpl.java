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
package edu.toronto.cs.se.mmtf.mid.relationship.impl;

import java.util.HashMap;

import edu.toronto.cs.se.mmtf.MMTFException;
import edu.toronto.cs.se.mmtf.mid.Model;
import edu.toronto.cs.se.mmtf.mid.constraint.MultiModelConstraintChecker;
import edu.toronto.cs.se.mmtf.mid.operator.ConversionOperator;
import edu.toronto.cs.se.mmtf.mid.relationship.BinaryModelRel;
import edu.toronto.cs.se.mmtf.mid.relationship.RelationshipPackage;
import edu.toronto.cs.se.mmtf.reasoning.Z3SMTUtils.MAVOTruthValue;
import edu.toronto.cs.se.mmtf.transformation.ModelRelTypeTransformation;
import edu.toronto.cs.se.mmtf.transformation.ModelRelTypeTransformationConstraint;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Model Rel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.BinaryModelRelImpl#getSourceModel <em>Source Model</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.BinaryModelRelImpl#getTargetModel <em>Target Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BinaryModelRelImpl extends ModelRelImpl implements BinaryModelRel {
	/**
	 * The cached setting delegate for the '{@link #getSourceModel() <em>Source Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModel()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate SOURCE_MODEL__ESETTING_DELEGATE = ((EStructuralFeature.Internal)RelationshipPackage.Literals.BINARY_MODEL_REL__SOURCE_MODEL).getSettingDelegate();
	/**
	 * The cached setting delegate for the '{@link #getTargetModel() <em>Target Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetModel()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate TARGET_MODEL__ESETTING_DELEGATE = ((EStructuralFeature.Internal)RelationshipPackage.Literals.BINARY_MODEL_REL__TARGET_MODEL).getSettingDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryModelRelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationshipPackage.Literals.BINARY_MODEL_REL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getSourceModel() {
		return (Model)SOURCE_MODEL__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetSourceModel() {
		return (Model)SOURCE_MODEL__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceModel(Model newSourceModel) {
		SOURCE_MODEL__ESETTING_DELEGATE.dynamicSet(this, null, 0, newSourceModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getTargetModel() {
		return (Model)TARGET_MODEL__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetTargetModel() {
		return (Model)TARGET_MODEL__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetModel(Model newTargetModel) {
		TARGET_MODEL__ESETTING_DELEGATE.dynamicSet(this, null, 0, newTargetModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationshipPackage.BINARY_MODEL_REL__SOURCE_MODEL:
				if (resolve) return getSourceModel();
				return basicGetSourceModel();
			case RelationshipPackage.BINARY_MODEL_REL__TARGET_MODEL:
				if (resolve) return getTargetModel();
				return basicGetTargetModel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelationshipPackage.BINARY_MODEL_REL__SOURCE_MODEL:
				setSourceModel((Model)newValue);
				return;
			case RelationshipPackage.BINARY_MODEL_REL__TARGET_MODEL:
				setTargetModel((Model)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RelationshipPackage.BINARY_MODEL_REL__SOURCE_MODEL:
				setSourceModel((Model)null);
				return;
			case RelationshipPackage.BINARY_MODEL_REL__TARGET_MODEL:
				setTargetModel((Model)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RelationshipPackage.BINARY_MODEL_REL__SOURCE_MODEL:
				return SOURCE_MODEL__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
			case RelationshipPackage.BINARY_MODEL_REL__TARGET_MODEL:
				return TARGET_MODEL__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public ConversionOperator getTypeTransformationOperator(Model srcModel) throws MMTFException {

		if (MultiModelConstraintChecker.isInstancesLevel(this)) {
			throw new MMTFException("Can't execute TYPES level operation on INSTANCES level element");
		}
		if (new ModelRelTypeTransformationConstraint(this).validate() != MAVOTruthValue.TRUE) {
			throw new MMTFException("Transformation constraint not satisfied");
		}
		if (!MultiModelConstraintChecker.isAllowedModelEndpoint(getModelEndpointRefs().get(0), srcModel, new HashMap<String, Integer>())) {
			throw new MMTFException("Source model not allowed");
		}

		ConversionOperator transformationOperator = new ModelRelTypeTransformation();
		transformationOperator.setName("ModelRelTypeTransformation");

		return transformationOperator;
	}

} //BinaryModelRelImpl
