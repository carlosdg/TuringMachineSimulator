package me.carlosdg.turing_machine.definition.exceptions;

import java.util.List;

/**
 * Represents an exception due to a transition not having the expected number of
 * tapes in a multitape turing machine
 *
 * @author Carlos Domínguez García
 */
public class InvalidNumberOfTapesException extends Exception {

	private static final long serialVersionUID = -4287746930323208799L;

	public InvalidNumberOfTapesException(int expectedNumberOfTapes, int numberOfTapesInTransition,
			List<String> transition) {
		super("Invalid number of tapes. Expected " + expectedNumberOfTapes + " but found " + numberOfTapesInTransition
				+ " in transition " + String.join(" ", transition));
	}

}
