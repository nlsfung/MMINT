/**
 * Copyright (c) 2012-2016 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmint.mavo.mavomid;

import edu.toronto.cs.se.mmint.mid.ModelEndpoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MAVO Model Endpoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * MAVO version. {@inheritDoc}
 * <!-- end-model-doc -->
 *
 *
 * @see edu.toronto.cs.se.mmint.mavo.mavomid.MAVOMIDPackage#getMAVOModelEndpoint()
 * @model
 * @generated
 */
public interface MAVOModelEndpoint extends ModelEndpoint {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * {@inheritDoc}<br />
	 * Gets the MAVO model target of this MAVO model endpoint.
	 * <!-- end-model-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='ExtendibleElement target = super.getTarget();\nreturn (target == null) ? null : (MAVOModel) target;'"
	 * @generated
	 */
	MAVOModel getTarget();
} // MAVOModelEndpoint
