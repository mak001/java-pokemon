package com.pokemon;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

public class Grid {

	private final int pieceSize;
	private final Rectangle bounds;
	private final LinkedList<Rectangle> rectangles;

	public Grid(Rectangle rectangle, int i) {
		rectangles = new LinkedList<Rectangle>();
		bounds = rectangle;
		pieceSize = i;
		setRectangles();
	}

	public Grid(int i) {
		this(new Rectangle(0, 0, GameBase.width(), GameBase.height()), i);
	}

	private void setRectangles() {
		int xs = (int) (bounds.getWidth() / pieceSize);
		for (int i = 0; i < xs; i++) {
			int ys = (int) (bounds.getHeight() / pieceSize);
			for (int j = 0; j < ys; j++) {
				rectangles.add(new Rectangle(i * pieceSize, j * pieceSize,
						pieceSize, pieceSize));
			}
		}
	}

	public Rectangle getPiece(int i) {
		return rectangles.get(i);
	}

	public void add(Rectangle arectangle[]) {
		Rectangle arectangle1[];
		int j = (arectangle1 = arectangle).length;
		for (int i = 0; i < j; i++) {
			Rectangle rectangle = arectangle1[i];
			rectangles.add(rectangle);
		}

	}

	public void remove(int i) {
		rectangles.remove(i);
	}

	public LinkedList<Rectangle> getPieces() {
		return rectangles;
	}

	public Rectangle[] getPiecesArray() {
		return rectangles.toArray(new Rectangle[rectangles.size()]);
	}

	public boolean contains(int i, Point point) {
		return getPiece(i).contains(point);
	}

	public boolean containsAll(int i, Point apoint[]) {
		Rectangle rectangle = getPiece(i);
		Point apoint1[];
		int k = (apoint1 = apoint).length;
		for (int j = 0; j < k; j++) {
			Point point = apoint1[j];
			if (!rectangle.contains(point)) {
				return false;
			}
		}

		return true;
	}

	public Rectangle getContaining(Point point) {
		for (Iterator<Rectangle> iterator = rectangles.iterator(); iterator
				.hasNext();) {
			Rectangle rectangle = iterator.next();
			if (rectangle.contains(point)) {
				return rectangle;
			}
		}

		return null;
	}

	public Rectangle getContaining(Point apoint[]) {
		for (int i = 0; i < rectangles.size(); i++) {
			if (containsAll(i, apoint)) {
				return getPiece(i);
			}
		}

		return null;
	}

	public int getPieceSize() {
		return pieceSize;
	}

	public void draw(Graphics g) {
		Rectangle rectangle;
		for (Iterator<Rectangle> iterator = rectangles.iterator(); iterator
				.hasNext(); g.drawRect(rectangle.x, rectangle.y,
				rectangle.width, rectangle.height)) {
			rectangle = iterator.next();
		}

	}

	public void drawPoints(Graphics g) {
		int i;
		int j;
		for (Iterator<Rectangle> iterator = rectangles.iterator(); iterator
				.hasNext(); g.drawLine(i, j, i, j)) {
			Rectangle rectangle = iterator.next();
			i = rectangle.x;
			j = rectangle.y;
		}

	}

	public Rectangle getBounds() {
		return bounds;
	}

}
