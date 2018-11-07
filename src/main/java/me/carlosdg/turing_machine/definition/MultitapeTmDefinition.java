package me.carlosdg.turing_machine.definition;

import java.util.ArrayList;
import java.util.List;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.definition.exceptions.InvalidMovementInTransition;
import me.carlosdg.turing_machine.definition.exceptions.InvalidNumberOfTapesException;
import me.carlosdg.turing_machine.definition.exceptions.UnknownSymbolInTransitionException;
import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.movement.exceptions.InvalidMovementRepresentationException;
import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;
import me.carlosdg.turing_machine.transition_functions.MultitapeTransitionFunction;
import me.carlosdg.turing_machine.utils.Pair;

/**
 * Represents the definition of a Multitape Turing Machine. It is responsible
 * for parsing the transition function of the multitape machine
 *
 * @see me.carlosdg.turing_machine.definition.TmDefinition
 * @author Carlos Domínguez García
 */
public class MultitapeTmDefinition extends TmDefinition<MultitapeTransitionFunction> {

	private int numberOfTapes = 0;

	/** @see me.carlosdg.turing_machine.definition.TmDefinition */
	public MultitapeTmDefinition(TmRawConfiguration config) throws Exception {
		super(config);
	}

	/** Returns the number of tapes of this machine */
	public int getNumberOfTapes() {
		return numberOfTapes;
	}

	@Override
	protected MultitapeTransitionFunction parseTransitionFunction(TmRawConfiguration config) throws Exception {
		List<List<String>> rawTransitions = config.getTransitionsRepr();
		MultitapeTransitionFunction transitionFunction = new MultitapeTransitionFunction();
		numberOfTapes = calculateNumberOfTapesInTransition(rawTransitions.get(0));

		// Checking that there is at least one tape inferred from the first transition
		if (getNumberOfTapes() <= 0) {
			throw new InvalidNumberOfTapesException(1, rawTransitions.get(0));
		}

		for (List<String> transition : rawTransitions) {
			parseAndAddNewTransition(transitionFunction, transition);
		}

		return transitionFunction;
	}

	/**
	 * Returns the number of tapes inferred from the given transition
	 *
	 * @param rawTransition Transition to infer the number of tapes from
	 * @return The number of tapes that this Turing Machine must have assuming the
	 *         given raw transition has no errors
	 */
	private int calculateNumberOfTapesInTransition(List<String> rawTransition) {
		// State, tapeSymbol, ..., tapeSymbol, state, tapeSymbol, Movement, ...,
		// tapeSymbol, Movement
		// State, k * tapeSymbols, state, k * ( tapeSymbol, Movement )
		// Being k the the number of tapes
		// rawTransition.size = 2 + 3k
		// k = (rawTransition.size - 2) / 3
		return (rawTransition.size() - 2) / 3;
	}

	/**
	 * Parses the given raw transition and updates the transition function with it
	 *
	 * @param transitionFunction Transition function to add the parsed transition
	 * @param transition         Transition to parse
	 * @throws UnknownSymbolInTransitionException If there is a string in the
	 *                                            transition that doesn't represent
	 *                                            a valid symbol of its
	 *                                            corresponding set
	 * @throws InvalidMovementInTransition        If there is a string in the
	 *                                            transition that doesn't represent
	 *                                            a valid movement
	 * @throws InvalidNumberOfTapesException      If the given raw transition has a
	 *                                            different number of tapes than
	 *                                            expected
	 */
	private void parseAndAddNewTransition(MultitapeTransitionFunction transitionFunction, List<String> transition)
			throws UnknownSymbolInTransitionException, InvalidMovementInTransition, InvalidNumberOfTapesException {
		int numberOfTapesInTransition = calculateNumberOfTapesInTransition(transition);
		int expectedNumberOfElementsInTransition = 2 + getNumberOfTapes() + 2 * getNumberOfTapes();

		// Checking that the number of tapes in this transition is the same as previous
		// transitions
		if (numberOfTapesInTransition != getNumberOfTapes()) {
			throw new InvalidNumberOfTapesException(getNumberOfTapes(), transition);
		}

		// Checking that the number of elements in the transition is the expected number
		if (expectedNumberOfElementsInTransition != transition.size()) {
			throw new InvalidNumberOfTapesException(getNumberOfTapes(), transition);
		}

		try {
			State currentState = parseCurrentState(transition);
			List<AlphabetSymbol> inputSymbols = parseInputSymbols(transition);
			State outputState = parseOutputState(transition);
			List<Pair<AlphabetSymbol, TmMove>> symbolMovementPairs = parseOutputSymbolMovementPairs(transition);

			transitionFunction.put(currentState, inputSymbols, outputState, symbolMovementPairs);
		} catch (SymbolNotFoundInSetException exception) {
			throw new UnknownSymbolInTransitionException(exception.getStringReprOfNotFoundSymbol(), transition,
					exception.getSet());
		} catch (InvalidMovementRepresentationException exception) {
			throw new InvalidMovementInTransition(exception.getInvalidMoveRepr(), transition);
		}
	}

	/**
	 * Parses the current state from the given transition and returns the state
	 * object
	 *
	 * @param transition Transition to parse
	 * @return The State object represented by the current state in the given
	 *         transition
	 * @throws SymbolNotFoundInSetException If the current state string in the
	 *                                      transition doesn't represent a state in
	 *                                      the machine state set
	 */
	private State parseCurrentState(List<String> transition) throws SymbolNotFoundInSetException {
		return getStates().getSymbol(transition.get(0));
	}

	/**
	 * Parses the output state from the given transition and returns the state
	 * object
	 *
	 * @param transition Transition to parse
	 * @return The State object represented by the output state in the given
	 *         transition
	 * @throws SymbolNotFoundInSetException If the output state string in the
	 *                                      transition doesn't represent a state in
	 *                                      the machine state set
	 */
	private State parseOutputState(List<String> transition) throws SymbolNotFoundInSetException {
		return getStates().getSymbol(transition.get(1 + getNumberOfTapes()));
	}

	/**
	 * Parses the input symbols of the given transition
	 *
	 * @param rawTransition Transition to parse the input symbols
	 * @return A list of alphabet symbols created from the representation from the
	 *         given transition
	 * @throws SymbolNotFoundInSetException If there is a string in the transition
	 *                                      that doesn't represent any symbol in the
	 *                                      tape alphabet
	 */
	private List<AlphabetSymbol> parseInputSymbols(List<String> rawTransition) throws SymbolNotFoundInSetException {
		List<AlphabetSymbol> inputSymbols = new ArrayList<>();
		Alphabet tapeAlphabet = getTapeAlphabet();

		for (int i = 1; i <= getNumberOfTapes(); ++i) {
			inputSymbols.add(tapeAlphabet.getSymbol(rawTransition.get(i)));
		}

		return inputSymbols;
	}

	/**
	 * Parses the output symbol-movement pair from the given raw transition
	 *
	 * @param rawTransition Transition representation to parse
	 * @return List of pair symbol-movement created after parsing their
	 *         representation in the given transition
	 * @throws SymbolNotFoundInSetException           If there is a string in the
	 *                                                transition that doesn't
	 *                                                represent any symbol in the
	 *                                                tape alphabet
	 * @throws InvalidMovementRepresentationException If there is a string in the
	 *                                                transition that doesn't
	 *                                                represent a valid movement
	 */
	private List<Pair<AlphabetSymbol, TmMove>> parseOutputSymbolMovementPairs(List<String> rawTransition)
			throws SymbolNotFoundInSetException, InvalidMovementRepresentationException {
		List<Pair<AlphabetSymbol, TmMove>> pairs = new ArrayList<>();
		Alphabet tapeAlphabet = getTapeAlphabet();

		for (int i = 2 + getNumberOfTapes(); i < rawTransition.size(); i += 2) {
			AlphabetSymbol symbol = tapeAlphabet.getSymbol(rawTransition.get(i));
			TmMove movement = TmMove.from(rawTransition.get(i + 1));
			pairs.add(new Pair<>(symbol, movement));
		}

		return pairs;
	}

}
