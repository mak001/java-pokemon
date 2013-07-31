package com.mak001.pokemon.player;

public class InventoryFullException extends Exception {
	private static final long serialVersionUID = 1L;

	public InventoryFullException() {
		super();
	}

	public InventoryFullException(String message) {
		super(message);
	}

	public InventoryFullException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
