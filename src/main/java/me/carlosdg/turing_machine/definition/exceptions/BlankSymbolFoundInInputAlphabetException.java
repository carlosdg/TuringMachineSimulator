package me.carlosdg.turing_machine.definition.exceptions;

import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

/**
 * Represents exceptions due to the blank symbol being part of the input
 * alphabet
 * 
 * @author Carlos Domínguez García
 */
public class BlankSymbolFoundInInputAlphabetException extends Exception {

	private static final long serialVersionUID = -6580446082768642883L;

	public BlankSymbolFoundInInputAlphabetException(AlphabetSymbol blankSymbol, Alphabet inputAlphabet) {
		super("Blank symbol represented with '" + blankSymbol + "' was found in the Input Alphabet " + inputAlphabet);
	}

}
