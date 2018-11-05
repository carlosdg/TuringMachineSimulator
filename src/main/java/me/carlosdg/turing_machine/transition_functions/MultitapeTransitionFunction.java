package me.carlosdg.turing_machine.transition_functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;
import me.carlosdg.turing_machine.utils.Pair;

/**
 * Multitape Turing Machine transition function
 *
 * @author Carlos Domínguez García
 */
public class MultitapeTransitionFunction {

	private Map<MultitapeTransitionInput, MultitapeTransitionOutput> transitionMap = new HashMap<>();

	/**
	 * Adds the given transition to this transition map/function
	 *
	 * @param currentState        Input state
	 * @param inputSymbols        Tape symbols (one for each tape)
	 * @param outputState         Output state
	 * @param symbolMovementPairs Output pairs of symbols-moves (one for each tape)
	 */
	public void put(State currentState, List<AlphabetSymbol> inputSymbols, State outputState,
			List<Pair<AlphabetSymbol, TmMove>> symbolMovementPairs) {
		MultitapeTransitionInput key = new MultitapeTransitionInput(currentState, inputSymbols);
		MultitapeTransitionOutput value = new MultitapeTransitionOutput(outputState, symbolMovementPairs);
		transitionMap.put(key, value);
	}

	/**
	 * Returns the output state and pairs symbol-moves associated to the given input
	 * state + input symbols
	 *
	 * @param currentState Current machine state
	 * @param inputSymbols Tape symbols
	 * @return The pairs symbol-moves associated to the given input
	 */
	public MultitapeTransitionOutput get(State currentState, List<AlphabetSymbol> inputSymbols) {
		MultitapeTransitionInput key = new MultitapeTransitionInput(currentState, inputSymbols);
		return transitionMap.get(key);
	}

	/**
	 * Returns whether the given entry has an associated value or not
	 *
	 * @param currentState Current machine state
	 * @param inputSymbols Tape symbols
	 * @return Whether the given entry has an associated value or not
	 */
	public boolean has(State currentState, List<AlphabetSymbol> inputSymbols) {
		MultitapeTransitionInput key = new MultitapeTransitionInput(currentState, inputSymbols);
		return transitionMap.containsKey(key);
	}

}
