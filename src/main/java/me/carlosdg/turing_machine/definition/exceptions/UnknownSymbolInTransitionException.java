package me.carlosdg.turing_machine.definition.exceptions;

import java.util.List;

import me.carlosdg.turing_machine.sets.SymbolSet;

/**
 * Represents an exception due to a symbol not being recognized in a transition
 *
 * @author Carlos Domínguez García
 */
public class UnknownSymbolInTransitionException extends Exception {

	private static final long serialVersionUID = 7077842814461012683L;

	public UnknownSymbolInTransitionException(String unknownSymbolRepr, List<String> transitionRepr,
			SymbolSet<?> allowedSymbols) {
		super("Unknown symbol represented by '" + unknownSymbolRepr + "' in transition '"
				+ String.join(" ", transitionRepr) + "'. It must be one of the following set: " + allowedSymbols);
	}

}
