package me.carlosdg.turing_machine.transition_functions;

import java.util.List;
import java.util.Objects;

import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Convenient class to wrap the keys of the transition function into a single
 * object
 *
 * @author Carlos Domínguez García
 */
public class MultitapeTransitionInput {

	private State state;
	private List<AlphabetSymbol> symbols;

	public MultitapeTransitionInput(State state, List<AlphabetSymbol> symbols) {
		this.state = state;
		this.symbols = symbols;
	}

	public State getState() {
		return state;
	}

	public List<AlphabetSymbol> getSymbols() {
		return symbols;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getState(), getSymbols());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		MultitapeTransitionInput other = (MultitapeTransitionInput) obj;
		return Objects.equals(getState(), other.getState()) && Objects.equals(getSymbols(), other.getSymbols());
	}

}
