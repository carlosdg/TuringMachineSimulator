package me.carlosdg.turing_machine.sets;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import me.carlosdg.turing_machine.sets.Alphabet;
import me.carlosdg.turing_machine.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.turing_machine.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

public class AlphabetTest {

	@Test
	public void shouldBeAbleToCreateAlphabetFromSingleSymbol()
			throws DuplicatedStringInSetException, SymbolNotFoundInSetException {
		Alphabet alphabet = new Alphabet(Arrays.asList("s"));
		assertThat(alphabet.getSymbol("s")).isEqualTo(new AlphabetSymbol("s"));
	}

	@Test
	public void shouldReturnStringRepresentationOf3Elements() throws DuplicatedStringInSetException {
		List<String> symbolRepresentations = Arrays.asList("a", "b", "c");
		Alphabet alphabet = new Alphabet(symbolRepresentations);
		assertThat(alphabet.toString()).isEqualTo("{ a b c }");
	}

	@Test
	public void shouldReturnStringRepresentationOf0Elements() throws DuplicatedStringInSetException {
		List<String> symbolRepresentations = Arrays.asList();
		Alphabet alphabet = new Alphabet(symbolRepresentations);
		assertThat(alphabet.toString()).isEqualTo("{ }");
	}

	@Test
	public void shouldReturnStringRepresentationOf1Element() throws DuplicatedStringInSetException {
		List<String> symbolRepresentations = Arrays.asList("a");
		Alphabet alphabet = new Alphabet(symbolRepresentations);
		assertThat(alphabet.toString()).isEqualTo("{ a }");
	}

	@Test
	public void shouldThrowDuplicatedStringWith2SameStrings() {
		assertThatExceptionOfType(DuplicatedStringInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("s", "s"));
		}).withMessageContaining("'s'").withMessageContaining("s s");
	}

	@Test
	public void shouldThrowDuplicatedStringWith2SameStringsWithMoreDifferent() {
		assertThatExceptionOfType(DuplicatedStringInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("s", "a", "b", "c", "s", "d"));
		}).withMessageContaining("'s'").withMessageContaining("s a b c s d");
	}

	@Test
	public void shouldThrowSymbolNotFoundOnEmptyAlphabet() {
		assertThatExceptionOfType(SymbolNotFoundInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList()).getSymbol("s");
		}).withMessageContaining("'s'").withMessageContaining("{ }");
	}

	@Test
	public void shouldThrowSymbolNotFoundOnPopulatedAlphabetWithUnknownSymbol() {
		assertThatExceptionOfType(SymbolNotFoundInSetException.class).isThrownBy(() -> {
			new Alphabet(Arrays.asList("a", "b", "c")).getSymbol("s");
		}).withMessageContaining("'s'").withMessageContaining("a b c");
	}

	@Test
	public void shouldBeIterable() throws DuplicatedStringInSetException {
		List<String> symbolRepresentations = Arrays.asList("a", "b", "c");
		Alphabet alphabet = new Alphabet(symbolRepresentations);
		int i = 0;
		for (AlphabetSymbol symbol : alphabet) {
			assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentations.get(i));
			++i;
		}
	}

}
