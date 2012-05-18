package com.game.engine;

import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameCanvas extends JComponent {
	private final Game game;
	
	public GameCanvas(Game game){
		this.game = game;
		addKeyListener(this.game);
		addMouseListener(this.game);
		addMouseMotionListener(this.game);
		requestFocus();
	}
	
	@Override
	public void paintComponent(Graphics g){
		game.render(g);
	}

}
