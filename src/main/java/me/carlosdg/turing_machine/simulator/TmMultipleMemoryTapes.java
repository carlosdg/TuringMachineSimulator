package me.carlosdg.turing_machine.simulator;

import java.util.ArrayList;
import java.util.List;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.utils.Pair;

/**
 * Convenient class to handle multiple memory tapes
 *
 * @author Carlos Domínguez García
 */
public class TmMultipleMemoryTapes {

	private List<TmMemoryTape> tapes = new ArrayList<>();

	/**
	 * Creates an instance of this class with N number of tapes with the given
	 * symbol as blank symbol
	 */
	public TmMultipleMemoryTapes(int numberOfTapes, AlphabetSymbol blankSymbol) {
		for (int i = 0; i < numberOfTapes; ++i) {
			tapes.add(new TmMemoryTape(blankSymbol));
		}
	}

	/**
	 * Sets the inputs of the tapes to the respective given input. Throws if the
	 * given number of inputs is different than the number of tapes
	 */
	public void setNewInput(List<List<AlphabetSymbol>> inputs) {
		if (inputs.size() != tapes.size()) {
			throw new IllegalArgumentException(
					"Invalid number of inputs. Expected " + tapes.size() + " but " + inputs.size() + " where given");
		}
		for (int i = 0; i < tapes.size(); ++i) {
			tapes.get(i).setNewInput(inputs.get(i));
		}
	}

	/** Reads a symbol from each tape */
	public List<AlphabetSymbol> read() {
		List<AlphabetSymbol> symbols = new ArrayList<>();
		for (TmMemoryTape tape : tapes) {
			symbols.add(tape.read());
		}

		return symbols;
	}

	/**
	 * Perform the given operations to their respective tape. Throws if the given
	 * number of operations is different than the number of tapes
	 */
	public void write(List<Pair<AlphabetSymbol, TmMove>> operations) {
		if (operations.size() != tapes.size()) {
			throw new IllegalArgumentException("Invalid number of inputs. Expected " + tapes.size() + " but "
					+ operations.size() + " where given");
		}
		for (int i = 0; i < tapes.size(); ++i) {
			tapes.get(i).write(operations.get(i));
		}
	}

}
