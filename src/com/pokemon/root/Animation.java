package com.pokemon.root;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pokemon.room.Coordinate;

public class Animation {

	private int current_frame;
	private boolean reverse;
	private Image image;
	private int xOffset = 9;
	private int walking = 0;

	public enum AnimationType {
		UP, DOWN, LEFT, RIGHT, UP_WALKING, DOWN_WALKING, LEFT_WALKING,
		RIGHT_WALKING, JUMPING
	}

	/**
	 * 
	 * 
	 * 
	 * @param image
	 *            - The image to be used to get the animation images from
	 * @param frame_limit
	 *            - The total amount of frames
	 */
	public Animation(Image image) {
		this.image = image;
	}

	public void setFrame(AnimationType animation) {
		switch (animation) {
		case DOWN:
			current_frame = 0;
			reverse = false;
			break;
		case UP:
			current_frame = 1;
			reverse = false;
			break;
		case LEFT:
			current_frame = 2;
			reverse = false;
			break;
		case RIGHT:
			current_frame = 2;
			reverse = true;
			break;
		case RIGHT_WALKING:
			if (walking == 0) {
				current_frame = 5;
				walking++;
			} else {
				current_frame = 6;
				walking = 0;
			}
			reverse = true;
			break;
		case LEFT_WALKING:
			if (walking == 0) {
				current_frame = 5;
				walking++;
			} else {
				current_frame = 6;
				walking = 0;
			}
			reverse = false;
			break;
		case UP_WALKING:
			current_frame = 4;
			reverse = false;
			break;
		case DOWN_WALKING:
			current_frame = 3;
			reverse = false;
			break;
		case JUMPING:
			current_frame = 7;
			reverse = false;
			break;
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

	public Image getAnimationFrame() {
		return ((BufferedImage) image).getSubimage(current_frame * xOffset, 0,
				16, 25);
	}

	public boolean isReversed() {
		return reverse;
	}

	public Image getFullImage() {
		return image;
	}

	public void draw(Graphics2D g, AnimationType animation,
			Coordinate coordinate) {
		setFrame(animation);
		if (isReversed()) {
			// TODO
			g.drawImage(getAnimationFrame(), 0, 0, 16, 25, 16, 0, 0, 25, null);
		} else {
			g.drawImage(getAnimationFrame(), coordinate.getOffsetX(),
					coordinate.getOffsetY(), null);
		}
	}

}
