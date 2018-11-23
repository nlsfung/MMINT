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
package edu.toronto.cs.se.modelepedia.safetycase.util;

import edu.toronto.cs.se.modelepedia.safetycase.*;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage
 * @generated
 */
public class SafetyCaseValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final SafetyCaseValidator INSTANCE = new SafetyCaseValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "edu.toronto.cs.se.modelepedia.safetycase";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SafetyCaseValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return SafetyCasePackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case SafetyCasePackage.SAFETY_CASE:
				return validateSafetyCase((SafetyCase)value, diagnostics, context);
			case SafetyCasePackage.ARGUMENT_ELEMENT:
				return validateArgumentElement((ArgumentElement)value, diagnostics, context);
			case SafetyCasePackage.STATEFUL_ELEMENT:
				return validateStatefulElement((StatefulElement)value, diagnostics, context);
			case SafetyCasePackage.ASI_LFUL_ELEMENT:
				return validateASILfulElement((ASILfulElement)value, diagnostics, context);
			case SafetyCasePackage.CORE_ELEMENT:
				return validateCoreElement((CoreElement)value, diagnostics, context);
			case SafetyCasePackage.DECOMPOSABLE_CORE_ELEMENT:
				return validateDecomposableCoreElement((DecomposableCoreElement)value, diagnostics, context);
			case SafetyCasePackage.CONTEXTUAL_ELEMENT:
				return validateContextualElement((ContextualElement)value, diagnostics, context);
			case SafetyCasePackage.SUPPORTED_BY:
				return validateSupportedBy((SupportedBy)value, diagnostics, context);
			case SafetyCasePackage.IN_CONTEXT_OF:
				return validateInContextOf((InContextOf)value, diagnostics, context);
			case SafetyCasePackage.GOAL:
				return validateGoal((Goal)value, diagnostics, context);
			case SafetyCasePackage.BASIC_GOAL:
				return validateBasicGoal((BasicGoal)value, diagnostics, context);
			case SafetyCasePackage.INDEPENDENCE_GOAL:
				return validateIndependenceGoal((IndependenceGoal)value, diagnostics, context);
			case SafetyCasePackage.STRATEGY:
				return validateStrategy((Strategy)value, diagnostics, context);
			case SafetyCasePackage.BASIC_STRATEGY:
				return validateBasicStrategy((BasicStrategy)value, diagnostics, context);
			case SafetyCasePackage.ASIL_DECOMPOSITION_STRATEGY:
				return validateASILDecompositionStrategy((ASILDecompositionStrategy)value, diagnostics, context);
			case SafetyCasePackage.SOLUTION:
				return validateSolution((Solution)value, diagnostics, context);
			case SafetyCasePackage.CONTEXT:
				return validateContext((Context)value, diagnostics, context);
			case SafetyCasePackage.JUSTIFICATION:
				return validateJustification((Justification)value, diagnostics, context);
			case SafetyCasePackage.ASSUMPTION:
				return validateAssumption((Assumption)value, diagnostics, context);
			case SafetyCasePackage.ASIL:
				return validateASIL((ASIL)value, diagnostics, context);
			case SafetyCasePackage.IMPACT_ANNOTATION:
				return validateImpactAnnotation((ImpactAnnotation)value, diagnostics, context);
			case SafetyCasePackage.ASIL_LEVEL:
				return validateASILLevel((ASILLevel)value, diagnostics, context);
			case SafetyCasePackage.VALIDITY_VALUE:
				return validateValidityValue((ValidityValue)value, diagnostics, context);
			case SafetyCasePackage.IMPACT_TYPE:
				return validateImpactType((ImpactType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSafetyCase(SafetyCase safetyCase, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(safetyCase, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(safetyCase, diagnostics, context);
		if (result || diagnostics != null) result &= validateSafetyCase_SingleRoot(safetyCase, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the SingleRoot constraint of '<em>Safety Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String SAFETY_CASE__SINGLE_ROOT__EEXPRESSION = "CoreElement.allInstances() -> \n" +
		"\t\t\tselect(d | d.supports.conclusion -> isEmpty()) -> size() = 1";

	/**
	 * Validates the SingleRoot constraint of '<em>Safety Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSafetyCase_SingleRoot(SafetyCase safetyCase, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.SAFETY_CASE,
				 safetyCase,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "SingleRoot",
				 SAFETY_CASE__SINGLE_ROOT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArgumentElement(ArgumentElement argumentElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(argumentElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStatefulElement(StatefulElement statefulElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(statefulElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILfulElement(ASILfulElement asiLfulElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(asiLfulElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCoreElement(CoreElement coreElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(coreElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(coreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(coreElement, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the GoalRoot constraint of '<em>Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String CORE_ELEMENT__GOAL_ROOT__EEXPRESSION = "self.supports.conclusion -> isEmpty() implies self.oclIsTypeOf(BasicGoal)";

	/**
	 * Validates the GoalRoot constraint of '<em>Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCoreElement_GoalRoot(CoreElement coreElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.CORE_ELEMENT,
				 coreElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "GoalRoot",
				 CORE_ELEMENT__GOAL_ROOT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecomposableCoreElement(DecomposableCoreElement decomposableCoreElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(decomposableCoreElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(decomposableCoreElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(decomposableCoreElement, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the SupportCycle constraint of '<em>Decomposable Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DECOMPOSABLE_CORE_ELEMENT__SUPPORT_CYCLE__EEXPRESSION = "self.supportedBy.premise -> closure(p | if p.oclIsKindOf(DecomposableCoreElement) then \n" +
		"\t\t\tp.oclAsType(DecomposableCoreElement).supportedBy.premise else \n" +
		"\t\t\tp.oclAsSet() endif) -> excludes(self)";

	/**
	 * Validates the SupportCycle constraint of '<em>Decomposable Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecomposableCoreElement_SupportCycle(DecomposableCoreElement decomposableCoreElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.DECOMPOSABLE_CORE_ELEMENT,
				 decomposableCoreElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "SupportCycle",
				 DECOMPOSABLE_CORE_ELEMENT__SUPPORT_CYCLE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the NonDecomposableLeaves constraint of '<em>Decomposable Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DECOMPOSABLE_CORE_ELEMENT__NON_DECOMPOSABLE_LEAVES__EEXPRESSION = "self.supportedBy.premise -> size() > 0 and self.supportedBy.premise -> excludes(null)";

	/**
	 * Validates the NonDecomposableLeaves constraint of '<em>Decomposable Core Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecomposableCoreElement_NonDecomposableLeaves(DecomposableCoreElement decomposableCoreElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.DECOMPOSABLE_CORE_ELEMENT,
				 decomposableCoreElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "NonDecomposableLeaves",
				 DECOMPOSABLE_CORE_ELEMENT__NON_DECOMPOSABLE_LEAVES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextualElement(ContextualElement contextualElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(contextualElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementSupporter(contextualElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementContext(contextualElement, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the ContextualElementSupporter constraint of '<em>Contextual Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String CONTEXTUAL_ELEMENT__CONTEXTUAL_ELEMENT_SUPPORTER__EEXPRESSION = "self.oclAsType(DecomposableCoreElement).oclIsInvalid()";

	/**
	 * Validates the ContextualElementSupporter constraint of '<em>Contextual Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextualElement_ContextualElementSupporter(ContextualElement contextualElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.CONTEXTUAL_ELEMENT,
				 contextualElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ContextualElementSupporter",
				 CONTEXTUAL_ELEMENT__CONTEXTUAL_ELEMENT_SUPPORTER__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ContextualElementContext constraint of '<em>Contextual Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String CONTEXTUAL_ELEMENT__CONTEXTUAL_ELEMENT_CONTEXT__EEXPRESSION = "self.oclAsType(DecomposableCoreElement).oclIsInvalid()";

	/**
	 * Validates the ContextualElementContext constraint of '<em>Contextual Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextualElement_ContextualElementContext(ContextualElement contextualElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.CONTEXTUAL_ELEMENT,
				 contextualElement,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ContextualElementContext",
				 CONTEXTUAL_ELEMENT__CONTEXTUAL_ELEMENT_CONTEXT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSupportedBy(SupportedBy supportedBy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(supportedBy, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInContextOf(InContextOf inContextOf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(inContextOf, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(goal, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalSupporter(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalContext(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_ASILInheritance(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_StateValidityInheritance(goal, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the GoalSupporter constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GOAL__GOAL_SUPPORTER__EEXPRESSION = "self.supportedBy -> forAll(s | s.premise.oclIsKindOf(Goal) or s.premise.oclIsKindOf(Strategy) or s.premise.oclIsKindOf(Solution))";

	/**
	 * Validates the GoalSupporter constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal_GoalSupporter(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.GOAL,
				 goal,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "GoalSupporter",
				 GOAL__GOAL_SUPPORTER__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the GoalContext constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GOAL__GOAL_CONTEXT__EEXPRESSION = "self.inContextOf.context -> forAll(c | c.oclIsKindOf(Context) or c.oclIsKindOf(Assumption) or c.oclIsKindOf(Justification))";

	/**
	 * Validates the GoalContext constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal_GoalContext(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.GOAL,
				 goal,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "GoalContext",
				 GOAL__GOAL_CONTEXT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ASILInheritance constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GOAL__ASIL_INHERITANCE__EEXPRESSION = "let directParents : Set(Goal) = self.supports.conclusion -> select(d | d.oclIsKindOf(Goal)).oclAsType(Goal) -> asSet(), \n" +
		"\t\t\tindirectParents : Set(Goal) = self.supports.conclusion -> select(d | d.oclIsTypeOf(BasicStrategy)).supports.conclusion -> select(d | d.oclIsKindOf(Goal)).oclAsType(Goal) -> asSet() \n" +
		"\t\t\tin indirectParents -> union(directParents) -> forAll(g | if g.asil = null then true else g.asil.value = self.asil.value endif)";

	/**
	 * Validates the ASILInheritance constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal_ASILInheritance(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.GOAL,
				 goal,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ASILInheritance",
				 GOAL__ASIL_INHERITANCE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the StateValidityInheritance constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GOAL__STATE_VALIDITY_INHERITANCE__EEXPRESSION = "self.stateValidity = ValidityValue::Valid implies \n" +
		"\t\t\tlet directChildren : Set(StatefulElement) = self.supportedBy.premise -> select(d | d.oclIsKindOf(StatefulElement)).oclAsType(StatefulElement) -> asSet(), \n" +
		"\t\t\t\tindirectChildren : Set(StatefulElement) = self.supportedBy.premise -> select(d | d.oclIsKindOf(Strategy)).oclAsType(Strategy).supportedBy.premise.oclAsType(StatefulElement) -> asSet() \n" +
		"\t\t\tin indirectChildren -> union(directChildren) -> forAll(g | g.stateValidity = ValidityValue::Valid)";

	/**
	 * Validates the StateValidityInheritance constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal_StateValidityInheritance(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.GOAL,
				 goal,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "StateValidityInheritance",
				 GOAL__STATE_VALIDITY_INHERITANCE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBasicGoal(BasicGoal basicGoal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(basicGoal, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalSupporter(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalContext(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_ASILInheritance(basicGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_StateValidityInheritance(basicGoal, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIndependenceGoal(IndependenceGoal independenceGoal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(independenceGoal, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalSupporter(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_GoalContext(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_ASILInheritance(independenceGoal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_StateValidityInheritance(independenceGoal, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStrategy(Strategy strategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(strategy, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategySupporter(strategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategyContext(strategy, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the StrategySupporter constraint of '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String STRATEGY__STRATEGY_SUPPORTER__EEXPRESSION = "self.supportedBy -> forAll(s | s.premise.oclIsKindOf(Goal) or s.premise.oclIsKindOf(Solution))";

	/**
	 * Validates the StrategySupporter constraint of '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStrategy_StrategySupporter(Strategy strategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.STRATEGY,
				 strategy,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "StrategySupporter",
				 STRATEGY__STRATEGY_SUPPORTER__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the StrategyContext constraint of '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String STRATEGY__STRATEGY_CONTEXT__EEXPRESSION = "self.inContextOf.context -> forAll(c | c.oclIsKindOf(Context) or c.oclIsKindOf(Assumption) or c.oclIsKindOf(Justification))";

	/**
	 * Validates the StrategyContext constraint of '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStrategy_StrategyContext(Strategy strategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.STRATEGY,
				 strategy,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "StrategyContext",
				 STRATEGY__STRATEGY_CONTEXT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBasicStrategy(BasicStrategy basicStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(basicStrategy, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategySupporter(basicStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategyContext(basicStrategy, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILDecompositionStrategy(ASILDecompositionStrategy asilDecompositionStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(asilDecompositionStrategy, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_SupportCycle(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecomposableCoreElement_NonDecomposableLeaves(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategySupporter(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateStrategy_StrategyContext(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateASILDecompositionStrategy_ASILDecompositionIndependence(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateASILDecompositionStrategy_ASILDecompositionComponents(asilDecompositionStrategy, diagnostics, context);
		if (result || diagnostics != null) result &= validateASILDecompositionStrategy_ASILDescendants(asilDecompositionStrategy, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the ASILDecompositionIndependence constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String ASIL_DECOMPOSITION_STRATEGY__ASIL_DECOMPOSITION_INDEPENDENCE__EEXPRESSION = "self.supportedBy.premise -> selectByType(IndependenceGoal) -> size() = 1";

	/**
	 * Validates the ASILDecompositionIndependence constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILDecompositionStrategy_ASILDecompositionIndependence(ASILDecompositionStrategy asilDecompositionStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.ASIL_DECOMPOSITION_STRATEGY,
				 asilDecompositionStrategy,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ASILDecompositionIndependence",
				 ASIL_DECOMPOSITION_STRATEGY__ASIL_DECOMPOSITION_INDEPENDENCE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ASILDecompositionComponents constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String ASIL_DECOMPOSITION_STRATEGY__ASIL_DECOMPOSITION_COMPONENTS__EEXPRESSION = "self.supportedBy.premise -> selectByType(BasicGoal) -> size() = 2";

	/**
	 * Validates the ASILDecompositionComponents constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILDecompositionStrategy_ASILDecompositionComponents(ASILDecompositionStrategy asilDecompositionStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.ASIL_DECOMPOSITION_STRATEGY,
				 asilDecompositionStrategy,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ASILDecompositionComponents",
				 ASIL_DECOMPOSITION_STRATEGY__ASIL_DECOMPOSITION_COMPONENTS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ASILDescendants constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String ASIL_DECOMPOSITION_STRATEGY__ASIL_DESCENDANTS__EEXPRESSION = "let goalSeq: Sequence(CoreElement) = self.supportedBy.premise -> select(p | p.oclIsTypeOf(BasicGoal)), \n" +
		"\t\t\tg1Descendants : Set(CoreElement) = goalSeq -> at(1) -> closure(c | \n" +
		"\t\t\t\t\tif c.oclIsKindOf(DecomposableCoreElement) then c.oclAsType(DecomposableCoreElement).supportedBy.premise else null endif),\n" +
		"\t\t\tg2Descendants : Set(CoreElement) = goalSeq -> at(2) -> closure(c | \n" +
		"\t\t\t\t\tif c.oclIsKindOf(DecomposableCoreElement) then c.oclAsType(DecomposableCoreElement).supportedBy.premise else null endif) \n" +
		"\t\t\tin g1Descendants -> intersection(g2Descendants) = Set{}";

	/**
	 * Validates the ASILDescendants constraint of '<em>ASIL Decomposition Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILDecompositionStrategy_ASILDescendants(ASILDecompositionStrategy asilDecompositionStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.ASIL_DECOMPOSITION_STRATEGY,
				 asilDecompositionStrategy,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ASILDescendants",
				 ASIL_DECOMPOSITION_STRATEGY__ASIL_DESCENDANTS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSolution(Solution solution, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(solution, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validateCoreElement_GoalRoot(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validateSolution_SolutionSupporter(solution, diagnostics, context);
		if (result || diagnostics != null) result &= validateSolution_SolutionContext(solution, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the SolutionSupporter constraint of '<em>Solution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String SOLUTION__SOLUTION_SUPPORTER__EEXPRESSION = "self.oclAsType(DecomposableCoreElement).oclIsInvalid()";

	/**
	 * Validates the SolutionSupporter constraint of '<em>Solution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSolution_SolutionSupporter(Solution solution, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.SOLUTION,
				 solution,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "SolutionSupporter",
				 SOLUTION__SOLUTION_SUPPORTER__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the SolutionContext constraint of '<em>Solution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String SOLUTION__SOLUTION_CONTEXT__EEXPRESSION = "self.oclAsType(DecomposableCoreElement).oclIsInvalid()";

	/**
	 * Validates the SolutionContext constraint of '<em>Solution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSolution_SolutionContext(Solution solution, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SafetyCasePackage.Literals.SOLUTION,
				 solution,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "SolutionContext",
				 SOLUTION__SOLUTION_CONTEXT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContext(Context context, DiagnosticChain diagnostics, Map<Object, Object> theContext) {
		if (!validate_NoCircularContainment(context, diagnostics, theContext)) return false;
		boolean result = validate_EveryMultiplicityConforms(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_UniqueID(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementSupporter(context, diagnostics, theContext);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementContext(context, diagnostics, theContext);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJustification(Justification justification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(justification, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementSupporter(justification, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementContext(justification, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssumption(Assumption assumption, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(assumption, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementSupporter(assumption, diagnostics, context);
		if (result || diagnostics != null) result &= validateContextualElement_ContextualElementContext(assumption, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASIL(ASIL asil, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(asil, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImpactAnnotation(ImpactAnnotation impactAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(impactAnnotation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateASILLevel(ASILLevel asilLevel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValidityValue(ValidityValue validityValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImpactType(ImpactType impactType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //SafetyCaseValidator
