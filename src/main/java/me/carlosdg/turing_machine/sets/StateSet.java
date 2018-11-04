package me.carlosdg.turing_machine.sets;

import java.util.Collection;

import me.carlosdg.turing_machine.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Represents a set of states
 *
 * @author Carlos Domínguez García
 */
public class StateSet extends SymbolSet<State> {

	/** @see me.carlosdg.turing_machine.sets.SymbolSet */
	public StateSet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected State newSymbol(String representation) {
		return new State(representation);
	}

}