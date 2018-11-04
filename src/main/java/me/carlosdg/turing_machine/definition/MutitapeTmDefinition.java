package me.carlosdg.turing_machine.definition;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.definition.exceptions.BlankSymbolFoundInInputAlphabetException;
import me.carlosdg.turing_machine.definition.exceptions.BlankSymbolNotFoundInTapeAlphabetException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidAcceptingStatesSet;
import me.carlosdg.turing_machine.definition.exceptions.InvalidInitialStateException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidInputAlphabetException;
import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.sets.StateSet;
import me.carlosdg.turing_machine.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Represents a Multitape Turing Machine as a tuple with the following elements:
 * <ul>
 * <li>The set of states of the machine</li>
 * <li>The set of accepting states of the machine</li>
 * <li>The initial state</li>
 * <li>The input alphabet</li>
 * <li>The tape alphabet</li>
 * <li>The blank symbol</li>
 * <li>The transition function</li>
 * </ul>
 *
 * @author Carlos Domínguez García
 */
public class MutitapeTmDefinition {
	private StateSet states;
	private StateSet acceptingStates;
	private State initialState;
	private Alphabet tapeAlphabet;
	private Alphabet inputAlphabet;
	private AlphabetSymbol blankSymbol;
	// TODO: Transition function

	/**
	 * Create a Turing Machine definition by parsing the given Turing Machine raw
	 * configuration object
	 *
	 * @param config Turing Machine raw configuration
	 * @throws DuplicatedStringInSetException             If there is any duplicated
	 *                                                    symbol representation in
	 *                                                    any set
	 * @throws InvalidAcceptingStatesSet                  If there is any accepting
	 *                                                    state symbol that is not
	 *                                                    part of the states set
	 * @throws InvalidInitialStateException               If the initial symbol is
	 *                                                    not found in the states
	 *                                                    set
	 * @throws InvalidInputAlphabetException              If the input alphabet is
	 *                                                    not a subset of the tape
	 *                                                    alphabet
	 * @throws BlankSymbolFoundInInputAlphabetException   If the blank symbol is
	 *                                                    found in the input
	 *                                                    alphabet
	 * @throws BlankSymbolNotFoundInTapeAlphabetException If the blank symbol is not
	 *                                                    found in the tape alphabet
	 */
	public MutitapeTmDefinition(TmRawConfiguration config) throws DuplicatedStringInSetException,
			InvalidInitialStateException, InvalidAcceptingStatesSet, BlankSymbolNotFoundInTapeAlphabetException,
			BlankSymbolFoundInInputAlphabetException, InvalidInputAlphabetException {
		setStates(config);
		setAlphabetSymbols(config);
	}

	/**
	 * Sets the set of states and initial state
	 *
	 * @param config Configuration object with the raw representation of the initial
	 *               state, the set of states and accepting states of the Turing
	 *               Machine
	 * @throws DuplicatedStringInSetException If there is any duplicated symbol
	 *                                        representation in the set of states or
	 *                                        the set of accepting states
	 * @throws InvalidAcceptingStatesSet      If there is any accepting state symbol
	 *                                        that is not part of the states set
	 * @throws InvalidInitialStateException   If the initial symbol is not found in
	 *                                        the states set
	 */
	private void setStates(TmRawConfiguration config)
			throws DuplicatedStringInSetException, InvalidInitialStateException, InvalidAcceptingStatesSet {
		states = new StateSet(config.getSetOfStatesRepr());
		acceptingStates = new StateSet(config.getSetOfAcceptingStatesRepr());
		initialState = new State(config.getInitialStateRepr());

		if (!states.has(initialState)) {
			throw new InvalidInitialStateException(initialState, states);
		}

		for (State acceptingState : acceptingStates) {
			if (!states.has(acceptingState)) {
				throw new InvalidAcceptingStatesSet(acceptingState, acceptingStates, states);
			}
		}

	}

	/**
	 * Sets the blank symbol, tape and input alphabet
	 *
	 * @param config Configuration object with the raw representation of the blank
	 *               symbol, the input and tape alphabet of the Turing Machine
	 * @throws DuplicatedStringInSetException             If there is any duplicated
	 *                                                    symbol representation in
	 *                                                    any set
	 * @throws InvalidInputAlphabetException              If the input alphabet is
	 *                                                    not a subset of the tape
	 *                                                    alphabet
	 * @throws BlankSymbolFoundInInputAlphabetException   If the blank symbol is
	 *                                                    found in the input
	 *                                                    alphabet
	 * @throws BlankSymbolNotFoundInTapeAlphabetException If the blank symbol is not
	 *                                                    found in the tape alphabet
	 */
	private void setAlphabetSymbols(TmRawConfiguration config)
			throws DuplicatedStringInSetException, BlankSymbolNotFoundInTapeAlphabetException,
			BlankSymbolFoundInInputAlphabetException, InvalidInputAlphabetException {
		inputAlphabet = new Alphabet(config.getInputAlphabetRepr());
		tapeAlphabet = new Alphabet(config.getTapeAlphabetRepr());
		blankSymbol = new AlphabetSymbol(config.getBlankSymbolRepr());

		if (!tapeAlphabet.has(blankSymbol)) {
			throw new BlankSymbolNotFoundInTapeAlphabetException(blankSymbol, tapeAlphabet);
		}

		if (inputAlphabet.has(blankSymbol)) {
			throw new BlankSymbolFoundInInputAlphabetException(blankSymbol, inputAlphabet);
		}

		for (AlphabetSymbol inputSymbol : inputAlphabet) {
			if (!tapeAlphabet.has(inputSymbol)) {
				throw new InvalidInputAlphabetException(inputSymbol, inputAlphabet, tapeAlphabet);
			}
		}

	}

}
