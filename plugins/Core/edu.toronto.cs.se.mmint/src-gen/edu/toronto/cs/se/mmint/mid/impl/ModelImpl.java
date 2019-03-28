/**
 * Copyright (c) 2012-2019 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmint.mid.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.osgi.framework.Bundle;

import edu.toronto.cs.se.mmint.MIDTypeHierarchy;
import edu.toronto.cs.se.mmint.MIDTypeRegistry;
import edu.toronto.cs.se.mmint.MMINTConstants;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.mid.ExtendibleElement;
import edu.toronto.cs.se.mmint.mid.MID;
import edu.toronto.cs.se.mmint.mid.MIDLevel;
import edu.toronto.cs.se.mmint.mid.MIDPackage;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ModelElement;
import edu.toronto.cs.se.mmint.mid.ModelEndpoint;
import edu.toronto.cs.se.mmint.mid.ModelOrigin;
import edu.toronto.cs.se.mmint.mid.editor.Diagram;
import edu.toronto.cs.se.mmint.mid.editor.Editor;
import edu.toronto.cs.se.mmint.mid.operator.ConversionOperator;
import edu.toronto.cs.se.mmint.mid.operator.Operator;
import edu.toronto.cs.se.mmint.mid.reasoning.MIDConstraintChecker;
import edu.toronto.cs.se.mmint.mid.relationship.BinaryModelRel;
import edu.toronto.cs.se.mmint.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogCancellation;
import edu.toronto.cs.se.mmint.mid.ui.SiriusUtils;
import edu.toronto.cs.se.mmint.mid.utils.FileUtils;
import edu.toronto.cs.se.mmint.mid.utils.MIDRegistry;
import edu.toronto.cs.se.mmint.mid.utils.MIDTypeFactory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link edu.toronto.cs.se.mmint.mid.impl.ModelImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.mid.impl.ModelImpl#getFileExtension <em>File Extension</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.mid.impl.ModelImpl#getEditors <em>Editors</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.mid.impl.ModelImpl#getModelElems <em>Model Elems</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.mid.impl.ModelImpl#getConversionOperators <em>Conversion Operators</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends GenericElementImpl implements Model {
    /**
   * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getOrigin()
   * @generated
   * @ordered
   */
    protected static final ModelOrigin ORIGIN_EDEFAULT = ModelOrigin.IMPORTED;

    /**
   * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getOrigin()
   * @generated
   * @ordered
   */
    protected ModelOrigin origin = ORIGIN_EDEFAULT;

    /**
   * The default value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getFileExtension()
   * @generated
   * @ordered
   */
    protected static final String FILE_EXTENSION_EDEFAULT = null;

    /**
   * The cached value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getFileExtension()
   * @generated
   * @ordered
   */
    protected String fileExtension = FILE_EXTENSION_EDEFAULT;

    /**
   * The cached value of the '{@link #getEditors() <em>Editors</em>}' reference list.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getEditors()
   * @generated
   * @ordered
   */
    protected EList<Editor> editors;

    /**
   * The cached value of the '{@link #getModelElems() <em>Model Elems</em>}' containment reference list.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getModelElems()
   * @generated
   * @ordered
   */
    protected EList<ModelElement> modelElems;

    /**
   * The cached value of the '{@link #getConversionOperators() <em>Conversion Operators</em>}' reference list.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see #getConversionOperators()
   * @generated
   * @ordered
   */
    protected EList<ConversionOperator> conversionOperators;

    /**
     * The root model object when it is not serialized in an ECore model file (different from
     * {@link edu.toronto.cs.se.mmint.mid.operator.impl.NestingOperatorImpl#inMemoryNestedMID}, this is NOT for
     * performance reasons).
     *
     * @generated NOT
     */
    protected EObject inMemoryRootModelObj;

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    protected ModelImpl() {
    super();
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    protected EClass eStaticClass() {
    return MIDPackage.Literals.MODEL;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public ModelOrigin getOrigin() {
    return origin;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public void setOrigin(ModelOrigin newOrigin) {
    ModelOrigin oldOrigin = origin;
    origin = newOrigin == null ? ORIGIN_EDEFAULT : newOrigin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MIDPackage.MODEL__ORIGIN, oldOrigin, origin));
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public String getFileExtension() {
    return fileExtension;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public void setFileExtension(String newFileExtension) {
    String oldFileExtension = fileExtension;
    fileExtension = newFileExtension;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MIDPackage.MODEL__FILE_EXTENSION, oldFileExtension, fileExtension));
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public EList<Editor> getEditors() {
    if (editors == null) {
      editors = new EObjectResolvingEList<Editor>(Editor.class, this, MIDPackage.MODEL__EDITORS);
    }
    return editors;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public EList<ModelElement> getModelElems() {
    if (modelElems == null) {
      modelElems = new EObjectContainmentEList<ModelElement>(ModelElement.class, this, MIDPackage.MODEL__MODEL_ELEMS);
    }
    return modelElems;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public EList<ConversionOperator> getConversionOperators() {
    if (conversionOperators == null) {
      conversionOperators = new EObjectResolvingEList<ConversionOperator>(ConversionOperator.class, this, MIDPackage.MODEL__CONVERSION_OPERATORS);
    }
    return conversionOperators;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public Model getMetatype() {
    ExtendibleElement metatype = super.getMetatype();
    return (metatype == null) ? null : (Model) metatype;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
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
    public MID getMIDContainer() {
    return (MID) this.eContainer();
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case MIDPackage.MODEL__MODEL_ELEMS:
        return ((InternalEList<?>)getModelElems()).basicRemove(otherEnd, msgs);
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
      case MIDPackage.MODEL__ORIGIN:
        return getOrigin();
      case MIDPackage.MODEL__FILE_EXTENSION:
        return getFileExtension();
      case MIDPackage.MODEL__EDITORS:
        return getEditors();
      case MIDPackage.MODEL__MODEL_ELEMS:
        return getModelElems();
      case MIDPackage.MODEL__CONVERSION_OPERATORS:
        return getConversionOperators();
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
      case MIDPackage.MODEL__ORIGIN:
        setOrigin((ModelOrigin)newValue);
        return;
      case MIDPackage.MODEL__FILE_EXTENSION:
        setFileExtension((String)newValue);
        return;
      case MIDPackage.MODEL__EDITORS:
        getEditors().clear();
        getEditors().addAll((Collection<? extends Editor>)newValue);
        return;
      case MIDPackage.MODEL__MODEL_ELEMS:
        getModelElems().clear();
        getModelElems().addAll((Collection<? extends ModelElement>)newValue);
        return;
      case MIDPackage.MODEL__CONVERSION_OPERATORS:
        getConversionOperators().clear();
        getConversionOperators().addAll((Collection<? extends ConversionOperator>)newValue);
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
      case MIDPackage.MODEL__ORIGIN:
        setOrigin(ORIGIN_EDEFAULT);
        return;
      case MIDPackage.MODEL__FILE_EXTENSION:
        setFileExtension(FILE_EXTENSION_EDEFAULT);
        return;
      case MIDPackage.MODEL__EDITORS:
        getEditors().clear();
        return;
      case MIDPackage.MODEL__MODEL_ELEMS:
        getModelElems().clear();
        return;
      case MIDPackage.MODEL__CONVERSION_OPERATORS:
        getConversionOperators().clear();
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
      case MIDPackage.MODEL__ORIGIN:
        return origin != ORIGIN_EDEFAULT;
      case MIDPackage.MODEL__FILE_EXTENSION:
        return FILE_EXTENSION_EDEFAULT == null ? fileExtension != null : !FILE_EXTENSION_EDEFAULT.equals(fileExtension);
      case MIDPackage.MODEL__EDITORS:
        return editors != null && !editors.isEmpty();
      case MIDPackage.MODEL__MODEL_ELEMS:
        return modelElems != null && !modelElems.isEmpty();
      case MIDPackage.MODEL__CONVERSION_OPERATORS:
        return conversionOperators != null && !conversionOperators.isEmpty();
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
    if (baseClass == ExtendibleElement.class) {
      switch (baseOperationID) {
        case MIDPackage.EXTENDIBLE_ELEMENT___GET_METATYPE: return MIDPackage.MODEL___GET_METATYPE;
        case MIDPackage.EXTENDIBLE_ELEMENT___GET_MID_CONTAINER: return MIDPackage.MODEL___GET_MID_CONTAINER;
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
      case MIDPackage.MODEL___GET_METATYPE:
        return getMetatype();
      case MIDPackage.MODEL___GET_SUPERTYPE:
        return getSupertype();
      case MIDPackage.MODEL___GET_MID_CONTAINER:
        return getMIDContainer();
      case MIDPackage.MODEL___CREATE_SUBTYPE__STRING_BOOLEAN:
        try {
          return createSubtype((String)arguments.get(0), (Boolean)arguments.get(1));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___DELETE_TYPE:
        try {
          deleteType();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___GET_EMF_TYPE_ROOT:
        try {
          return getEMFTypeRoot();
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___OPEN_TYPE:
        try {
          openType();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___CREATE_INSTANCE__EOBJECT_STRING_MID:
        try {
          return createInstance((EObject)arguments.get(0), (String)arguments.get(1), (MID)arguments.get(2));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___CREATE_INSTANCE_EDITOR__BOOLEAN:
        try {
          return createInstanceEditor((Boolean)arguments.get(0));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___CREATE_INSTANCE_AND_EDITOR__EOBJECT_STRING_MID:
        try {
          return createInstanceAndEditor((EObject)arguments.get(0), (String)arguments.get(1), (MID)arguments.get(2));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___IMPORT_INSTANCE__STRING_MID:
        try {
          return importInstance((String)arguments.get(0), (MID)arguments.get(1));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___IMPORT_INSTANCE_AND_EDITOR__STRING_MID:
        try {
          return importInstanceAndEditor((String)arguments.get(0), (MID)arguments.get(1));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___COPY_INSTANCE__MODEL_STRING_MID:
        try {
          return copyInstance((Model)arguments.get(0), (String)arguments.get(1), (MID)arguments.get(2));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___COPY_INSTANCE_AND_EDITOR__MODEL_STRING_BOOLEAN_MID:
        try {
          return copyInstanceAndEditor((Model)arguments.get(0), (String)arguments.get(1), (Boolean)arguments.get(2), (MID)arguments.get(3));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___DELETE_INSTANCE:
        try {
          deleteInstance();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___DELETE_INSTANCE_AND_FILE:
        try {
          deleteInstanceAndFile();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___GET_EMF_INSTANCE_ROOT:
        try {
          return getEMFInstanceRoot();
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___OPEN_INSTANCE:
        try {
          openInstance();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___CREATE_WORKFLOW_INSTANCE__STRING_MID:
        try {
          return createWorkflowInstance((String)arguments.get(0), (MID)arguments.get(1));
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
      case MIDPackage.MODEL___DELETE_WORKFLOW_INSTANCE:
        try {
          deleteWorkflowInstance();
          return null;
        }
        catch (Throwable throwable) {
          throw new InvocationTargetException(throwable);
        }
    }
    return super.eInvoke(operationID, arguments);
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    @Override
    public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (origin: ");
    result.append(origin);
    result.append(", fileExtension: ");
    result.append(fileExtension);
    result.append(')');
    return result.toString();
  }

    /**
     * Adds a subtype of this model type to the Type MID, together with an editor type for it.
     *
     * @param newModelType
     *            The new model type to be added.
     * @param newModelTypeName
     *            The name of the new model type.
     * @param isMetamodelExtension
     *            True if the new model type is extending the supertype's metamodel, false otherwise.
     * @throws MMINTException
     *             If the uri of the new model type is already registered in the Type MID.
     * @generated NOT
     */
    protected void addSubtype(Model newModelType, String newModelTypeName, boolean isMetamodelExtension) throws MMINTException {

        MID typeMID = this.getMIDContainer();
        super.addSubtype(newModelType, this, null, newModelTypeName);
        MIDTypeFactory.addModelType(newModelType, typeMID);
        newModelType.setOrigin(ModelOrigin.CREATED);

        if (isMetamodelExtension) {
            try {
                String newMetamodelUri = MIDTypeRegistry.getExtendedMetamodelPath(newModelType);
                if (newMetamodelUri == null) { // create new metamodel file, else we're just recreating this subtype at startup
                    EPackage newEPackage = EcoreFactory.eINSTANCE.createEPackage();
                    newEPackage.setName(newModelTypeName.toLowerCase());
                    newEPackage.setNsPrefix(newModelTypeName.toLowerCase());
                    newEPackage.setNsURI(newModelType.getUri());
                    EAnnotation newEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
                    newEAnnotation.setSource(EcorePackage.eNS_URI);
                    EMap<String, String> newEAnnotationDetails = newEAnnotation.getDetails();
                    newEAnnotationDetails.put(MIDTypeFactory.ECORE_INVOCATION_DELEGATE, MIDTypeFactory.ECORE_PIVOT_URI);
                    newEAnnotationDetails.put(MIDTypeFactory.ECORE_SETTING_DELEGATE, MIDTypeFactory.ECORE_PIVOT_URI);
                    newEAnnotationDetails.put(MIDTypeFactory.ECORE_VALIDATION_DELEGATE, MIDTypeFactory.ECORE_PIVOT_URI);
                    newEPackage.getEAnnotations().add(newEAnnotation);
                    EClass newRootEClass = EcoreFactory.eINSTANCE.createEClass();
                    newRootEClass.setName(newModelTypeName);
                    if (!MIDTypeHierarchy.isRootType(this)) {
                        EClass rootEClass = (EClass) this.getEMFTypeRoot().getEClassifiers().get(0);
                        newRootEClass.getESuperTypes().add(rootEClass);
                    }
                    newEPackage.getEClassifiers().add(newRootEClass);
                    newMetamodelUri = newModelTypeName + "." + EcorePackage.eNAME;
                    FileUtils.writeModelFileInState(newEPackage, newMetamodelUri);
                }
                newModelType.setFileExtension(MIDTypeFactory.ECORE_REFLECTIVE_FILE_EXTENSION);
            }
            catch (Exception e) {
                MMINTException.print(IStatus.WARNING, "Error creating extended metamodel file, fallback to no extension", e);
                newModelType.setFileExtension(this.getFileExtension());
                isMetamodelExtension = false;
            }
        }
        else {
            newModelType.setFileExtension(this.getFileExtension());
        }

        // create editors
        String newEditorTypeFragmentUri = newModelType.getName(), newEditorTypeName, newModelTypeUri = newModelType.getUri(), editorId, wizardId, wizardDialogClassName;
        for (Editor editorType : this.getEditors()) {
            if (isMetamodelExtension) {
                if (editorType instanceof Diagram) {
                    continue;
                }
                newEditorTypeName = MMINTConstants.ROOT_EDITOR_NAME;
                editorId = MMINTConstants.ROOT_EDITOR_ID;
                wizardId = null;
                wizardDialogClassName = null;
            }
            else {
                newEditorTypeName = editorType.getName();
                editorId = editorType.getId();
                wizardId = editorType.getWizardId();
                wizardDialogClassName = editorType.getWizardDialogClass();
            }
            try {
                //TODO MMINT[EDITOR] a new editor is created instead of attaching existing ones
                //TODO MMINT[EDITOR] because I couldn't find a way then from an editor to understand which model was being created
                Editor newEditorType = editorType.createSubtype(newEditorTypeFragmentUri, newEditorTypeName, newModelTypeUri, editorId, wizardId, wizardDialogClassName);
                MIDTypeFactory.addModelTypeEditor(newEditorType, newModelType);
                if (isMetamodelExtension) { // reflective editor only
                    newEditorType.getFileExtensions().clear();
                    newEditorType.getFileExtensions().add(MIDTypeFactory.ECORE_REFLECTIVE_FILE_EXTENSION);
                    break;
                }
            }
            catch (MMINTException e) {
                // models created through this editor will have the supermodel as static type
                MIDTypeFactory.addModelTypeEditor(editorType, newModelType);
            }
        }
    }

    /**
     * @generated NOT
     */
    @Override
    public Model createSubtype(String newModelTypeName, boolean isMetamodelExtension) throws MMINTException {

        MMINTException.mustBeType(this);

        Model newModelType = super.createThisEClass();
        this.addSubtype(newModelType, newModelTypeName, isMetamodelExtension);

        return newModelType;
    }

    /**
     * @generated NOT
     */
    @Override
    public void deleteType() throws MMINTException {

        MMINTException.mustBeType(this);

        MID typeMID = this.getMIDContainer();
        // delete the "thing"
        for (ModelElement modelElemType : this.getModelElems()) {
            super.delete(modelElemType.getUri(), typeMID);
        }
        List<Editor> delEditorTypes = new ArrayList<>(this.getEditors());
        for (Editor delEditorType : delEditorTypes) {
            delEditorType.deleteType();
        }
        super.delete();
        typeMID.getModels().remove(this);
        String metamodelUri = MIDTypeRegistry.getExtendedMetamodelPath(this);
        if (metamodelUri != null) {
            FileUtils.deleteFile(metamodelUri, false);
        }
        // delete operator types that use this model type
        List<Operator> delOperatorTypes = new ArrayList<>();
        for (Operator operatorType : typeMID.getOperators()) {
            for (ModelEndpoint inputModelTypeEndpoint : operatorType.getInputs()) {
                if (inputModelTypeEndpoint.getTargetUri().equals(this.getUri()) && !delOperatorTypes.contains(operatorType)) {
                    delOperatorTypes.add(operatorType);
                }
            }
            for (ModelEndpoint outputModelTypeEndpoint : operatorType.getOutputs()) {
                if (outputModelTypeEndpoint.getTargetUri().equals(this.getUri()) && !delOperatorTypes.contains(operatorType)) {
                    delOperatorTypes.add(operatorType);
                }
            }
        }
        for (Operator operatorType : delOperatorTypes) {
            operatorType.deleteType();
        }
        // delete model relationship types that use this model type
        List<ModelRel> delModelRelTypes = new ArrayList<>();
        List<ModelEndpoint> delModelTypeEndpoints = new ArrayList<>();
        for (ModelRel modelRelType : typeMID.getModelRels()) {
            for (ModelEndpoint modelTypeEndpoint : modelRelType.getModelEndpoints()) {
                if (modelTypeEndpoint.getTargetUri().equals(this.getUri())) {
                    if (modelRelType instanceof BinaryModelRel) {
                        if (!delModelRelTypes.contains(modelRelType)) {
                            delModelRelTypes.add(modelRelType);
                        }
                    }
                    else {
                        delModelTypeEndpoints.add(modelTypeEndpoint);
                    }
                }
            }
        }
        for (ModelEndpoint modelTypeEndpoint : delModelTypeEndpoints) {
            modelTypeEndpoint.deleteType(true);
        }
        for (ModelRel modelRelType : delModelRelTypes) {
            modelRelType.deleteType();
        }
        // delete the subtypes of the "thing"
        for (Model modelSubtype : MIDTypeHierarchy.getDirectSubtypes(this, typeMID)) {
            modelSubtype.deleteType();
        }
    }

    /**
     * @generated NOT
     */
    @Override
    public EPackage getEMFTypeRoot() throws MMINTException {

        MMINTException.mustBeType(this);

        EPackage rootModelTypeObj;
        if (!this.isDynamic()) { // get package from registry
            rootModelTypeObj = EPackage.Registry.INSTANCE.getEPackage(this.getUri());
        }
        else {
            String metamodelUri = MIDTypeRegistry.getExtendedMetamodelPath(this);
            if (metamodelUri != null) { // get package from metamodel file
                try {
                    rootModelTypeObj = (EPackage) FileUtils.readModelFile(metamodelUri, false);
                }
                catch (Exception e) {
                    throw new MMINTException("Error accessing the metamodel file for model type" + this.getUri(), e);
                }
            }
            else { // climb up light types
                rootModelTypeObj = this.getSupertype().getEMFTypeRoot();
            }
        }

        return rootModelTypeObj;
    }

    /**
     * @generated NOT
     */
    @Override
    public void openType() throws Exception {

        MMINTException.mustBeType(this);

        Model modelType = this;
        List<String> metamodelPaths = new ArrayList<>();
        /*TODO MMINT[EDITOR] Fix:
         * a) references to inherited metamodels not good in binary eclipse
         * b) open UML
         * c) abstract model types (related to a, need to climb)
         */
        while (true) {
            if (modelType.isDynamic()) {
                String metamodelPath = MIDTypeRegistry.getExtendedMetamodelPath(modelType);
                if (metamodelPath != null) { // get metamodel file from mmint state area
                    metamodelPaths.add(metamodelPath);
                    break;
                }
            }
            else { // get metamodel files from bundle
                Bundle bundle = MIDTypeRegistry.getTypeBundle(modelType.getUri());
                Enumeration<URL> metamodels = bundle.findEntries("/", "*" + MMINTConstants.MODEL_FILEEXTENSION_SEPARATOR + EcorePackage.eNAME, true);
                List<String> tempMetamodelPaths = new ArrayList<>();
                String tempMetamodelPath = null;
                while (metamodels.hasMoreElements()) {
                    String metamodelPath = FileLocator.toFileURL(metamodels.nextElement()).getFile();
                    // heuristic to open just one metamodel with many in the same bundle
                    if (FileUtils.getFileNameFromPath(metamodelPath).equalsIgnoreCase(modelType.getName())) {
                        tempMetamodelPath = metamodelPath;
                        break;
                    }
                    tempMetamodelPaths.add(metamodelPath);
                }
                if (tempMetamodelPath != null) {
                    metamodelPaths.add(tempMetamodelPath);
                }
                else {
                    metamodelPaths.addAll(tempMetamodelPaths);
                }
                break;
            }
            // climb up light types
            modelType = modelType.getSupertype();
        }

        // open editors
        Model ecoreModelType = MIDTypeRegistry.getType(EcorePackage.eNS_URI);
        Editor ecoreEditorType = ecoreModelType.getEditors().get(0);
        for (String metamodelPath : metamodelPaths) {
            FileUtils.openEclipseEditor(metamodelPath, ecoreEditorType.getId(), false);
            //TODO MMINT[ECORE] Try to open ecore diagram
            //String metamodelDiagramPath = metamodelPath + GMFDiagramUtils.DIAGRAM_SUFFIX;
        }
    }

    /**
     * Adds an instance of this model type to an Instance or Workflow MID, or simply adds additional info to the model
     * instance.
     *
     * @param newModel
     *            The new model to be added.
     * @param newModelId
     *            The id of the new model.
     * @param newModelName
     *            The name of the new model.
     * @param origin
     *            The origin of the new model.
     * @param fileExtension
     *            The file extension of the new model.
     * @param midLevel
     *            The kind of MID (Instance or Workflow) that could contain the new model, regardless of whether it is
     *            or isn't going to be contained in one.
     * @param instanceMID
     *            An Instance or Workflow MID, null if the model isn't going to be contained in one.
     * @throws MMINTException
     *             If the id of the new model is already registered in the MID.
     * @generated NOT
     */
    protected void addInstance(@NonNull Model newModel, @NonNull String newModelId, @NonNull String newModelName, @NonNull ModelOrigin origin, @NonNull String fileExtension, @NonNull MIDLevel midLevel, @Nullable MID instanceMID) throws MMINTException {

        if (instanceMID == null) {
            super.addBasicInstance(newModel, newModelId, newModelName, midLevel);
        }
        else {
            super.addInstance(newModel, newModelId, newModelName, instanceMID);
            instanceMID.getModels().add(newModel);
        }
        newModel.setOrigin(origin);
        newModel.setFileExtension(fileExtension);
    }

    /**
     * @generated NOT
     */
    @Override
    public Model createInstance(EObject rootModelObj, String newModelPath, MID instanceMID) throws MMINTException, IOException {

        MMINTException.mustBeType(this);
        if (this.isAbstract()) {
            throw new MMINTException("Can't instanciate an abstract model type");
        }

        Model newModel = super.createThisEClass();
        this.addInstance(
            newModel,
            newModelPath,
            FileUtils.getFileNameFromPath(newModelPath),
            ModelOrigin.CREATED,
            FileUtils.getFileExtensionFromPath(newModelPath),
            MIDLevel.INSTANCES,
            instanceMID);
        /*TODO MMINT[OPERATOR] Separate using instanceMID = null from creating the model file,
         *                     using a flag set by invokers of startInstance
         */
        if (rootModelObj != null) {
            if (instanceMID == null) {
                /* TODO MMINT[OPERATOR] Could we put it in memory when serialized too as optimization, or will it consume too much memory?
                 * (find the cases where it really stays in memory, which shouldn't happen when working with a MID diagram, only in a workflow)
                 * Also, should cache it when using getEMFInstanceRoot() in that case.
                 * This thing (having to copy it) is tricky because one never modifies a model in place with an operator (only with a MID diagram) but a new model is always created
                 */
                ((ModelImpl) newModel).inMemoryRootModelObj = rootModelObj;
            }
            else {
                FileUtils.writeModelFile(rootModelObj, newModelPath, true);
            }
        }

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public Editor createInstanceEditor(boolean createEditorFile) throws MMINTException {

        MMINTException.mustBeInstance(this);

        MID instanceMID = this.getMIDContainer();
        Editor newEditor = null;
        MMINTException lastException = null;
        List<Editor> sortedEditors = new ArrayList<>(this.getMetatype().getEditors());
        sortedEditors.sort(new Comparator<Editor>() {
            @Override
            public int compare(Editor e1, Editor e2) {
                // diagrams before editors, alphabetical within same type
                boolean d1 = e1 instanceof Diagram;
                boolean d2 = e2 instanceof Diagram;
                if (d1 == d2) {
                    return e1.getId().compareTo(e2.getId());
                }
                if (d1) {
                    return -1;
                }
                return 1;
            }
        });
        for (Editor editorType : sortedEditors) {
            try {
                newEditor = editorType.createInstance(this.getUri(), createEditorFile, instanceMID);
                break;
            }
            catch (MIDDialogCancellation e) {
                // bubble up to signal user cancellation
                throw e;
            }
            catch (MMINTException e) {
                lastException = e;
                continue;
            }
        }
        if (newEditor == null) {
            throw new MMINTException("No editor type found", lastException);
        }
        this.getEditors().add(newEditor);

        return newEditor;
    }

    /**
     * @generated NOT
     */
    @Override
    public Model createInstanceAndEditor(EObject rootModelObj, String newModelPath, MID instanceMID) throws MMINTException, IOException {

        Model newModel = this.createInstance(rootModelObj, newModelPath, instanceMID);
        if (instanceMID != null) {
            //TODO MMINT[EDITOR] Add an optional way to select an explicit editor, to avoid creating unwanted diagrams
            newModel.createInstanceEditor(true);
        }

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public Model importInstance(String modelPath, MID instanceMID) throws MMINTException {

        MMINTException.mustBeType(this);
        if (!FileUtils.isFile(modelPath, true)) {
            throw new MMINTException("Missing ECore model file " + modelPath);
        }

        Model newModel = super.createThisEClass();
        this.addInstance(
            newModel,
            modelPath,
            FileUtils.getFileNameFromPath(modelPath),
            ModelOrigin.IMPORTED,
            FileUtils.getFileExtensionFromPath(modelPath),
            MIDLevel.INSTANCES,
            instanceMID);

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public Model importInstanceAndEditor(String modelPath, MID instanceMID) throws MMINTException {

        Model newModel = this.importInstance(modelPath, instanceMID);
        if (instanceMID != null) {
            newModel.createInstanceEditor(false);
        }

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public Model copyInstance(Model origModel, String newModelName, MID instanceMID) throws MMINTException, IOException {

        MMINTException.mustBeType(this);

        String newModelPath = FileUtils.replaceFileNameInPath(origModel.getUri(), newModelName);

        return this.createInstance(origModel.getEMFInstanceRoot(), newModelPath, instanceMID);
    }

    /**
     * @generated NOT
     */
    @Override
    public Model copyInstanceAndEditor(Model origModel, String newModelName, boolean copyDiagram, MID instanceMID) throws MMINTException, IOException {

        //TODO MMINT[SIRIUS] Make this work when copying a sirius representation
        Model newModel = this.copyInstance(origModel, newModelName, instanceMID);
        // copy diagrams
        if (copyDiagram && instanceMID != null) {
            for (Editor oldEditor : origModel.getEditors()) {
                if (oldEditor.getUri().equals(origModel.getUri())) {
                    continue;
                }
                try {
                    FileUtils.copyTextFileAndReplaceText(
                        oldEditor.getUri(),
                        FileUtils.replaceFileNameInPath(oldEditor.getUri(), newModelName),
                        origModel.getName() + MMINTConstants.MODEL_FILEEXTENSION_SEPARATOR,
                        newModelName + MMINTConstants.MODEL_FILEEXTENSION_SEPARATOR,
                        true);
                } catch (Exception e) {
                    MMINTException.print(IStatus.WARNING, "Error copying diagram file, skipping it", e);
                    continue;
                }
                //TODO MMINT[UML] add support for notation extra file (e.g. in UML)
            }
        }
        newModel.createInstanceEditor(false);

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public boolean validateInstanceType(ExtendibleElement type) throws MMINTException {

        MMINTException.mustBeInstance(this);
        MMINTException.mustBeType(type);

        return MIDConstraintChecker.checkModelConstraint(this, type.getConstraint());
    }

    /**
     * @generated NOT
     */
    @Override
    public boolean validateInstance() throws MMINTException {

        MMINTException.mustBeInstance(this);

        boolean validates = MIDConstraintChecker.checkModelConstraint(this, this.getConstraint());

        return validates && this.validateInstanceType(this.getMetatype());
    }

    /**
     * Deletes this model instance from an Instance or Workflow MID.
     *
     * @param instanceMID
     *            The Instance or Workflow MID that contains the operator.
     *
     * @generated NOT
     */
    protected void deleteInstance(MID instanceMID) {

        instanceMID.getModels().remove(this);
    }

    /**
     * @generated NOT
     */
    @Override
    public void deleteInstance() throws MMINTException {

        MMINTException.mustBeInstance(this);

        MID instanceMID = this.getMIDContainer();
        // delete model elements
        for (ModelElement modelElem : this.getModelElems()) {
            super.delete(modelElem.getUri(), instanceMID);
        }
        // delete editors
        for (Editor editor : this.getEditors()) {
            editor.deleteInstance();
        }
        super.delete();
        this.deleteInstance(instanceMID);
        // delete operators that use this model
        Set<Operator> delOperators = MIDRegistry.getInputOutputOperators(this, instanceMID);
        for (Operator delOperator : delOperators) {
            delOperator.deleteInstance();
        }
        // delete model relationships that use this model
        Set<BinaryModelRel> delModelRels = MIDRegistry.getConnectedBinaryModelRels(this, instanceMID);
        for (BinaryModelRel delModelRel : delModelRels) {
            delModelRel.deleteInstance();
        }
        Set<ModelEndpoint> delModelEndpoints = MIDRegistry.getConnectedNaryModelRelEndpoints(this, instanceMID);
        for (ModelEndpoint delModelEndpoint : delModelEndpoints) {
            delModelEndpoint.deleteInstance(true);
        }
    }

    /**
     * @generated NOT
     */
    @Override
    public void deleteInstanceAndFile() throws MMINTException {

        MMINTException.mustBeInstance(this);

        /*TODO MMINT[MODEL] Deleting assumes that all models are unique, e.g. there are no copies, and it can't detect when a model exists in two mids
         * The following proper mid deletion breaks it
        if (this.getMetatype() == MIDTypeRegistry.getMIDModelType()) { // the model is a MID
            MID nestedMID = (MID) this.getEMFInstanceRoot();
            for (Model nestedModel : new ArrayList<>(nestedMID.getModels())) {
                nestedModel.deleteInstanceAndFile();
            }
        }
         */
        for (Editor editor : this.getEditors()) {
            if (editor.getFileExtensions().get(0).equals(SiriusUtil.SESSION_RESOURCE_EXTENSION)) { // Sirius
                SiriusUtils.deleteRepresentation((Diagram) editor);
            }
            else {
                FileUtils.deleteFile(editor.getUri(), true);
            }
        }
        FileUtils.deleteFile(this.getUri(), true);
        this.deleteInstance();
    }

    /**
     * @generated NOT
     */
    @Override
    public EObject getEMFInstanceRoot() throws MMINTException {

        MMINTException.mustBeInstance(this);

        if (this.inMemoryRootModelObj != null) {
            return EcoreUtil.copy(this.inMemoryRootModelObj);
        }

        try {
            EObject rootModelObj = FileUtils.readModelFile(this.getUri(), true);
            return rootModelObj;
        }
        catch (Exception e) {
            throw new MMINTException("Error accessing the model file for model " + this.getUri(), e);
        }
    }

    /**
     * @generated NOT
     */
    @Override
    public void openInstance() throws Exception {

        MMINTException.mustBeInstance(this);

        Editor editor = this.getEditors().get(0);
        FileUtils.openEclipseEditor(editor.getUri(), editor.getId(), true);
    }

    /**
     * @generated NOT
     */
    @Override
    public Model createWorkflowInstance(String newModelId, MID workflowMID) throws MMINTException {

        MMINTException.mustBeType(this);

        Model newModel = super.createThisEClass();
        this.addInstance(
            newModel,
            newModelId,
            newModelId,
            ModelOrigin.CREATED,
            this.getFileExtension(),
            MIDLevel.WORKFLOWS,
            workflowMID);

        return newModel;
    }

    /**
     * @generated NOT
     */
    @Override
    public void deleteWorkflowInstance() throws MMINTException {

        MMINTException.mustBeWorkflow(this);

        MID workflowMID = this.getMIDContainer();
        super.delete();
        this.deleteInstance(workflowMID);
        workflowMID.getModels().remove(this);
        // delete operators that use this model
        Set<Operator> delOperators = MIDRegistry.getInputOutputOperators(this, workflowMID);
        for (Operator delOperator : delOperators) {
            delOperator.deleteWorkflowInstance();
        }
        // delete model relationships that use this model
        Set<BinaryModelRel> delModelRels = MIDRegistry.getConnectedBinaryModelRels(this, workflowMID);
        for (BinaryModelRel delModelRel : delModelRels) {
            delModelRel.deleteWorkflowInstance();
        }
        Set<ModelEndpoint> delModelEndpoints = MIDRegistry.getConnectedNaryModelRelEndpoints(this, workflowMID);
        for (ModelEndpoint delModelEndpoint : delModelEndpoints) {
            delModelEndpoint.deleteWorkflowInstance();
        }
    }

} //ModelImpl
