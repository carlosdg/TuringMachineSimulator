package me.carlosdg.turing_machine.definition;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.sets.StateSet;
import me.carlosdg.turing_machine.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;

public class MutitapeTmDefinition {
	private StateSet states;
	private Alphabet inputAlphabet;
	private Alphabet tapeAlphabet;
	private State initialState;
	private AlphabetSymbol blankSymbol;
	private StateSet acceptingStates;
	// TODO: Transition function

	public MutitapeTmDefinition(TmRawConfiguration config)
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		setStates(config);
		setAlphabetSymbols(config);
	}

	private void setStates(TmRawConfiguration config)
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		states = new StateSet(config.getSetOfStatesRepr());
		acceptingStates = new StateSet(config.getSetOfAcceptingStatesRepr());
		initialState = new State(config.getInitialStateRepr());

		if (!states.has(initialState)) {
			throw new SymbolNotFoundInSetException(initialState.getRepresentation(), states);
		}

		for (State acceptingState : acceptingStates) {
			if (!states.has(acceptingState)) {
				throw new SymbolNotFoundInSetException(acceptingState.getRepresentation(), states);
			}
		}

	}

	private void setAlphabetSymbols(TmRawConfiguration config)
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		inputAlphabet = new Alphabet(config.getInputAlphabetRepr());
		tapeAlphabet = new Alphabet(config.getTapeAlphabetRepr());
		blankSymbol = new AlphabetSymbol(config.getBlankSymbolRepr());

		if (!tapeAlphabet.has(blankSymbol)) {
			throw new SymbolNotFoundInSetException(blankSymbol.getRepresentation(), tapeAlphabet);
		}

		if (inputAlphabet.has(blankSymbol)) {

		}

		for (AlphabetSymbol inputSymbol : inputAlphabet) {
			if (!tapeAlphabet.has(inputSymbol)) {
				throw new SymbolNotFoundInSetException(inputSymbol.getRepresentation(), tapeAlphabet);
			}
		}

	}

}
