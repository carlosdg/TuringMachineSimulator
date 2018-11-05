package me.carlosdg.turing_machine.transition_functions;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.State;
import me.carlosdg.turing_machine.utils.Pair;

public class MultitapeTransitionFunctionTest {

	MultitapeTransitionFunction uut = new MultitapeTransitionFunction();
	State startingState = new State("q0");
	List<AlphabetSymbol> inputSymbols = Arrays.asList(new AlphabetSymbol("a"), new AlphabetSymbol("b"));
	State outputState = new State("q1");
	List<Pair<AlphabetSymbol, TmMove>> pairs = Arrays.asList(new Pair<>(new AlphabetSymbol("a"), TmMove.LEFT),
			new Pair<>(new AlphabetSymbol("b"), TmMove.RIGHT));

	@Test
	public void shouldBeAbleToPutNewValues() {
		try {
			uut.put(startingState, inputSymbols, outputState, pairs);
			assertTrue(true);
		} catch (Exception e) {
			fail("An exception was thrown" + e.getMessage());
			e.printStackTrace();
		}
		assertThat(true).isEqualTo(true);
	}

	@Test
	public void newValuesShouldBeAccessible() {
		uut.put(startingState, inputSymbols, outputState, pairs);
		MultitapeTransitionOutput output = uut.get(startingState, inputSymbols);
		assertThat(output.getState()).isEqualTo(outputState);
		assertThat(output.getPairs()).containsExactly(new Pair<>(new AlphabetSymbol("a"), TmMove.LEFT),
				new Pair<>(new AlphabetSymbol("b"), TmMove.RIGHT));
	}

	@Test
	public void shouldReturnNullTryingToGetUnknownKey() {
		assertNull(uut.get(startingState, inputSymbols));
	}

}
