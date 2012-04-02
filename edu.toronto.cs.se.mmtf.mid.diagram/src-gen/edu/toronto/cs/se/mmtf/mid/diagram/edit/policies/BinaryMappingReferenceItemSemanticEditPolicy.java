/*
 * Copyright (C) 2012 Marsha Chechik, Alessio Di Sandro, Rick Salay
 * 
 * This file is part of MMTF ver. 0.9.0.
 * 
 * MMTF is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MMTF is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MMTF.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.toronto.cs.se.mmtf.mid.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.BinaryMappingReferenceChangeModelCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.BinaryMappingReferenceCreateCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.BinaryMappingReferenceCreateMappingCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.BinaryMappingReferenceReorientCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.MappingReferenceAddModelCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.MappingReferenceChangeModelCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.MappingReferenceModelsCreateCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.commands.MappingReferenceModelsReorientCommand;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.parts.BinaryMappingReferenceEditPart;
import edu.toronto.cs.se.mmtf.mid.diagram.edit.parts.MappingReferenceModelsEditPart;
import edu.toronto.cs.se.mmtf.mid.diagram.providers.MIDElementTypes;

/**
 * @generated
 */
public class BinaryMappingReferenceItemSemanticEditPolicy extends
		MIDBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public BinaryMappingReferenceItemSemanticEditPolicy() {
		super(MIDElementTypes.BinaryMappingReference_4004);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommandGen(
			CreateRelationshipRequest req) {
		if (MIDElementTypes.MappingReferenceModels_4003 == req.getElementType()) {
			return getGEFWrapper(new MappingReferenceModelsCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (MIDElementTypes.BinaryMappingReference_4004 == req.getElementType()) {
			return getGEFWrapper(new BinaryMappingReferenceCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (MIDElementTypes.MappingReferenceModels_4003 == req.getElementType()) {
			return getGEFWrapper(new MappingReferenceAddModelCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (MIDElementTypes.BinaryMappingReference_4004 == req.getElementType()) {
			return getGEFWrapper(new BinaryMappingReferenceCreateMappingCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommandGen(
			CreateRelationshipRequest req) {
		if (MIDElementTypes.MappingReferenceModels_4003 == req.getElementType()) {
			return getGEFWrapper(new MappingReferenceModelsCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (MIDElementTypes.BinaryMappingReference_4004 == req.getElementType()) {
			return getGEFWrapper(new BinaryMappingReferenceCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (MIDElementTypes.MappingReferenceModels_4003 == req.getElementType()) {
			return getGEFWrapper(new MappingReferenceAddModelCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (MIDElementTypes.BinaryMappingReference_4004 == req.getElementType()) {
			return getGEFWrapper(new BinaryMappingReferenceCreateMappingCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommandGen(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case BinaryMappingReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new BinaryMappingReferenceReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated NOT
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case BinaryMappingReferenceEditPart.VISUAL_ID:
			return getGEFWrapper(new BinaryMappingReferenceChangeModelCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommandGen(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case MappingReferenceModelsEditPart.VISUAL_ID:
			return getGEFWrapper(new MappingReferenceModelsReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated NOT
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case MappingReferenceModelsEditPart.VISUAL_ID:
			return getGEFWrapper(new MappingReferenceChangeModelCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
