package com.mak001.pokemon.world.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.mak001.pokemon.PokeGame;
import com.mak001.pokemon.screens.huds.NPCInteractionHud;
import com.mak001.pokemon.world.Locatable;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.data.Direction;
import com.mak001.pokemon.world.entity.data.Interaction;

public class NPC extends Entity {

	private ArrayList<Vector2> path;
	private Interaction interaction;

	private NPCInteractionHud hud = null;

	private String name;
	private Direction original_direction;
	private int nextPosition = 1;

	private boolean shouldStop = false;

	public NPC(Direction direction, final Vector2 position, World world,
			Interaction interaction, String generic_name, String name) {
		this(direction, new ArrayList<Vector2>() {
			private static final long serialVersionUID = 1L;
			{
				add(position);
			}
		}, world, interaction, generic_name, name);
	}

	public NPC(Direction direction, ArrayList<Vector2> path, World world,
			String generic_name, String name) {
		this(direction, path, world, null, generic_name, name);
	}

	public NPC(Direction direction, ArrayList<Vector2> path, World world,
			Interaction interaction, String generic_name, String name) {
		super(direction, new Vector2(path.get(0)), generic_name, world);
		this.name = name;
		this.path = path;
		this.interaction = interaction;
		this.original_direction = direction;
	}

	public Interaction getFirstInteraction() {
		return interaction;
	}

	public void update() {
		if (talking) {
			setDirection(world.getPlayer().getPosition());
		} else {
			if (shouldStop == false) {
				for (int i = 0; i < getSpeed().getSpeed(); i++) {
					if (path.size() > 1) {

						if (!isMoving()) {
							if (path.get(nextPosition).x == position.x
									&& path.get(nextPosition).y == position.y) {
								updatePos();
							}
							setDirection(path.get(nextPosition));

							if (isInLineOfSight(world.getPlayer())) { // TODO
								System.out.println(this.generic_name + " "
										+ this.name
										+ " has spotted the player!");
							}

							Vector2 v = getNextPos();
							if (!isBlocked(v.x, v.y, direction)) {
								move();
							}
						} else {
							move();
						}
					} else {
						direction = original_direction;
					}
				}
			}
		}
		updateBounds();
	}

	private void setDirection(Vector2 vec) {
		if (!getDirection().equals(getDirection(vec)))
			direction = getDirection(vec);
	}

	private void updatePos() {
		if (nextPosition == path.size() - 1) {
			nextPosition = 0;
		} else {
			nextPosition++;
		}
	}

	private Vector2 getNextPos() {
		switch (direction) {
		case DOWN:
			return new Vector2(position.x, position.y - 1);
		case LEFT:
			return new Vector2(position.x - 1, position.y);
		case RIGHT:
			return new Vector2(position.x + 1, position.y);
		case UP:
			return new Vector2(position.x, position.y + 1);
		default:
			return null;

		}
	}

	private void move() {
		switch (getDirection()) {
		case DOWN:
			position.y -= (1f / PokeGame.TILE_DIMENSION);
			break;
		case LEFT:
			position.x -= (1f / PokeGame.TILE_DIMENSION);
			break;
		case RIGHT:
			position.x += (1f / PokeGame.TILE_DIMENSION);
			break;
		case UP:
			position.y += (1f / PokeGame.TILE_DIMENSION);
			break;
		}
	}

	public String getName() {
		return name;
	}

	public String getGenericName() {
		return generic_name;
	}

	public String getFullName() {
		return generic_name + " " + name;
	}

	@Override
	public void setTalking(boolean talking) {
		if (talking) {
			if (hud == null) {
				hud = new NPCInteractionHud(world.screen, this);
				world.screen.addHud(hud);
				this.talking = talking;
			} else {
				hud.yesOption();
			}
		} else {
			if (hud == null) {
				resetHud();
			} else {
				if (hud.getInteraction() != null) {
					if (talking) {
						hud.yesOption();
					} else {
						hud.noOption();
					}
				} else {
					resetHud();
				}
			}
		}
	}

	public boolean isInLineOfSight(Locatable l) {
		if (!l.equals(this))
			return inLineWith(l) && getDirection(l).equals(getDirection());
		return false;
	}

	public void stop() {
		shouldStop = true;
	}

	public void resume() {
		shouldStop = false;
	}

	public void resetHud() {
		this.talking = false;
		if (hud != null)
			hud = null;
	}

}
