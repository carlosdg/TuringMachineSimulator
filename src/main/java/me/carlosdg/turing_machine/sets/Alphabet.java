package me.carlosdg.turing_machine.sets;

import java.util.Collection;

import me.carlosdg.turing_machine.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

/**
 * Alphabet class to represent a set of input symbols or tape symbols
 *
 * @author Carlos Domínguez García
 */
public class Alphabet extends SymbolSet<AlphabetSymbol> {

	/** @see me.carlosdg.turing_machine.sets.SymbolSet */
	public Alphabet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		super(symbolRepresentations);
	}

	@Override
	protected AlphabetSymbol newSymbol(String representation) {
		return new AlphabetSymbol(representation);
	}

}
