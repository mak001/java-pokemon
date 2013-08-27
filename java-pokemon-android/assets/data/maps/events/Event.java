package data.maps.events;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.world.World;
import com.mak001.pokemon.world.entity.Entity;
import com.mak001.pokemon.world.objects.ScriptedEvent;

public class Event extends ScriptedEvent {

	private int time = 0;
	private Entity follow;

	public Event(Rectangle bounds, World world) {
		super(bounds, world);
		follow = getWorld().getEntity("player", "mak001");
	}

	@Override
	public void run() {
		if (!getWorld().screen.renderer.cameraCenter.atPosition(follow)
				&& time == 0) {
			getWorld().screen.renderer.cameraCenter.moveTo(follow);
		} else {
			if (time < 100) {
				getWorld().screen.renderer.cameraCenter.update(follow);
				time++;
			} else {
				if (!getWorld().screen.renderer.cameraCenter
						.atPosition(getWorld().getPlayer())) {
					getWorld().screen.renderer.cameraCenter.moveTo(getWorld()
							.getPlayer());
				} else {
					time = 0;
					this.setRunning(false);
				}
			}
		}
	}

	@Override
	public boolean shouldTrigger() {
		// TODO Auto-generated method stub
		return true;
	}

}
