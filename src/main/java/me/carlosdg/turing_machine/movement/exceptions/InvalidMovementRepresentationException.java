package me.carlosdg.turing_machine.movement.exceptions;

/**
 * Represents an exception due to an illegal move representation that doesn't
 * represent any movement
 *
 * @author Carlos Domínguez García
 */
public class InvalidMovementRepresentationException extends Exception {

	private static final long serialVersionUID = 8529707769204722429L;
	private String moveRepr;

	public InvalidMovementRepresentationException(String representation) {
		super("Illegal movement representation '" + representation + "'");
		moveRepr = representation;
	}

	public String getInvalidMoveRepr() {
		return moveRepr;
	}

}
