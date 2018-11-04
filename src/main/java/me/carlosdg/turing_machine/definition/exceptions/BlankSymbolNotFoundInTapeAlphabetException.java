package me.carlosdg.turing_machine.definition.exceptions;

import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

/**
 * Represents exceptions due to the blank symbol not being part of the tape
 * alphabet
 *
 * @author Carlos Domínguez García
 */
public class BlankSymbolNotFoundInTapeAlphabetException extends Exception {

	private static final long serialVersionUID = 8002364182748298086L;

	public BlankSymbolNotFoundInTapeAlphabetException(AlphabetSymbol blankSymbol, Alphabet tapeAlphabet) {
		super("Blank symbol represented with '" + blankSymbol + "' was not found in the Tape Alphabet " + tapeAlphabet);
	}

}
