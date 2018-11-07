package me.carlosdg.turing_machine.simulator;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.definition.MultitapeTmDefinition;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

public class MultitapeTmSimulatorTest {

	List<String> setOfStatesRepr;
	List<String> setOfAcceptingStatesRepr;
	String initialStateRepr = "q0";
	List<String> tapeAlphabetRepr;
	List<String> inputAlphabetRepr;
	String blankSymbolRepr;
	List<List<String>> transitionsRepr;
	TmRawConfiguration tmAcceptsWordsWithSomeZ;
	MultitapeTmDefinition definition;
	MultitapeTmSimulator simulator;

	@Before
	public void init() throws Exception {
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

		definition = new MultitapeTmDefinition(tmAcceptsWordsWithSomeZ);
		simulator = new MultitapeTmSimulator(definition);
	}

	@Test
	public void shouldAcceptStringWithLastZ() {
		List<List<AlphabetSymbol>> inputs = Arrays
				.asList(Arrays.asList(new AlphabetSymbol("x"), new AlphabetSymbol("y"), new AlphabetSymbol("z")));

		assertThat(simulator.accepts(inputs, Optional.empty())).isTrue();
	}

	@Test
	public void shouldAcceptStringOnlyWithZ() {
		List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList(new AlphabetSymbol("z")));

		assertThat(simulator.accepts(inputs, Optional.empty())).isTrue();
	}

	@Test
	public void shouldNotAcceptEmptyString() {
		List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList());

		assertThat(simulator.accepts(inputs, Optional.empty())).isFalse();
	}

	@Test
	public void shouldNotAcceptStringWithSingleX() {
		List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList(new AlphabetSymbol("x")));

		assertThat(simulator.accepts(inputs, Optional.empty())).isFalse();
	}

	@Test
	public void shouldNotAcceptStringWithSingleY() {
		List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList(new AlphabetSymbol("y")));

		assertThat(simulator.accepts(inputs, Optional.empty())).isFalse();
	}

	@Test
	public void shouldThrowWithBlankOnInput() {
		assertThatThrownBy(() -> {
			List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList(new AlphabetSymbol(".")));
			simulator.accepts(inputs, Optional.empty());
		}).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid input symbol");
	}

	@Test
	public void shouldThrowWithUnknownSymbolOnInput() {
		assertThatThrownBy(() -> {
			List<List<AlphabetSymbol>> inputs = Arrays.asList(Arrays.asList(new AlphabetSymbol("K")));
			simulator.accepts(inputs, Optional.empty());
		}).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid input symbol");
	}

}
