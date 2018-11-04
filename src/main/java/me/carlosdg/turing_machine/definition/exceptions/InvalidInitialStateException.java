package me.carlosdg.turing_machine.definition.exceptions;

import me.carlosdg.turing_machine.sets.StateSet;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Represent exceptions due to the initial state not being present in the set of
 * states
 *
 * @author Carlos Domínguez García
 */
public class InvalidInitialStateException extends Exception {

	private static final long serialVersionUID = 2420112226447601937L;

	public InvalidInitialStateException(State invalidInitialState, StateSet stateSet) {
		super("Invalid state '" + invalidInitialState + "'. It is not part of the set of states: " + stateSet);
	}

}
