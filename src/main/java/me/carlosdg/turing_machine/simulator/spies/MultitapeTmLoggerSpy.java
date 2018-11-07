package me.carlosdg.turing_machine.simulator.spies;

import me.carlosdg.turing_machine.simulator.TmMemoryTape;
import me.carlosdg.turing_machine.simulator.TmMultipleMemoryTapes;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Represents a spy that logs the progress of the machine to the console
 *
 * @author Carlos Domínguez García
 */
public class MultitapeTmLoggerSpy implements MultitapeTmExcecutionSpy {

	@Override
	public void newIteration(State currentState, TmMultipleMemoryTapes tapes) {
		for (TmMemoryTape tape : tapes) {
			int headPos = tape.getHeadPosition();
			for (int i = tape.getTapeLowestPosition(); i <= tape.getTapeHighestPosition(); ++i) {
				if (i == headPos) {
					System.out.print(" " + currentState + " ");
				}
				System.out.print(" " + tape.getSymbol(i) + " ");
			}
			System.out.println("");
		}
	}

}
