package me.carlosdg.turing_machine.sets.exceptions;

import java.util.Collection;

/**
 * Exception for when a duplicated string is found wanting to be part of a set
 * of symbols
 *
 * @author Carlos Domínguez García
 */
public class DuplicatedStringInSetException extends Exception {

	private static final long serialVersionUID = -6985536630123145670L;

	/**
	 * Creates an instance of this exception
	 *
	 * @param duplicatedString Duplicated string found that caused the exception
	 * @param collection       Collection where the duplicated string was found
	 */
	public DuplicatedStringInSetException(String duplicatedString, Collection<String> collection) {
		super("Found at least '" + duplicatedString + "' to be duplicated in " + collectionRepresentation(collection));
	}

	/** Helper method to get a string representation of a collection of strings */
	private static String collectionRepresentation(Collection<String> collection) {
		StringBuilder builder = new StringBuilder("{ ");
		for (String element : collection) {
			builder.append(element + " ");
		}
		builder.append("}");
		return builder.toString();
	}

}
