package me.carlosdg.turing_machine.definition.exceptions;

import me.carlosdg.turing_machine.sets.StateSet;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Represent exceptions due to the set of accepting states having a state that
 * is not part of the set of states
 *
 * @author Carlos Domínguez García
 */
public class InvalidAcceptingStatesSetException extends Exception {

	private static final long serialVersionUID = 3902529109875769844L;

	public InvalidAcceptingStatesSetException(State acceptingStateNotFound, StateSet acceptingStateSet, StateSet stateSet) {
		super("Invalid accepting state '" + acceptingStateNotFound + "' found in the set of accepting states. "
				+ acceptingStateSet + " It is not part of the set of states: " + stateSet);
	}

}
