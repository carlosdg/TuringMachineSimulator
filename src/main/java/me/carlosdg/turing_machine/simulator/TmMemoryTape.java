package me.carlosdg.turing_machine.simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.utils.Pair;

/**
 * Turing Machine memory tape.
 *
 * @author Carlos Domínguez García
 */
public class TmMemoryTape {

	private AlphabetSymbol blankSymbol;
	private Map<Integer, AlphabetSymbol> tape = new HashMap<>();
	private int currentPosition = 0;

	/**
	 * Creates an instance of a Turing Machine memory tape
	 *
	 * @param blankSymbol Blank symbol of the tape
	 */
	public TmMemoryTape(AlphabetSymbol blankSymbol) {
		this.blankSymbol = blankSymbol;
	}

	/** Clears the tape and writes the given symbols to it */
	void setNewInput(List<AlphabetSymbol> inputSymbols) {
		tape.clear();
		for (int i = 0; i < inputSymbols.size(); ++i) {
			tape.put(i, inputSymbols.get(i));
		}
		currentPosition = 0;
	}

	/**
	 * Returns the symbol in the tape that is being pointed by the head of the
	 * machine
	 */
	AlphabetSymbol read() {
		return tape.getOrDefault(currentPosition, blankSymbol);
	}

	/**
	 * Writes the given symbol to the position being pointed by the machine head.
	 * And updates the position of the head according to the given move
	 */
	void write(Pair<AlphabetSymbol, TmMove> operation) {
		tape.put(currentPosition, operation.getFirst());
		currentPosition += operation.getSecond().getMovementOffset();
	}

}
