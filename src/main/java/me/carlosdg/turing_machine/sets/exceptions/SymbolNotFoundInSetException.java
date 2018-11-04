package me.carlosdg.turing_machine.sets.exceptions;

import me.carlosdg.turing_machine.sets.SymbolSet;

/**
 * Exception for when a given string doesn't represent any symbol of the target
 * set of symbols
 *
 * @author Carlos Domínguez García
 */
public class SymbolNotFoundInSetException extends Exception {

	private static final long serialVersionUID = 7250455007229293610L;

	/**
	 * Creates an instance of this exception
	 *
	 * @param stringReprOfNotFoundSymbol String representation of the symbol that
	 *                                   wasn't found in the alphabet
	 */
	public SymbolNotFoundInSetException(String stringReprOfNotFoundSymbol, SymbolSet<?> stringReprOfSet) {
		super("Symbol '" + stringReprOfNotFoundSymbol + "' in not found in " + stringReprOfSet);
	}

}
