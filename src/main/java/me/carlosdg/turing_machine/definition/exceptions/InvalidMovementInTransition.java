package me.carlosdg.turing_machine.definition.exceptions;

import java.util.List;

/**
 * Represents an exception due to a transition having a string that doesn't
 * represent an expected movement
 *
 * @author Carlos Domínguez García
 */
public class InvalidMovementInTransition extends Exception {

	private static final long serialVersionUID = 3313076707298424055L;

	public InvalidMovementInTransition(String invalidMoveRepr, List<String> transitionRepr) {
		super("Invalid move '" + invalidMoveRepr + "' found in transition " + String.join(" ", transitionRepr));
	}

}
