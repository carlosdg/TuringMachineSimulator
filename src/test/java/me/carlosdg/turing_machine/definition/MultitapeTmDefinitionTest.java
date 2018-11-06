package me.carlosdg.turing_machine.definition;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.config.exceptions.BadConfigurationException;
import me.carlosdg.turing_machine.definition.exceptions.BlankSymbolFoundInInputAlphabetException;
import me.carlosdg.turing_machine.definition.exceptions.BlankSymbolNotFoundInTapeAlphabetException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidAcceptingStatesSetException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidInitialStateException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidInputAlphabetException;
import me.carlosdg.turing_machine.definition.exceptions.InvalidNumberOfTapesException;
import me.carlosdg.turing_machine.definition.exceptions.UnknownSymbolInTransitionException;

public class MultitapeTmDefinitionTest {
	List<String> setOfStatesRepr;
	List<String> setOfAcceptingStatesRepr;
	String initialStateRepr = "q0";
	List<String> tapeAlphabetRepr;
	List<String> inputAlphabetRepr;
	String blankSymbolRepr;
	List<List<String>> transitionsRepr;
	TmRawConfiguration tmAcceptsWordsWithSomeZ;

	@Before
	public void init() throws BadConfigurationException {
		setOfStatesRepr = Arrays.asList(initialStateRepr, "q1");
		setOfAcceptingStatesRepr = Arrays.asList("q1");
		initialStateRepr = "q0";
		tapeAlphabetRepr = Arrays.asList("x", "y", "z", ".");
		inputAlphabetRepr = Arrays.asList("x", "y", "z");
		blankSymbolRepr = ".";
		transitionsRepr = Arrays.asList(Arrays.asList("q0", "x", "q0", "x", "R"),
				Arrays.asList("q0", "y", "q0", "y", "R"), Arrays.asList("q0", "z", "q1", "z", "R"));

		tmAcceptsWordsWithSomeZ = new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
				initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr, transitionsRepr);
	}

	@Test
	public void noExceptionShouldBeThrownWithGoodConfiguration() {
		try {
			new MultitapeTmDefinition(tmAcceptsWordsWithSomeZ);
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected no exception to be thrown with a good configuration. " + e.getMessage());
		}
	}

	@Test
	public void initialStateReprShouldMatchTheOneOnConfiguration() throws Exception {
		MultitapeTmDefinition definition = new MultitapeTmDefinition(tmAcceptsWordsWithSomeZ);
		assertThat(definition.getInitialState().getRepresentation()).isEqualTo(initialStateRepr);
	}

	// TODO: check that the representation of every element in the definition match
	// the ones in the configuration

	@Test
	public void shouldThrowWithUnknownInitialState() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr, "U",
					blankSymbolRepr, setOfAcceptingStatesRepr, transitionsRepr));

		}).isInstanceOf(InvalidInitialStateException.class);
	}

	@Test
	public void shouldThrowWithAnUnknownStateInAcceptingSet() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, blankSymbolRepr, Arrays.asList("_"), transitionsRepr));
		}).isInstanceOf(InvalidAcceptingStatesSetException.class);
	}

	@Test
	public void shouldThrowWithBlankSymbolNotInTapeAlphabet() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, "1", setOfAcceptingStatesRepr, transitionsRepr));
		}).isInstanceOf(BlankSymbolNotFoundInTapeAlphabetException.class);
	}

	@Test
	public void shouldThrowWithBlankSymbolInInputAlphabet() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, "z", setOfAcceptingStatesRepr, transitionsRepr));
		}).isInstanceOf(BlankSymbolFoundInInputAlphabetException.class);
	}

	@Test
	public void shouldThrowWithAnInputSymbolNotInTapeAlphabet() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, Arrays.asList("x", "y", "z", "a"),
					tapeAlphabetRepr, initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr, transitionsRepr));
		}).isInstanceOf(InvalidInputAlphabetException.class);
	}

	@Test
	public void shouldNotThrowWithATapeSymbolNotInInputAlphabet() {
		try {
			new MultitapeTmDefinition(
					new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, Arrays.asList("x", "y", "z", "a", "."),
							initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr, transitionsRepr));
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected not to throw. " + e.getMessage());
		}
	}

	@Test
	public void shouldThrowWithTransitionWithUnknownInputState() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr,
					Arrays.asList(Arrays.asList("p", "x", "q0", "x", "R"))));
		}).isInstanceOf(UnknownSymbolInTransitionException.class).hasMessageContaining("'p'");
	}

	@Test
	public void shouldThrowWithTransitionWithUnknownOutputState() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr,
					Arrays.asList(Arrays.asList("q0", "x", "qwe", "x", "R"))));
		}).isInstanceOf(UnknownSymbolInTransitionException.class).hasMessageContaining("'qwe'");
	}

	@Test
	public void shouldFailToParseTransitionWithMoreNumberOfTapesInOutput() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr,
					Arrays.asList(Arrays.asList("q0", "x", "q0", "x", "R", "x", "R"))));
		}).isInstanceOf(InvalidNumberOfTapesException.class).hasMessageContaining("Expected 1 but found 2");
	}

	@Test
	public void shouldFailToParseTransitionWithLessNumberOfTapesInOutput() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(
					new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr, initialStateRepr,
							blankSymbolRepr, setOfAcceptingStatesRepr, Arrays.asList(Arrays.asList("q0", "x", "q0"))));
		}).isInstanceOf(InvalidNumberOfTapesException.class).hasMessageContaining("Expected 1 but found 0");
	}

	@Test
	public void shouldFailToParseTransitionWithMoreNumberOfTapesInInput() throws Exception {
		assertThatThrownBy(() -> {
			new MultitapeTmDefinition(new TmRawConfiguration(setOfStatesRepr, inputAlphabetRepr, tapeAlphabetRepr,
					initialStateRepr, blankSymbolRepr, setOfAcceptingStatesRepr,
					Arrays.asList(Arrays.asList("q0", "x", "y", "q0", "x", "R"))));
		}).isInstanceOf(InvalidNumberOfTapesException.class).hasMessageContaining("Expected 1");
	}

}
