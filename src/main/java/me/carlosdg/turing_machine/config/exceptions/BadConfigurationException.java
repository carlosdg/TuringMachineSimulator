package me.carlosdg.turing_machine.config.exceptions;

/**
 * Represents an exception during the creation of a Turing Machine configuration
 * object due to a presence of some null argument
 *
 * @author Carlos Domínguez García
 */
public class BadConfigurationException extends Exception {

	private static final long serialVersionUID = 271641349373520418L;

	public BadConfigurationException(String nullObjectName) {
		super("Invalid null representation of '" + nullObjectName + "' found in configuration");
	}

}
