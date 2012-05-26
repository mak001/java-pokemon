package com.pokemon.root;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.pokemon.room.Coordinate;

public class Animation {

	private Image image;
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

	public Image getAnimationFrame(int frame) {
		return ((BufferedImage) image).getSubimage(frame * 16, 0, 16, 25);
	}

	public Image getFullImage() {
		return image;
	}

	public void draw(Graphics2D g, AnimationType animation,
			Coordinate coordinate) {
		switch (animation) {
		case DOWN:
			drawNormal(g, 0, coordinate.getOffsetX(),
					coordinate.getCharOffset());
			break;
		case UP:
			drawNormal(g, 1, coordinate.getOffsetX(),
					coordinate.getCharOffset());
			break;
		case LEFT:
			drawNormal(g, 2, coordinate.getOffsetX(),
					coordinate.getCharOffset());
			break;
		case RIGHT:
			drawNormal(g, 2, coordinate.getOffsetX(),
					coordinate.getCharOffset());
			break;
		case RIGHT_WALKING:
			if (walking == 0) {
				drawReverse(g, 5, coordinate.getOffsetX() + 8,
						coordinate.getCharOffset());
			} else {
				drawReverse(g, 6, coordinate.getOffsetX() + 8,
						coordinate.getCharOffset());
			}
			break;
		case LEFT_WALKING:
			if (walking == 0) {
				drawNormal(g, 5, coordinate.getOffsetX() - 8,
						coordinate.getCharOffset());
			} else {
				drawNormal(g, 6, coordinate.getOffsetX() - 8,
						coordinate.getCharOffset());
			}
			break;
		case UP_WALKING:
			if (walking == 0) {
				drawNormal(g, 4, coordinate.getOffsetX(),
						coordinate.getCharOffset() - 8);
			} else {
				drawReverse(g, 4, coordinate.getOffsetX(),
						coordinate.getCharOffset() - 8);
			}
			break;
		case DOWN_WALKING:
			if (walking == 0) {
				drawNormal(g, 3, coordinate.getOffsetX(),
						coordinate.getCharOffset() + 8);
			} else {
				drawReverse(g, 3, coordinate.getOffsetX(),
						coordinate.getCharOffset() + 8);
			}
			break;
		case JUMPING:
			if (walking == 0) {
				drawNormal(g, 7, coordinate.getOffsetX(),
						coordinate.getCharOffset());
			} else {
				drawNormal(g, 0, coordinate.getOffsetX(),
						coordinate.getCharOffset());
			}
			break;
		default:
			drawNormal(g, 0, coordinate.getOffsetX(),
					coordinate.getCharOffset());
			break;
		}
	}

	private void drawNormal(Graphics2D g, int frame, int x, int y) {
		g.drawImage(getAnimationFrame(frame), x, y, null);
	}

	/*
	 * dx1 - the x coordinate of the first corner of the destination rectangle.
	 * dy1 - the y coordinate of the first corner of the destination rectangle.
	 * dx2 - the x coordinate of the second corner of the destination rectangle.
	 * dy2 - the y coordinate of the second corner of the destination rectangle.
	 * sx1 - the x coordinate of the first corner of the source rectangle. sy1 -
	 * the y coordinate of the first corner of the source rectangle. sx2 - the x
	 * coordinate of the second corner of the source rectangle. sy2 - the y
	 * coordinate of the second corner of the source rectangle. observer -
	 * object to be notified as more of the image is scaled and converted.
	 */

	private void drawReverse(Graphics2D g, int frame, int x, int y) {
		// TODO - figure out how to reverse the image
		g.drawImage(getAnimationFrame(frame), null, null);
	}

	public void addTick() {
		if (walking == 0) {
			walking++;
		} else {
			walking = 0;
		}
	}

}
