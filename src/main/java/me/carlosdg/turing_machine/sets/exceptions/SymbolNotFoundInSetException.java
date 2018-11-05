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

	private String stringReprOfNotFoundSymbol;
	private SymbolSet<?> set;

	/**
	 * Creates an instance of this exception
	 *
	 * @param stringReprOfNotFoundSymbol String representation of the symbol that
	 *                                   wasn't found in the alphabet
	 * @param set                        Set where the symbol wasn't found
	 */
	public SymbolNotFoundInSetException(String stringReprOfNotFoundSymbol, SymbolSet<?> set) {
		super("Symbol '" + stringReprOfNotFoundSymbol + "' in not found in " + set);

		this.stringReprOfNotFoundSymbol = stringReprOfNotFoundSymbol;
		this.set = set;
	}

	public String getStringReprOfNotFoundSymbol() {
		return stringReprOfNotFoundSymbol;
	}

	public SymbolSet<?> getSet() {
		return set;
	}

}
