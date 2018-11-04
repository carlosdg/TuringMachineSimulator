package me.carlosdg.turing_machine.definition.sets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.carlosdg.turing_machine.definition.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.definition.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.turing_machine.definition.symbols.Symbol;

/**
 * Base class for symbol sets. It is used as a set of symbols and a verifier to
 * know if a given string represents a symbol in the set or not
 *
 * @author Carlos Domínguez García
 */
public abstract class SymbolSet<T extends Symbol> implements Iterable<T> {

	/** Map from string representation to symbols represented by the string */
	private Map<String, T> mapReprToSymbol = new HashMap<>();

	/**
	 * Creates a set of symbols from the given collection of strings that represent
	 * symbols. Throws if there is any duplicate string
	 *
	 * @param symbolRepresentations Collection of strings that represent symbols.
	 * @throws DuplicatedStringInSetException If there is any duplicated string
	 */
	public SymbolSet(Collection<String> symbolRepresentations) throws DuplicatedStringInSetException {
		for (String repr : symbolRepresentations) {
			if (mapReprToSymbol.containsKey(repr)) {
				throw new DuplicatedStringInSetException(repr, symbolRepresentations);
			}

			mapReprToSymbol.put(repr, newSymbol(repr));
		}
	}

	/**
	 * Returns a symbol object represented by the given string if it belongs to this
	 * set or throws otherwise
	 *
	 * @param repr String representation of the symbol to get
	 * @return Symbol object represented by the given string
	 * @throws SymbolNotFoundInSetException If the given string doesn't represent
	 *                                      any symbol in this set
	 */
	public T getSymbol(String repr) throws SymbolNotFoundInSetException {
		T symbol = mapReprToSymbol.get(repr);
		if (symbol == null) {
			throw new SymbolNotFoundInSetException(repr, this);
		}
		return symbol;
	}

	/** Returns whether the given symbol is in the set or not */
	public boolean has(Symbol symbol) {
		return mapReprToSymbol.get(symbol.getRepresentation()) != null;
	}

	@Override
	public Iterator<T> iterator() {
		return mapReprToSymbol.values().iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ");
		mapReprToSymbol.values().forEach(symbol -> builder.append(symbol + " "));
		builder.append("}");
		return builder.toString();
	}

	/** Returns an instance of the symbols of this alphabet */
	abstract protected T newSymbol(String representation);

}
