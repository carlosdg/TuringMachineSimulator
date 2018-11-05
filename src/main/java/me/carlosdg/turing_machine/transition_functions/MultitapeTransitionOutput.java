package me.carlosdg.turing_machine.transition_functions;

import java.util.List;
import java.util.Objects;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;
import me.carlosdg.turing_machine.utils.Pair;

/**
 * Convenient class to wrap the values of the transition function into a single
 * object
 *
 * @author Carlos Domínguez García
 */
public class MultitapeTransitionOutput {

	private State state;
	private List<Pair<AlphabetSymbol, TmMove>> pairs;

	public MultitapeTransitionOutput(State state, List<Pair<AlphabetSymbol, TmMove>> pairs) {
		this.state = state;
		this.pairs = pairs;
	}

	public State getState() {
		return state;
	}

	public List<Pair<AlphabetSymbol, TmMove>> getPairs() {
		return pairs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPairs(), getState());
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
		MultitapeTransitionOutput other = (MultitapeTransitionOutput) obj;
		return Objects.equals(getPairs(), other.getPairs()) && Objects.equals(getState(), other.getState());
	}

}
