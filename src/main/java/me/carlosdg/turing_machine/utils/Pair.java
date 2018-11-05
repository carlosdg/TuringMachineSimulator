package me.carlosdg.turing_machine.utils;

import java.util.Objects;

/**
 * Generic pair class
 *
 * @author Carlos Domínguez García
 */
public class Pair<U, V> {
	private U first;
	private V second;

	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}

	public V getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFirst(), getSecond());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Pair<?, ?> other = (Pair<?, ?>) obj;
		return getFirst().equals(other.getFirst()) && getSecond().equals(other.getSecond());
	}

}