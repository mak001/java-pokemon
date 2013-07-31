package com.mak001.pokemon.player;

public class ItemStack {

	private int stackNumber;
	private int maxStack;
	private Object o;

	public ItemStack(Object object, int currentStack) {
		this(object, currentStack, Integer.MAX_VALUE);
	}

	public ItemStack(Object object, int currentStack, int maxStack) {
		stackNumber = currentStack;
		this.maxStack = maxStack;
		o = object;
	}

	public Object getType() {
		return o;
	}

	public int getStack() {
		return stackNumber;
	}

	public int getMax() {
		return maxStack;
	}

	public int removeItem(int i) {
		stackNumber = stackNumber - i;
		if (stackNumber < 0)
			stackNumber = 0;
		return stackNumber;
	}

	public int addItem(int i) throws StackFullException {
		stackNumber = stackNumber + i;
		if (stackNumber > maxStack) {
			int j = stackNumber - maxStack;
			stackNumber = maxStack;
			throw (new StackFullException(j));
		}
		return stackNumber;
	}

}
