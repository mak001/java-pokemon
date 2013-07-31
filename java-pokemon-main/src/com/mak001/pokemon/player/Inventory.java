package com.mak001.pokemon.player;

import java.util.ArrayList;

public abstract class Inventory<O extends Object> {

	protected int slots;
	protected ArrayList<ItemStack> objects;

	public Inventory(int max_slots) {
		slots = max_slots;
		objects = new ArrayList<ItemStack>();
	}

	public void add(O object) {
		add(object, 1);
	}

	public void remove(O object) {
		remove(object, 1);
	}

	public void add(O object, int amount) {
		int i = objects.lastIndexOf(object);
		if (i != -1) {
			try {
				objects.get(i).addItem(amount);
			} catch (StackFullException e) {
				objects.add(new ItemStack(object, e.getLeftOver()));
			}
		} else {
			objects.add(new ItemStack(object, amount));
		}
	}

	public void remove(O object, int amount) {

	}

	public void remove(int slot) {
		remove(slot, 1);
	}

	public void remove(int slot, int amount) {

	}

}
