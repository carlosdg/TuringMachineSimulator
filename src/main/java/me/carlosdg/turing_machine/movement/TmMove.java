package me.carlosdg.turing_machine.movement;

import me.carlosdg.turing_machine.movement.exceptions.InvalidMovementRepresentationException;

/**
 * Turing Machine possible moves
 *
 * @author Carlos Domínguez García
 */
public enum TmMove {

	STOP("S", 0), LEFT("L", -1), RIGHT("R", 1);

	/**
	 * Returns the TmMove object that best represents the given string
	 *
	 * @param represention String representation of the movement
	 * @return The TmMove object that represents the given movement
	 * @throws InvalidMovementRepresentationException If there is no movement
	 *                                                represented with the given
	 *                                                string
	 */
	static public TmMove from(String represention) throws InvalidMovementRepresentationException {
		switch (represention) {
		case "S":
			return TmMove.STOP;
		case "L":
			return TmMove.LEFT;
		case "R":
			return TmMove.RIGHT;

		default:
			throw new InvalidMovementRepresentationException(represention);
		}
	}

	private String representation;
	private int movementOffset;

	private TmMove(String representation, int movementValue) {
		this.representation = representation;
		movementOffset = movementValue;
	}

	/** Returns the movement string representation */
	public String getRepresentation() {
		return representation;
	}

	/** Returns a value representing the movement offset */
	public int getMovementOffset() {
		return movementOffset;
	}

	@Override
	public String toString() {
		return representation;
	}
}
