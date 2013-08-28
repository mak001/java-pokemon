package data.maps.events;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.screens.GameScreen;
import com.mak001.pokemon.screens.huds.NPCInteractionHud;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.NPC;
import com.mak001.pokemon.world.entity.data.Direction;
import com.mak001.pokemon.world.entity.data.Interaction;
import com.mak001.pokemon.world.objects.ScriptedEvent;

public class Event extends ScriptedEvent {

	private NPC follow;
	private Interaction interaction;

	private int stage = 0;
	private final int MOVE_TO_NPC = 0, STOP_NPC = 1, NPC_TALK = 2,
			MOVE_TO_PLAYER = 3;

	public Event(Rectangle bounds, World world) {
		super(bounds, world);
		init();
	}

	private void init() {
		follow = (NPC) getWorld().getEntity("player", "mak001");
		interaction = new Interaction("Hello");

	}

	@Override
	public void run() {
		if (stage == MOVE_TO_NPC) {
			if (!getWorld().screen.renderer.cameraCenter.atPosition(follow)) {
				if (!follow.isMoving()) {
					follow.setDirection(Direction.DOWN);
					follow.freeze(true);
				}
				getWorld().screen.renderer.cameraCenter.moveTo(follow);
			} else {
				stage++;
			}
		} else if (stage == STOP_NPC) {
			getWorld().screen.addHud(new NPCInteractionHud(getWorld().screen,
					interaction), GameScreen.NPC_HUD);
			stage++;
		} else if (stage == NPC_TALK) {
			if (getWorld().screen.getHuds().get(GameScreen.NPC_HUD) == null) {
				follow.freeze(false);
				stage++;
			} else {
				if (!follow.getDirection().equals(Direction.DOWN))
					follow.setDirection(Direction.DOWN);
			}
		} else if (stage == MOVE_TO_PLAYER) {
			if (!getWorld().screen.renderer.cameraCenter.atPosition(getWorld()
					.getPlayer())) {
				getWorld().screen.renderer.cameraCenter.moveTo(getWorld()
						.getPlayer());
			} else {
				stage++;
			}
		} else {
			stage = 0;
			this.setRunning(false);
		}
	}

	@Override
	public boolean shouldTrigger() {
		// TODO Auto-generated method stub
		return true;
	}

}
