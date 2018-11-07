package me.carlosdg.turing_machine.simulator;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import me.carlosdg.turing_machine.movement.TmMove;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.utils.Pair;

public class TmMemoryTapeTest {

	TmMemoryTape uut;
	AlphabetSymbol blank;

	@Before
	public void init() {
		blank = new AlphabetSymbol(".");
		uut = new TmMemoryTape(blank);
	}

	@Test
	public void whenEmptyShouldReturnBlankOnRead() {
		assertThat(uut.read()).isEqualTo(blank);
	}

	@Test
	public void shouldReturnBlankOnReadAfterMovingToLeftWithNoMoreInput() {
		uut.write(new Pair<>(new AlphabetSymbol("a"), TmMove.RIGHT));
		assertThat(uut.read()).isEqualTo(blank);
	}

	@Test
	public void shouldReturnSymbolOnReadAfterWrittingWithStopMove() {
		AlphabetSymbol a = new AlphabetSymbol("a");
		uut.write(new Pair<>(a, TmMove.STOP));
		assertThat(uut.read()).isEqualTo(a);
	}

	@Test
	public void shouldReturnSymbolOnReadAfterSettingInput() {
		uut.setNewInput(Arrays.asList(new AlphabetSymbol("a")));
		assertThat(uut.read()).isEqualTo(new AlphabetSymbol("a"));
	}

}
