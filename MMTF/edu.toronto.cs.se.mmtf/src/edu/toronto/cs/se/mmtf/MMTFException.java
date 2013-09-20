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
package edu.toronto.cs.se.mmtf;

/**
 * Custom exception for MMTF.
 *
 * @author Alessio Di Sandro
 * 
 */
public class MMTFException extends Exception {

	/** Exception types. */
	public enum Type {WARNING, ERROR}
	/** Autogenerated. */
	private static final long serialVersionUID = 1L;

	//TODO MMTF: rework ->
	// 1) Use a private Type type, pass it in constructor and create a print function that inspects it.
	// 2) Everywhere an exception is used, clearly differentiate between mmtf exceptions and other ones.
	// 3) Make gmf commands aware of "real" errors or simple user cancellations (e.g. returning a cancel command)
	/**
	 * Prints an exception.
	 * 
	 * @param type
	 *            The exception category.
	 * @param message
	 *            The exception message.
	 * @param e
	 *            The exception or error.
	 */
	public static void print(Type type, String message, Throwable e) {

		switch (type) {
			case WARNING:
				message = "WARNING: " + message;
				break;
			case ERROR:
				message = "ERROR: " + message;
				break;
			default:
				message = "UNKNOWN PROBLEM: " + message;
		}
		System.err.println(message);
		if (e != null) {
			System.err.println(" -> " + e.getMessage());
		}

		if (type == Type.ERROR) {
			if (e != null) {
				e.printStackTrace();
			}
			System.exit(-1);
		}
	}

	/**
	 * Constructor: initialises the superclass.
	 * 
	 * @param message
	 *            The exception message.
	 */
	public MMTFException(String message) {

		super(message);
	}

	/**
	 * Constructor: initialises the superclass with a chained exception.
	 * 
	 * @param message
	 *            The exception message.
	 * @param cause
	 *            The exception that caused this exception.
	 */
	public MMTFException(String message, Throwable cause) {

		super(message, cause);
	}

}
