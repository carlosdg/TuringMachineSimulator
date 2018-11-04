package me.carlosdg.turing_machine.symbols;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import me.carlosdg.turing_machine.symbols.AlphabetSymbol;
import me.carlosdg.turing_machine.symbols.Symbol;

public class AlphabetSymbolTest {

	@Test
	public void shouldBeAbleToCreateAlphabetSymbolFromSingleCharacterString() {
		String symbolRepresentation = "s";
		Symbol symbol = new AlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void shouldBeAbleToCreateAlphabetSymbolFromMultipleCharactersString() {
		String symbolRepresentation = "q0";
		Symbol symbol = new AlphabetSymbol(symbolRepresentation);
		assertThat(symbol.getRepresentation()).isEqualTo(symbolRepresentation);
	}

	@Test
	public void alphabetSymbolShouldNotBeEqualToAlphabetSymbolWithDifferentRepresentation() {
		Symbol iSymbol = new AlphabetSymbol("q");
		Symbol tSymbol = new AlphabetSymbol("S");
		assertThat(iSymbol).isNotEqualTo(tSymbol);
	}

	@Test
	public void alphabetSymbolShouldBeEqualToAlphabetSymbolWithSameRepresentation() {
		Symbol iSymbol = new AlphabetSymbol("a");
		Symbol tSymbol = new AlphabetSymbol("a");
		assertThat(iSymbol).isEqualTo(tSymbol);
	}

	@Test
	public void alphabetSymbolShouldBeEqualToAlphabetSymbolWithSameReference() {
		Symbol s1 = new AlphabetSymbol("a");
		Symbol s2 = s1;
		assertThat(s1).isEqualTo(s2);
	}

	@Test
	public void alphabetSymbolShouldNotBeEqualToAlphabetWithDifferentRepresentation() {
		Symbol s1 = new AlphabetSymbol("S");
		Symbol s2 = new AlphabetSymbol("R");
		assertThat(s1).isNotEqualTo(s2);
	}

}
