package com.mak001.pokemon.control;

public interface AccelerometerListener {

	/**
	 * @param x
	 *            - The current accelerometer x value
	 */
	public void accelerometerXChanged(float x);

	/**
	 * @param y
	 *            - The current accelerometer y value
	 */
	public void accelerometerYChanged(float y);

	/**
	 * @param z
	 *            - The current accelerometer z value
	 */
	public void accelerometerZChanged(float z);

}
