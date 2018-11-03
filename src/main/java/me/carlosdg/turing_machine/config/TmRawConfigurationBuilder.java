package me.carlosdg.turing_machine.config;

import java.util.List;

import me.carlosdg.turing_machine.config.exceptions.BadConfigurationException;

/**
 * TmRawConfiguration Builder, to make TmRawConfiguration objects creation
 * easier and cleaner
 *
 * @author Carlos Domínguez García
 */
public class TmRawConfigurationBuilder {

	// Arguments to give to the TmRawConfiguration constructor

	private List<String> setOfStatesRepr;
	private List<String> inputAlphabetRepr;
	private List<String> tapeAlphabetRepr;
	private String initialStateRepr;
	private String blankSymbolRepr;
	private List<String> setOfAcceptingStatesRepr;
	private List<List<String>> transitionsRepr;

	/**
	 * Returns the instance of TmRawConfiguration from the given parameters. Throws
	 * if there is any null
	 */
	public TmRawConfiguration build() throws BadConfigurationException {
		return new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr, initialStateRepr,
				blankSymbolRepr, setOfAcceptingStatesRepr, transitionsRepr);
	}

	public TmRawConfigurationBuilder setStatesRepr(List<String> newStatesRepresentation) {
		setOfStatesRepr = newStatesRepresentation;
		return this;
	}

	public TmRawConfigurationBuilder setInputAlphabetRepr(List<String> newInputAlphabetRepr) {
		inputAlphabetRepr = newInputAlphabetRepr;
		return this;
	}

	public TmRawConfigurationBuilder setTapeAlphabetRepr(List<String> newTapeAlphabetRepr) {
		tapeAlphabetRepr = newTapeAlphabetRepr;
		return this;
	}

	public TmRawConfigurationBuilder setInitialStateRepr(String newInitialStateRepr) {
		initialStateRepr = newInitialStateRepr;
		return this;
	}

	public TmRawConfigurationBuilder setBlankSymbolRepr(String newBlankSymbolRepr) {
		blankSymbolRepr = newBlankSymbolRepr;
		return this;
	}

	public TmRawConfigurationBuilder setTransitionsRepr(List<List<String>> newTransitionsRepr) {
		transitionsRepr = newTransitionsRepr;
		return this;
	}

}
