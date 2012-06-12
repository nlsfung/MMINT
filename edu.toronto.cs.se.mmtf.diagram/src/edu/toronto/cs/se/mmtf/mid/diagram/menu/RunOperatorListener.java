/*
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
package edu.toronto.cs.se.mmtf.mid.diagram.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.se.mmtf.MMTFException;
import edu.toronto.cs.se.mmtf.MMTFException.Type;
import edu.toronto.cs.se.mmtf.mid.Model;
import edu.toronto.cs.se.mmtf.mid.operator.ConversionOperator;
import edu.toronto.cs.se.mmtf.mid.operator.Operator;

public class RunOperatorListener extends SelectionAdapter {

	Operator operator;
	EList<Model> actualParameters;
	HashMap<Integer, EList<ConversionOperator>> conversionMap;

	public RunOperatorListener(Operator operator, EList<Model> actualParameters, HashMap<Integer, EList<ConversionOperator>> conversionMap) {

		this.operator = operator;
		this.actualParameters = actualParameters;
		this.conversionMap = conversionMap;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {

		List<IFile> files = new ArrayList<IFile>();
		files.add((IFile) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput().getAdapter(IFile.class));
		//TODO MMTF: need domain file too?
		AbstractTransactionalCommand operatorCommand = new RunOperatorCommand(
			TransactionUtil.getEditingDomain(actualParameters.get(0)),
			"Run operator",
			files
		);
		try {
			OperationHistoryFactory.getOperationHistory().execute(operatorCommand, null, null);
		}
		catch (ExecutionException ex) {
			MMTFException.print(Type.WARNING, "Operator " + operator.getName() + " history execution error", ex);
		}
	}

	protected class RunOperatorCommand extends AbstractTransactionalCommand {

		public RunOperatorCommand(TransactionalEditingDomain domain, String label, List<IFile> affectedFiles) {

			super(domain, label, affectedFiles);
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

			try {
				// run all conversion operators
				if (!conversionMap.isEmpty()) {
					for (Entry<Integer, EList<ConversionOperator>> entry : conversionMap.entrySet()) {
						int i = entry.getKey();
						EList<ConversionOperator> conversionList = entry.getValue();
						Model newActualParameter = actualParameters.get(i);
						for (ConversionOperator operator : conversionList) {
							EList<Model> operatorParameters = new BasicEList<Model>();
							operatorParameters.add(newActualParameter);
							newActualParameter = operator.getExecutable().execute(operatorParameters).get(0);
						}
						actualParameters.set(i, newActualParameter);
					}
				}
				// run operator
				operator.getExecutable().execute(actualParameters);
				// cleanup all conversion operators
				//TODO MMTF: do it with interface

				return CommandResult.newOKCommandResult();
			}
			catch (Exception e) {
				MMTFException.print(Type.WARNING, "Operator " + operator.getName() + " execution error", e);
				return CommandResult.newErrorCommandResult("Operator " + operator.getName() + " execution error");
			}
		}

	}

}
