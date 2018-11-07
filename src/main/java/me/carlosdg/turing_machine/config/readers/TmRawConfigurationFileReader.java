package me.carlosdg.turing_machine.config.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import me.carlosdg.turing_machine.config.TmRawConfiguration;
import me.carlosdg.turing_machine.config.TmRawConfigurationBuilder;
import me.carlosdg.turing_machine.config.exceptions.BadConfigurationException;

public class TmRawConfigurationFileReader {

	/** Regular expression for single line comment delimiter */
	static private String SINGLE_LINE_COMMENT_DELIMITER_REGEX = "#";
	/** Regular expression for token delimiter */
	static private String TOKEN_DELIMITER_REGEX = "\\s+";

	private Scanner fileReader;
	private TmRawConfigurationBuilder configBuilder;

	/**
	 * Reads the given file initializing the TM raw configuration object
	 *
	 * @param filePath The path of the file containing the TM configuration
	 * @throws IOException If the given file path is not found or there is an error
	 *                     reading the file
	 */
	public TmRawConfigurationFileReader(String filePath) throws IOException {
		fileReader = new Scanner(new BufferedReader(new FileReader(filePath)));
		configBuilder = new TmRawConfigurationBuilder();

		// Parse the file
		parseStates();
		parseInputAlphabet();
		parseTapeAlphabet();
		parseInitialState();
		parseBlankSymbol();
		parseAcceptingStates();
		parseTransitions();
	}

	/** Returns the TM raw configuration object obtained from the file read */
	public TmRawConfiguration getConfig() throws BadConfigurationException {
		return configBuilder.build();
	}

	/**
	 * Strips comments from the line and returns the array of tokens (understanding
	 * as token words separated by TOKEN_DELIMITER_REGEX)
	 *
	 * @param line Line to parse
	 * @return Array of tokens gotten from parsing the given line
	 */
	private String[] parseLine(String line) {
		String lineWithoutComments = line.split(SINGLE_LINE_COMMENT_DELIMITER_REGEX)[0].trim();

		// If there are no tokens -> return an empty array
		if (lineWithoutComments.length() > 0) {
			return lineWithoutComments.split(TOKEN_DELIMITER_REGEX);
		} else {
			return new String[] {};
		}

	}

	/**
	 * Parses the next lines gotten from the file reader and returns the first one
	 * that has at least one token ignoring white spaces and comments
	 *
	 * @return An Optional array of tokens from the first non empty line, empty if
	 *         the end of the file is reached
	 */
	private Optional<String[]> getNextNonEmptyParsedLine() {
		String[] currentLineTokens;

		while (fileReader.hasNextLine()) {
			currentLineTokens = parseLine(fileReader.nextLine());
			if (currentLineTokens.length > 0) {
				return Optional.of(currentLineTokens);
			}
		}

		return Optional.empty();
	}

	/**
	 * Returns the tokens from the next non empty line or throws an IOException with
	 * the given message
	 *
	 * @param exceptionMessage Message of the IOException to throw if there are no
	 *                         more lines with tokens
	 * @return An array of tokens of the next non empty line
	 * @throws IOException If there are no more lines with tokens
	 */
	private String[] getNextNonEmptyParsedLineOrThrow(String exceptionMessage) throws IOException {
		return getNextNonEmptyParsedLine().orElseThrow(() -> new IOException(exceptionMessage));
	}

	/**
	 * Treats the next non empty line as the list of states and adds them to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseStates() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the set of states");
		configBuilder.setStatesRepr(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the input alphabet and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseInputAlphabet() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the input alphabet");
		configBuilder.setInputAlphabetRepr(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the tape alphabet and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseTapeAlphabet() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the tape alphabet");
		configBuilder.setTapeAlphabetRepr(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the starting state and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines or if the parsed
	 *                     line has more than one token
	 */
	private void parseInitialState() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the initial state");
		if (tokens.length != 1) {
			throw new IOException("Invalid number of starting states, expected 1 but found " + tokens.length);
		}
		configBuilder.setInitialStateRepr(tokens[0]);
	}

	/**
	 * Treats the next non empty line as the blank symbol and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines or if the parsed
	 *                     line has more than one token
	 */
	private void parseBlankSymbol() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the blank symbol");
		if (tokens.length != 1) {
			throw new IOException("Invalid number of initial stack top, expected 1 but found " + tokens.length);
		}
		configBuilder.setBlankSymbolRepr(tokens[0]);
	}

	/**
	 * Treats the next non empty line as the list of accepting states and adds them
	 * to the configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseAcceptingStates() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the set of accepting states");
		configBuilder.setAcceptingStatesRepr(Arrays.asList(tokens));
	}

	/**
	 * Treats the rest of the non empty lines as the transition definitions and adds
	 * them to the configuration builder
	 */
	private void parseTransitions() {
		Optional<String[]> maybeTransition = getNextNonEmptyParsedLine();
		List<List<String>> transitionsRepr = new ArrayList<>();

		while (maybeTransition.isPresent()) {
			transitionsRepr.add(Arrays.asList(maybeTransition.get()));
			maybeTransition = getNextNonEmptyParsedLine();
		}

		configBuilder.setTransitionsRepr(transitionsRepr);
	}

}
