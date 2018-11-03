package me.carlosdg.turing_machine.config;

import java.util.List;

import me.carlosdg.turing_machine.config.exceptions.BadConfigurationException;

/**
 * Represents JSON like objects to hold the Turing Machine configuration read
 * from any source. It is intended to be used as an abstraction layer between
 * how the Turing Machine definition is entered to the application and how it is
 * used. This way the parser doesn't need to know how the configuration is read,
 * it could be from a file with custom format, from a file with JSON format,
 * from memory, etc.
 *
 * @author Carlos Domínguez García
 */
public class TmRawConfiguration {

	/** List of representations of the states in the state set */
	private List<String> setOfStatesRepr;

	/** List of representations of symbols in the input alphabet */
	private List<String> inputAlphabetRepr;

	/** List of representations of symbols in the tape alphabet */
	private List<String> tapeAlphabetRepr;

	/** String representation of the initial state */
	private String initialStateRepr;

	/** String representation of the blank symbol */
	private String blankSymbolRepr;

	/** List of representations of the states in the accepting state set */
	private List<String> setOfAcceptingStatesRepr;

	/** List of representations of transitions */
	private List<List<String>> transitionsRepr;

	/**
	 * Creates a Turing Machine configuration object, throws if there is any null
	 */
	public TmRawConfiguration(List<String> setOfStatesRepr, List<String> inputAlphabetRepr,
			List<String> tapeAlphabetRepr, String initialStateRepr, String blankSymbolRepr,
			List<String> setOfAcceptingStatesRepr, List<List<String>> transitionsRepr)
			throws BadConfigurationException {
		setSetOfStatesRepr(setOfStatesRepr);
		setInputAlphabetRepr(inputAlphabetRepr);
		setTapeAlphabetRepr(tapeAlphabetRepr);
		setInitialStateRepr(initialStateRepr);
		setBlankSymbolRepr(blankSymbolRepr);
		setSetOfAcceptingStatesRepr(setOfAcceptingStatesRepr);
		setTransitionsRepr(transitionsRepr);
	}

	// Getters

	public List<String> getSetOfStatesRepr() {
		return setOfStatesRepr;
	}

	public List<String> getInputAlphabetRepr() {
		return inputAlphabetRepr;
	}

	public List<String> getTapeAlphabetRepr() {
		return tapeAlphabetRepr;
	}

	public String getInitialStateRepr() {
		return initialStateRepr;
	}

	public String getBlankSymbolRepr() {
		return blankSymbolRepr;
	}

	public List<String> getSetOfAcceptingStatesRepr() {
		return setOfAcceptingStatesRepr;
	}

	public List<List<String>> getTransitionsRepr() {
		return transitionsRepr;
	}

	// Setters

	private void setSetOfStatesRepr(List<String> setOfStatesRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Set of states");
		}
		this.setOfStatesRepr = setOfStatesRepr;
	}

	private void setInputAlphabetRepr(List<String> inputAlphabetRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Input alphabet");
		}
		this.inputAlphabetRepr = inputAlphabetRepr;
	}

	private void setTapeAlphabetRepr(List<String> tapeAlphabetRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Tape alphabet");
		}
		this.tapeAlphabetRepr = tapeAlphabetRepr;
	}

	private void setInitialStateRepr(String initialStateRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Initial state");
		}
		this.initialStateRepr = initialStateRepr;
	}

	private void setBlankSymbolRepr(String blankSymbolRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Blank symbol");
		}
		this.blankSymbolRepr = blankSymbolRepr;
	}

	private void setSetOfAcceptingStatesRepr(List<String> setOfAcceptingStatesRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("Set of accepting states");
		}
		this.setOfAcceptingStatesRepr = setOfAcceptingStatesRepr;
	}

	private void setTransitionsRepr(List<List<String>> transitionsRepr) throws BadConfigurationException {
		if (setOfStatesRepr == null) {
			throw new BadConfigurationException("List of Transitions");
		}

		for (int i = 0; i < transitionsRepr.size(); ++i) {
			if (transitionsRepr.get(i) == null) {
				throw new BadConfigurationException("Transition in position " + i);
			}
		}

		this.transitionsRepr = transitionsRepr;
	}

}
