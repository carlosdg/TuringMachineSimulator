package me.carlosdg.turing_machine.simulator.spies;

import me.carlosdg.turing_machine.simulator.TmMultipleMemoryTapes;
import me.carlosdg.turing_machine.symbols.State;

/**
 * Interface for defining "spy" objects whose methods will be called on certain
 * parts of the Multitape Turing Machine algorithm to accept or reject an input
 * string
 *
 * @author Carlos Domínguez García
 */
public interface MultitapeTmExcecutionSpy {

	public void newIteration(State currentState, TmMultipleMemoryTapes tapes);

}
