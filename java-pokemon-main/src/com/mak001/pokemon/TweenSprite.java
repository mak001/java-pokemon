package com.mak001.pokemon;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenSprite implements TweenAccessor<Sprite> {

	public static final int ALPHA = 1;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return ALPHA;
		default:
			return 0;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setColor(1, 1, 1, newValues[0]);
			break;
		default:
			return;
		}
	}

}
