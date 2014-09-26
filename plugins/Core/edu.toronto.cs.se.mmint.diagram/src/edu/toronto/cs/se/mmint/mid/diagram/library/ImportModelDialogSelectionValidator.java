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
package edu.toronto.cs.se.mmint.mid.diagram.library;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

public class ImportModelDialogSelectionValidator implements ISelectionStatusValidator {

	/**
	 * {@inheritDoc}
	 * The selection of a folder is not allowed, only files.
	 */
	@Override
	public IStatus validate(Object[] selection) {

		return new Status(IStatus.ERROR, "edu.toronto.cs.se.mmint", "Please select a file");
		//return new Status(IStatus.OK, "edu.toronto.cs.se.mmint", "");
	}

}
