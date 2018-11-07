package me.carlosdg.turing_machine.simulator;

import java.util.HashMap;
import java.util.Iterator;
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
public class TmMemoryTape implements Iterable<AlphabetSymbol> {

	private AlphabetSymbol blankSymbol;
	private Map<Integer, AlphabetSymbol> tape = new HashMap<>();
	private int currentPosition = 0;
	private int minPosition = 0;
	private int maxPosition = 0;

	/**
	 * Creates an instance of a Turing Machine memory tape
	 *
	 * @param blankSymbol Blank symbol of the tape
	 */
	public TmMemoryTape(AlphabetSymbol blankSymbol) {
		this.blankSymbol = blankSymbol;
	}

	/** Returns the current position of the head in the tape */
	public int getHeadPosition() {
		return currentPosition;
	}

	/**
	 * Returns the lowest position visited by the head or with a symbol in the tape
	 */
	public int getTapeLowestPosition() {
		return minPosition;
	}

	/**
	 * Returns the highest position visited by the head or with a symbol in the tape
	 */
	public int getTapeHighestPosition() {
		return maxPosition;
	}

	/** Returns the symbol at the given position in the tape */
	public AlphabetSymbol getSymbol(int position) {
		return tape.getOrDefault(position, blankSymbol);
	}

	/** Clears the tape and writes the given symbols to it */
	public void setNewInput(List<AlphabetSymbol> inputSymbols) {
		tape.clear();
		for (int i = 0; i < inputSymbols.size(); ++i) {
			tape.put(i, inputSymbols.get(i));
		}
		currentPosition = 0;
		minPosition = 0;
		maxPosition = inputSymbols.size() - 1;
	}

	/**
	 * Returns the symbol in the tape that is being pointed by the head of the
	 * machine
	 */
	public AlphabetSymbol read() {
		return getSymbol(currentPosition);
	}

	/**
	 * Writes the given symbol to the position being pointed by the machine head.
	 * And updates the position of the head according to the given move
	 */
	public void write(Pair<AlphabetSymbol, TmMove> operation) {
		tape.put(currentPosition, operation.getFirst());
		updatePosition(operation.getSecond());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(" ");

		for (AlphabetSymbol symbol : this) {
			builder.append(symbol.getRepresentation() + " ");
		}

		return builder.toString();
	}

	@Override
	public Iterator<AlphabetSymbol> iterator() {
		return new Iterator<AlphabetSymbol>() {

			private int i = getTapeLowestPosition();

			@Override
			public boolean hasNext() {
				return i < getTapeHighestPosition();
			}

			@Override
			public AlphabetSymbol next() {
				return getSymbol(i++);
			}

		};
	}

	/**
	 * Updates the head position according to the given movement. Also keeps track
	 * of the farthest position at the left and right of the tape
	 */
	private void updatePosition(TmMove posOffset) {
		currentPosition += posOffset.getMovementOffset();

		if (currentPosition < minPosition) {
			minPosition = currentPosition;
		} else if (currentPosition > maxPosition) {
			maxPosition = currentPosition;
		}
	}

}
