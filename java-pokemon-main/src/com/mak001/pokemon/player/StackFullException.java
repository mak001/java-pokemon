package com.mak001.pokemon.player;

public class StackFullException extends Exception {
	private static final long serialVersionUID = 1L;

	private final int left;

	public StackFullException(int left) {
		super();
		this.left = left;
	}

	public StackFullException(int left, String message) {
		super(message);
		this.left = left;
	}

	public StackFullException(int left, String message, Throwable throwable) {
		super(message, throwable);
		this.left = left;
	}

	public int getLeftOver() {
		return left;
	}

}
