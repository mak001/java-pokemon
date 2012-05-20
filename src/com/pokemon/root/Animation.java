package com.pokemon.root;

import java.awt.Image;

public class Animation {

	private int current_frame;
	private int frame_limit;
	private Image image;

	/**
	 * 
	 * 
	 * 
	 * @param image
	 *            - The image to be used to get the animation images from
	 * @param frame_limit
	 *            - The total amount of frames
	 */
	public Animation(Image image, int frame_limit) {
		this.frame_limit = frame_limit;

	}

	public void nextFrame() {
		if (current_frame + 1 == frame_limit) {
			current_frame = 0;
		} else {
			current_frame++;
		}
	}

	public void previousFrame() {
		if (current_frame == 0) {
			current_frame = frame_limit - 1;
		} else {
			current_frame--;
		}
	}

	public void setFrame(int frame) {
		if (frame < frame_limit) {
			current_frame = frame;
		}
	}

	/**
	 * gets the current frame the animation is on
	 * 
	 * @return The current frame
	 */
	public int getCurrentFrame() {
		return current_frame;
	}

	/**
	 * gets the frame limit of the animation
	 * 
	 * @return The frame limit
	 */
	public int getFramelimit() {
		return frame_limit;
	}

	public Image getAnimationFrame() {
		return image; // TODO
	}

	public Image getFullImage() {
		return image;
	}

}
