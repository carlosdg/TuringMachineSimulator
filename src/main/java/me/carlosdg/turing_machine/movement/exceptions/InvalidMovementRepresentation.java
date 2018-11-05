package me.carlosdg.turing_machine.movement.exceptions;

/**
 * Represents an exception due to an illegal move representation that doesn't
 * represent any movement
 * 
 * @author Carlos Domínguez García
 */
public class InvalidMovementRepresentation extends Exception {

	public InvalidMovementRepresentation(String representation) {
		super("Illegal movement representation '" + representation + "'");
	}

}
