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
package edu.toronto.cs.se.mmint.mid.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import edu.toronto.cs.se.mmint.MMINT;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.mid.MID;
import edu.toronto.cs.se.mmint.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogUtils;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogCancellation;

/**
 * The command to create a model relationship.
 * 
 * @author Alessio Di Sandro
 * 
 */
public class ModelRelNewNaryRelCommand extends ModelRelCreateCommand {

	/**
	 * Constructor: initialises the superclass.
	 * 
	 * @param req
	 *            The request.
	 */
	public ModelRelNewNaryRelCommand(CreateElementRequest req) {

		super(req);
	}
	
    /**
     * Overrides superclass to re-initialize diagram type hierarchy.
     */
	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		IStatus status = super.doUndo(monitor, info);
		MID mid = (MID) getElementToEdit();
		if (mid.isTypesLevel()) {
			MMINT.createTypeHierarchy(mid);
		}

		return status;
    }

    /**
     * Overrides superclass to re-initialize diagram type hierarchy.
     */
	protected IStatus doRedo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		IStatus status = super.doRedo(monitor, info);
		MID mid = (MID) getElementToEdit();
		if (mid.isTypesLevel()) {
			MMINT.createTypeHierarchy(mid);
		}

		return status;
    }

	/**
	 * Checks if a model relationship can be created.
	 * 
	 * @return True if a model relationship can be created, false otherwise.
	 */
	@Override
	public boolean canExecute() {

		MID mid = (MID) getElementToEdit();
		return super.canExecute() && (
			!mid.isWorkflowsLevel() ||
			mid.getOperators().isEmpty()
		);
	}

	protected ModelRel doExecuteTypesLevel() throws MMINTException, MIDDialogCancellation {

		MID typeMID = (MID) getElementToEdit();
		ModelRel modelRelType = MIDDialogUtils.selectModelRelTypeToExtend(typeMID, null, null);
		String newModelRelTypeName = MIDDialogUtils.getStringInput("Create new light model relationship type", "Insert new model relationship type name", null);
		String[] constraint = MIDDialogUtils.getConstraintInput("Create new light model relationship type", null);
		ModelRel newModelRelType = (ModelRel) modelRelType.createSubtype(newModelRelTypeName, constraint[0], constraint[1], false);
		MMINT.createTypeHierarchy(typeMID);

		return newModelRelType;
	}

	protected ModelRel doExecuteInstancesLevel() throws MMINTException, MIDDialogCancellation {

		MID instanceMID = (MID) getElementToEdit();
		ModelRel modelRelType = MIDDialogUtils.selectModelRelTypeToCreate(null, null);
		ModelRel newModelRel = (ModelRel) modelRelType.createInstance(null, instanceMID);

		return newModelRel;
	}

	protected ModelRel doExecuteWorkflowsLevel() throws MMINTException, MIDDialogCancellation {

		MID workflowMID = (MID) getElementToEdit();
		ModelRel modelRelType = MIDDialogUtils.selectModelRelTypeToCreate(null, null);
		ModelRel newModelRel = (ModelRel) modelRelType.createWorkflowInstance("TODO", workflowMID);

		return newModelRel;
	}

	/**
	 * Creates a new model relationship.
	 * 
	 * @param monitor
	 *            The progress monitor.
	 * @param info
	 *            Additional parameter, not used.
	 * @return The ok result.
	 * @throws ExecutionException
	 *             If configuration command execution goes wrong.
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		try {
			ModelRel newElement;
			switch (((MID) getElementToEdit()).getLevel()) {
				case TYPES:
					newElement = this.doExecuteTypesLevel();
					break;
				case INSTANCES:
					newElement = this.doExecuteInstancesLevel();
					break;
				case WORKFLOWS:
					newElement = this.doExecuteWorkflowsLevel();
					break;
				default:
					throw new MMINTException("The MID level is missing");
			}
			doConfigure(newElement, monitor, info);
			((CreateElementRequest) getRequest()).setNewElement(newElement);
	
			return CommandResult.newOKCommandResult(newElement);
		}
		catch (ExecutionException ee) {
			throw ee;
		}
		catch (MIDDialogCancellation e) {
			return CommandResult.newCancelledCommandResult();
		}
		catch (MMINTException e) {
			MMINTException.print(IStatus.ERROR, "No model relationship created", e);
			return CommandResult.newErrorCommandResult("No model relationship created");
		}
	}

}
