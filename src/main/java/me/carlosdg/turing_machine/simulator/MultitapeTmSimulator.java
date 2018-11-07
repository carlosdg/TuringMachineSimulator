package me.carlosdg.turing_machine.simulator;

import java.util.List;
import java.util.Optional;

import me.carlosdg.turing_machine.definition.MultitapeTmDefinition;
import me.carlosdg.turing_machine.simulator.spies.MultitapeTmExcecutionSpy;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;
import me.carlosdg.turing_machine.transition_functions.MultitapeTransitionFunction;
import me.carlosdg.turing_machine.transition_functions.MultitapeTransitionOutput;

/**
 * Simulator of a Multitape Turing Machine
 *
 * @author Carlos Domínguez García
 */
public class MultitapeTmSimulator {

	private TmMultipleMemoryTapes tapes;
	private MultitapeTmDefinition definition;

	/** Initializes this simulator to simulate the given Multitape Turing Machine */
	public MultitapeTmSimulator(MultitapeTmDefinition definition) {
		this.definition = definition;
		tapes = new TmMultipleMemoryTapes(definition.getNumberOfTapes(), definition.getBlankSymbol());
	}

	public TmMultipleMemoryTapes getTapes() {
		return tapes;
	}

	/**
	 * Runs the machine with the given inputs, one for each tape. Returns whether
	 * the strings are accepted or not
	 */
	public boolean accepts(List<List<AlphabetSymbol>> inputWords, Optional<MultitapeTmExcecutionSpy> maybeSpy) {
		setInputToTape(inputWords);

		MultitapeTransitionFunction transitionFunction = definition.getTransitionFunction();
		State currentState = definition.getInitialState();
		List<AlphabetSymbol> inputs = tapes.read();
		if (maybeSpy.isPresent()) {
			maybeSpy.get().newIteration(currentState, tapes);
		}

		while (transitionFunction.has(currentState, inputs)) {
			MultitapeTransitionOutput transitionOutput = transitionFunction.get(currentState, inputs);
			tapes.write(transitionOutput.getPairs());
			currentState = transitionOutput.getState();
			inputs = tapes.read();
			if (maybeSpy.isPresent()) {
				maybeSpy.get().newIteration(currentState, tapes);
			}
		}

		return definition.getAcceptingStates().has(currentState);
	}

	/**
	 * Sets the given inputs to the tapes. Throws if there is any symbol that does
	 * not belong to the input alphabet
	 */
	private void setInputToTape(List<List<AlphabetSymbol>> inputWords) {
		if (inputWords.size() != definition.getNumberOfTapes()) {
			throw new IllegalArgumentException("Invalid number of inputs");
		}

		// Checking that symbols belong to input alphabet
		for (List<AlphabetSymbol> word : inputWords) {
			for (AlphabetSymbol symbol : word) {
				if (!definition.getInputAlphabet().has(symbol)) {
					throw new IllegalArgumentException("Invalid input symbol '" + symbol + "'");
				}
			}
		}

		tapes.setNewInput(inputWords);
	}

}
