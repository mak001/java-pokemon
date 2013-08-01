package com.mak001.pokemon.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mak001.pokemon.GlobalVars;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.TweenSprite;

public class SplashScreen extends AbstractScreen {
	TweenManager manager;

	private Texture splashTexture;
	private Sprite splashSprite;
	private Sound sound;

	public SplashScreen(PokeGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		sound = Gdx.audio.newSound(Gdx.files.internal(PokeGame.SOUND_EFFECTS
				+ "pokemon-recovery.mp3"));

		sound.play(GlobalVars.effects_sound_level);

		splashTexture = new Texture(Gdx.files.internal("data/libgdx.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
		splashSprite
				.setX((Gdx.graphics.getWidth() - splashSprite.getWidth()) / 2);
		splashSprite.setY(Gdx.graphics.getHeight() - splashSprite.getHeight());

		batch = new SpriteBatch();

		TweenCallback callBack = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				if (type == TweenCallback.COMPLETE) {
					game.setScreen(new MainMenu(game));
				}
			}
		};
		Tween.registerAccessor(Sprite.class, new TweenSprite());
		manager = new TweenManager();
		Tween.to(splashSprite, TweenSprite.ALPHA, 2f).target(1)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1, 3f)
				.setCallback(callBack).start(manager);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		manager.update(delta);

		batch.begin();

		splashSprite.draw(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		splashTexture.dispose();
		sound.dispose();
	}
}
