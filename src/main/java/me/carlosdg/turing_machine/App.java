package me.carlosdg.turing_machine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import me.carlosdg.turing_machine.config.readers.TmRawConfigurationFileReader;
import me.carlosdg.turing_machine.definition.MultitapeTmDefinition;
import me.carlosdg.turing_machine.simulator.MultitapeTmSimulator;
import me.carlosdg.turing_machine.simulator.TmMemoryTape;
import me.carlosdg.turing_machine.simulator.TmMultipleMemoryTapes;
import me.carlosdg.turing_machine.symbols.AlphabetSymbol;

/**
 * Multitape Turing Machine simulator main. This application handles the parsing
 * of the input turing machine configuration and input word to simulate the
 * turing machine process of accepting/rejecting the given word
 *
 * @author Carlos Domínguez García
 */
public class App {
	public static void main(String[] args) {
		try {
			if (args.length != 2) {
				throw new IllegalArgumentException(
						"Usage: java Simulator.java <turing_machine.config.path> <path.to.input.strings.files>");
			}
			String configPath = args[0];
			String inputsPath = args[1];

			TmRawConfigurationFileReader rawConfigReader = new TmRawConfigurationFileReader(configPath);
			MultitapeTmDefinition definition = new MultitapeTmDefinition(rawConfigReader.getConfig());
			MultitapeTmSimulator simulator = new MultitapeTmSimulator(definition);
			List<List<AlphabetSymbol>> inputs = readStringsFromFile(inputsPath);

			boolean isAccepted = simulator.accepts(inputs, Optional.empty());
			showTapesTails(simulator.getTapes());
			System.out.println("Accepted: " + isAccepted);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/** Print to the console the tail of each tape (from head position to right) */
	private static void showTapesTails(TmMultipleMemoryTapes tapes) {
		for (TmMemoryTape tape : tapes) {
			for (int i = tape.getHeadPosition(); i <= tape.getTapeHighestPosition(); ++i) {
				System.out.print(tape.getSymbol(i) + " ");
			}
			System.out.println("");
		}

	}

	/** Read the words for each tape from the given file */
	private static List<List<AlphabetSymbol>> readStringsFromFile(String fileName) throws FileNotFoundException {
		Scanner reader = new Scanner(new BufferedReader(new FileReader(fileName)));
		try {
			List<List<AlphabetSymbol>> words = new ArrayList<>();

			while (reader.hasNextLine()) {
				String line = reader.nextLine().trim();
				String[] tokens = line.split("\\s+");

				List<AlphabetSymbol> inputWord = new ArrayList<>();
				if (tokens.length == 1 && tokens[0].trim().isEmpty()) {
					words.add(inputWord);
				} else {
					for (String token : tokens) {
						inputWord.add(new AlphabetSymbol(token));
					}
					words.add(inputWord);
				}
			}

			return words;
		} finally {
			reader.close();
		}
	}
}
