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
package edu.toronto.cs.se.mmint.mid.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import edu.toronto.cs.se.mmint.MMINTActivator;

public class NewOperatorTypeDialogSelectionValidator implements ISelectionStatusValidator {

	/**
	 * {@inheritDoc} The selection of a project or folder is not allowed, only files.
	 */
	@Override
	public IStatus validate(Object[] selection) {

		for (Object object : selection) {
			if (object instanceof IFolder || object instanceof IProject) {
				return new Status(IStatus.ERROR, MMINTActivator.PLUGIN_ID, "Please select a Workflow MID file");
			}
		}

		return new Status(IStatus.OK, MMINTActivator.PLUGIN_ID, "Workflow MID file selected");
	}

}
