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
package edu.toronto.cs.se.mmtf.mid.relationship.impl;

import edu.toronto.cs.se.mmtf.MMTFException;
import edu.toronto.cs.se.mmtf.MultiModelLightTypeFactory;
import edu.toronto.cs.se.mmtf.MultiModelTypeFactory;
import edu.toronto.cs.se.mmtf.MultiModelTypeHierarchy;
import edu.toronto.cs.se.mmtf.mid.ExtendibleElement;
import edu.toronto.cs.se.mmtf.mid.MidPackage;
import edu.toronto.cs.se.mmtf.mid.Model;
import edu.toronto.cs.se.mmtf.mid.ModelEndpoint;
import edu.toronto.cs.se.mmtf.mid.ModelOrigin;
import edu.toronto.cs.se.mmtf.mid.MultiModel;
import edu.toronto.cs.se.mmtf.mid.impl.ModelImpl;
import edu.toronto.cs.se.mmtf.mid.library.MultiModelInstanceFactory;
import edu.toronto.cs.se.mmtf.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmtf.mid.relationship.Link;
import edu.toronto.cs.se.mmtf.mid.relationship.LinkReference;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelElementEndpoint;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelEndpointReference;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmtf.mid.relationship.RelationshipFactory;
import edu.toronto.cs.se.mmtf.mid.relationship.RelationshipPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Rel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.ModelRelImpl#getModelEndpoints <em>Model Endpoints</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.ModelRelImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.ModelRelImpl#getModelEndpointRefs <em>Model Endpoint Refs</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.relationship.impl.ModelRelImpl#getLinkRefs <em>Link Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelRelImpl extends ModelImpl implements ModelRel {
	/**
	 * The cached value of the '{@link #getModelEndpoints() <em>Model Endpoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelEndpoints()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelEndpoint> modelEndpoints;

	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> links;

	/**
	 * The cached value of the '{@link #getModelEndpointRefs() <em>Model Endpoint Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelEndpointRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelEndpointReference> modelEndpointRefs;

	/**
	 * The cached value of the '{@link #getLinkRefs() <em>Link Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<LinkReference> linkRefs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelRelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationshipPackage.Literals.MODEL_REL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelEndpoint> getModelEndpoints() {
		if (modelEndpoints == null) {
			modelEndpoints = new EObjectContainmentEList<ModelEndpoint>(ModelEndpoint.class, this, RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS);
		}
		return modelEndpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getLinks() {
		if (links == null) {
			links = new EObjectContainmentEList<Link>(Link.class, this, RelationshipPackage.MODEL_REL__LINKS);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelEndpointReference> getModelEndpointRefs() {
		if (modelEndpointRefs == null) {
			modelEndpointRefs = new EObjectContainmentEList<ModelEndpointReference>(ModelEndpointReference.class, this, RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS);
		}
		return modelEndpointRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkReference> getLinkRefs() {
		if (linkRefs == null) {
			linkRefs = new EObjectContainmentEList<LinkReference>(LinkReference.class, this, RelationshipPackage.MODEL_REL__LINK_REFS);
		}
		return linkRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelRel getMetatype() {
		ExtendibleElement metatype = super.getMetatype();
		return (metatype == null) ? null : (ModelRel) metatype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getSupertype() {
		ExtendibleElement supertype = super.getSupertype();
		return (supertype == null) ? null : (Model) supertype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS:
				return ((InternalEList<?>)getModelEndpoints()).basicRemove(otherEnd, msgs);
			case RelationshipPackage.MODEL_REL__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS:
				return ((InternalEList<?>)getModelEndpointRefs()).basicRemove(otherEnd, msgs);
			case RelationshipPackage.MODEL_REL__LINK_REFS:
				return ((InternalEList<?>)getLinkRefs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS:
				return getModelEndpoints();
			case RelationshipPackage.MODEL_REL__LINKS:
				return getLinks();
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS:
				return getModelEndpointRefs();
			case RelationshipPackage.MODEL_REL__LINK_REFS:
				return getLinkRefs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS:
				getModelEndpoints().clear();
				getModelEndpoints().addAll((Collection<? extends ModelEndpoint>)newValue);
				return;
			case RelationshipPackage.MODEL_REL__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends Link>)newValue);
				return;
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS:
				getModelEndpointRefs().clear();
				getModelEndpointRefs().addAll((Collection<? extends ModelEndpointReference>)newValue);
				return;
			case RelationshipPackage.MODEL_REL__LINK_REFS:
				getLinkRefs().clear();
				getLinkRefs().addAll((Collection<? extends LinkReference>)newValue);
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
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS:
				getModelEndpoints().clear();
				return;
			case RelationshipPackage.MODEL_REL__LINKS:
				getLinks().clear();
				return;
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS:
				getModelEndpointRefs().clear();
				return;
			case RelationshipPackage.MODEL_REL__LINK_REFS:
				getLinkRefs().clear();
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
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINTS:
				return modelEndpoints != null && !modelEndpoints.isEmpty();
			case RelationshipPackage.MODEL_REL__LINKS:
				return links != null && !links.isEmpty();
			case RelationshipPackage.MODEL_REL__MODEL_ENDPOINT_REFS:
				return modelEndpointRefs != null && !modelEndpointRefs.isEmpty();
			case RelationshipPackage.MODEL_REL__LINK_REFS:
				return linkRefs != null && !linkRefs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == Model.class) {
			switch (baseOperationID) {
				case MidPackage.MODEL___GET_METATYPE: return RelationshipPackage.MODEL_REL___GET_METATYPE;
				case MidPackage.MODEL___GET_SUPERTYPE: return RelationshipPackage.MODEL_REL___GET_SUPERTYPE;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case RelationshipPackage.MODEL_REL___GET_METATYPE:
				return getMetatype();
			case RelationshipPackage.MODEL_REL___GET_SUPERTYPE:
				return getSupertype();
			case RelationshipPackage.MODEL_REL___CREATE_SUBTYPE__STRING_BOOLEAN_STRING_STRING:
				try {
					return createSubtype((String)arguments.get(0), (Boolean)arguments.get(1), (String)arguments.get(2), (String)arguments.get(3));
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
			case RelationshipPackage.MODEL_REL___DELETE_TYPE:
				try {
					deleteType();
					return null;
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
			case RelationshipPackage.MODEL_REL___CREATE_INSTANCE__STRING_BOOLEAN_MODELORIGIN_MULTIMODEL:
				try {
					return createInstance((String)arguments.get(0), (Boolean)arguments.get(1), (ModelOrigin)arguments.get(2), (MultiModel)arguments.get(3));
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
			case RelationshipPackage.MODEL_REL___DELETE_INSTANCE:
				try {
					deleteInstance();
					return null;
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * Adds a subtype of this model relationship type to the Type MID.
	 * 
	 * @param newModelRelType
	 *            The new model relationship type to be added.
	 * @param newModelRelTypeName
	 *            The name of the new model relationship type.
	 * @param constraintLanguage
	 *            The constraint language of the constraint associated with the
	 *            new model relationship type, null if no constraint is
	 *            associated.
	 * @param constraintImplementation
	 *            The constraint implementation of the constraint associated
	 *            with the new model relationship type, null if no constraint is
	 *            associated.
	 * @throws MMTFException
	 *             If the uri of the new model relationship type is already
	 *             registered in the Type MID.
	 * @generated NOT
	 */
	protected void addSubtype(ModelRel newModelRelType, String newModelRelTypeName, String constraintLanguage, String constraintImplementation) throws MMTFException {

		MultiModelLightTypeFactory.addLightModelType(newModelRelType, this, newModelRelTypeName, constraintLanguage, constraintImplementation, false);
		MultiModelTypeFactory.addModelRelType(newModelRelType, this);
	}

	/**
	 * Creates and adds a subtype of this model relationship type to the Type
	 * MID.
	 * 
	 * @param newModelRelTypeName
	 *            The name of the new model relationship type.
	 * @param isBinary
	 *            True if the new model relationship type is binary, false
	 *            otherwise.
	 * @param constraintLanguage
	 *            The constraint language of the constraint associated with the
	 *            new model relationship type, null if no constraint is
	 *            associated.
	 * @param constraintImplementation
	 *            The constraint implementation of the constraint associated
	 *            with the new model relationship type, null if no constraint is
	 *            associated.
	 * @return The created model relationship type.
	 * @throws MMTFException
	 *             If the uri of the new model relationship type is already
	 *             registered in the Type MID.
	 * @generated NOT
	 */
	public ModelRel createSubtype(String newModelRelTypeName, boolean isBinary, String constraintLanguage, String constraintImplementation) throws MMTFException {

		ModelRel newModelRelType = (isBinary) ?
			RelationshipFactory.eINSTANCE.createBinaryModelRel() :
			RelationshipFactory.eINSTANCE.createModelRel();
		addSubtype(newModelRelType, newModelRelTypeName, constraintLanguage, constraintImplementation);

		return newModelRelType;
	}

	/**
	 * Removes a model relationship type from a multimodel.
	 * 
	 * @param modelRelType
	 *            The model relationship type to be removed.
	 * @param multiModel
	 *            The multimodel that contains the model relationship type.
	 * @generated NOT
	 */
	protected void removeModelRelType(ModelRel modelRelType, MultiModel multiModel) {

		MultiModelTypeFactory.removeModelType(modelRelType, multiModel);

		// remove model type endpoints
		for (ModelEndpoint modelTypeEndpoint : modelRelType.getModelEndpoints()) {
			MultiModelTypeFactory.removeType(modelTypeEndpoint.getUri(), multiModel);
		}
		// remove link types
		for (Link linkType : modelRelType.getLinks()) {
			MultiModelTypeFactory.removeType(linkType.getUri(), multiModel);
			// remove model element type endpoints
			for (ModelElementEndpoint modelElemTypeEndpoint : linkType.getModelElemEndpoints()) {
				MultiModelTypeFactory.removeType(modelElemTypeEndpoint.getUri(), multiModel);
			}
		}
	}

	/**
	 * Deletes this model relationship type from the Type MID.
	 * 
	 * @generated NOT
	 */
	public void deleteType() throws MMTFException {

		MultiModel multiModel = MultiModelRegistry.getMultiModel(this);
		// delete the "thing"
		removeModelRelType(this, multiModel);
		// delete the subtypes of the "thing"
		for (ModelRel modelRelSubtype : MultiModelTypeHierarchy.getSubtypes(this, multiModel)) {
			removeModelRelType(modelRelSubtype, multiModel);
		}
	}

	/**
	 * Creates and possibly adds a model relationship instance of this type to
	 * an Instance MID.
	 * 
	 * @param newModelRelUri
	 *            The uri of the new model relationship, null if the new model
	 *            relationship is not in a separate file; e.g. a standalone
	 *            model relationship is in its own files, a plain model
	 *            relationship is not.
	 * @param isBinary
	 *            True if the new model relationship is binary, false otherwise.
	 * @param origin
	 *            The origin of the new model relationship.
	 * @param containerMultiModel
	 *            An Instance MID, null if the model relationship isn't going to
	 *            be added to it.
	 * @return The created model relationship.
	 * @throws MMTFException
	 *             If the uri of the new model relationship is already
	 *             registered in the Instance MID.
	 * @generated NOT
	 */
	public ModelRel createInstance(String newModelRelUri, boolean isBinary, ModelOrigin origin, MultiModel containerMultiModel) throws MMTFException {

		ModelRel newModelRel = (isBinary) ?
			RelationshipFactory.eINSTANCE.createBinaryModelRel() :
			RelationshipFactory.eINSTANCE.createModelRel();
		MultiModelInstanceFactory.addModel(newModelRel, this, newModelRelUri, origin, containerMultiModel);

		return newModelRel;
	}

	/**
	 * Deletes this model relationship from the Instance MID that contains it.
	 * 
	 * @generated NOT
	 */
	public void deleteInstance() throws MMTFException {

		MultiModelInstanceFactory.removeModel(this);
	}

} //ModelRelImpl
