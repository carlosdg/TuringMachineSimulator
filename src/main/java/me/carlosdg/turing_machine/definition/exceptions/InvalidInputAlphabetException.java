package me.carlosdg.turing_machine.definition.exceptions;

import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

/**
 * Represents exceptions due to the input alphabet not being a subset of the
 * tape alphabet
 *
 * @author Carlos Domínguez García
 */
public class InvalidInputAlphabetException extends Exception {

	private static final long serialVersionUID = -1652913061475231254L;

	public InvalidInputAlphabetException(AlphabetSymbol symbolNotFoundInTapeAlphabet, Alphabet inputAlphabet,
			Alphabet tapeAlphabet) {
		super("Invalid symbol represented by '" + symbolNotFoundInTapeAlphabet
				+ "' it was found to be part of the input alphabet " + inputAlphabet
				+ " but not as part of the tape alphabet " + tapeAlphabet);
	}

}
