package me.carlosdg.turing_machine.transition_functions.exceptions;

import me.carlosdg.turing_machine.transition_functions.MultitapeTransitionInput;
import me.carlosdg.turing_machine.transition_functions.MultitapeTransitionOutput;

/**
 * Represents an exception due to a duplicated transition found
 *
 * @author Carlos Domínguez García
 */
public class TransitionAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 4538596671523880643L;

	public TransitionAlreadyPresentException(MultitapeTransitionInput key, MultitapeTransitionOutput value) {
		super("Duplicated transition");
	}

}
