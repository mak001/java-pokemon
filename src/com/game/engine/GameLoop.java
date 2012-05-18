package com.game.engine;

public class GameLoop extends Thread{
	
	private final Game game;
	private final GameCanvas canvas;
	
	public GameLoop (Game game, GameCanvas canvas){
		this.game = game;
		this.canvas = canvas;
	}
	
	@Override
	public void run(){
		game.initialize();
		
		while(!game.isOver()){
			game.update();
			canvas.repaint();
			
			try {
				Thread.sleep(game.getRenderDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
