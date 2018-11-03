package me.carlosdg.turing_machine.definition.sets;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.Test;

import me.carlosdg.turing_machine.definition.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.definition.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.turing_machine.definition.symbols.AlphabetSymbol;

public class AlphabetTest {

	@Test
	public void shouldBeAbleToCreateAlphabetFromSingleSymbol()
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		Alphabet alphabet = new Alphabet(Arrays.asList("s"));
		assertThat(alphabet.getSymbol("s")).isEqualTo(new AlphabetSymbol("s"));
	}

	@Test
	public void shouldThrowDuplicatedStringWith2SameStrings() {
		assertThatExceptionOfType(DuplicatedStringInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("s", "s"));
		});
	}

	@Test
	public void shouldThrowDuplicatedStringWith2SameStringsWithMoreDifferent() {
		assertThatExceptionOfType(DuplicatedStringInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("s", "a", "b", "c", "s", "d"));
		});
	}

	@Test
	public void shouldThrowSymbolNotFoundOnEmptyAlphabet() {
		assertThatExceptionOfType(SymbolNotFoundInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList()).getSymbol("s");
		});
	}

	@Test
	public void shouldThrowSymbolNotFoundOnPopulatedAlphabetWithUnknownSymbol() {
		assertThatExceptionOfType(SymbolNotFoundInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("a", "b", "c")).getSymbol("s");
		});
	}

}
