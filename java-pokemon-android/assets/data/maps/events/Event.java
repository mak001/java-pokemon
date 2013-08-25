package data.maps.events;

import com.badlogic.gdx.math.Rectangle;
import com.mak001.pokemon.world.objects.ScriptedEvent;

public class Event extends ScriptedEvent {

	public Event(Rectangle bounds) {
		super(bounds);
	}

	@Override
	public boolean shouldTrigger() {
		// TODO Auto-generated method stub
		return false;
	}

}
