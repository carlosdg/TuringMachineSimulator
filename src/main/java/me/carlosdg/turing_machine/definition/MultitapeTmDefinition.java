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
 * Represents the definition of a Multitape Turing Machine
 *
 * @see me.carlosdg.turing_machine.definition.TmDefinition
 * @author Carlos Domínguez García
 */
public class MultitapeTmDefinition extends TmDefinition<MultitapeTransitionFunction> {

	/** @see me.carlosdg.turing_machine.definition.TmDefinition */
	public MultitapeTmDefinition(TmRawConfiguration config) throws Exception {
		super(config);
	}

	@Override
	protected MultitapeTransitionFunction parseTransitionFunction(TmRawConfiguration config) throws Exception {
		List<List<String>> rawTransitions = config.getTransitionsRepr();
		int expectedNumberOfTapes = getNumberOfTapesInTransition(rawTransitions.get(0));
		MultitapeTransitionFunction transitionFunction = new MultitapeTransitionFunction();

		for (List<String> transition : rawTransitions) {
			int numberOfTapesInTransition = getNumberOfTapesInTransition(transition);
			if (numberOfTapesInTransition <= 0) {
				throw new InvalidNumberOfTapesException(1, numberOfTapesInTransition, transition);
			}

			if (expectedNumberOfTapes != numberOfTapesInTransition) {
				throw new InvalidNumberOfTapesException(expectedNumberOfTapes, numberOfTapesInTransition, transition);
			}

			// Maybe TODO: make a new exception type for this case
			int expectedNumberOfElementsInTransition = 2 + numberOfTapesInTransition + 2 * numberOfTapesInTransition;
			if (expectedNumberOfElementsInTransition != transition.size()) {
				throw new InvalidNumberOfTapesException(numberOfTapesInTransition,
						transition.size() - expectedNumberOfElementsInTransition, transition);
			}

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
	private int getNumberOfTapesInTransition(List<String> rawTransition) {
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
	 */
	private void parseAndAddNewTransition(MultitapeTransitionFunction transitionFunction, List<String> transition)
			throws UnknownSymbolInTransitionException, InvalidMovementInTransition {
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
		int expectedNumberOfTapes = getNumberOfTapesInTransition(transition);
		return getStates().getSymbol(transition.get(expectedNumberOfTapes + 1));
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
		int numberOfTapes = getNumberOfTapesInTransition(rawTransition);
		Alphabet tapeAlphabet = getTapeAlphabet();

		for (int i = 1; i <= numberOfTapes; ++i) {
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
		int numberOfTapes = getNumberOfTapesInTransition(rawTransition);

		for (int i = numberOfTapes + 2; i < rawTransition.size(); i += 2) {
			AlphabetSymbol symbol = tapeAlphabet.getSymbol(rawTransition.get(i));
			TmMove movement = TmMove.from(rawTransition.get(i + 1));
			pairs.add(new Pair<>(symbol, movement));
		}

		return pairs;
	}

}
