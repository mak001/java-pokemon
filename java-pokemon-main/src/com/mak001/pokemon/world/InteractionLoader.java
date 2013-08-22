package com.mak001.pokemon.world;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mak001.pokemon.world.entity.Interaction;

public class InteractionLoader {

	private static final String NPC_REAL_NAME = "real_name";
	private static final String NPC_GENERIC_NAME = "generic_name";
	private static final String NPC = "NPC";
	private static final String INTERACTION = "INTERACTION";
	private static final String INTERACTION_TEXT = "text";

	private XmlReader xml;
	private XmlReader.Element xml_element;

	public InteractionLoader(String worldName) {
		try {
			xml = new XmlReader();
			xml_element = xml.parse(Gdx.files.internal("data/maps/npcs/"
					+ worldName + ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Interaction loadInteractions(String gName, String name) {
		Iterator<Element> iterator_level = xml_element.getChildrenByName(NPC)
				.iterator();
		while (iterator_level.hasNext()) {
			Element npc = (Element) iterator_level.next();
			if (npc.getAttribute(NPC_REAL_NAME).equalsIgnoreCase(name)
					&& npc.getAttribute(NPC_GENERIC_NAME).equalsIgnoreCase(
							gName)) {
				return getInteraction(npc);
			}
		}
		return null;
	}

	private Interaction getInteraction(Element e) {
		Array<Element> interactions = e.getChildrenByName(INTERACTION);
		Interaction[] value = new Interaction[2];
		String text = null;
		int iCount = 0;
		for (int i = 0; i < interactions.size; i++) {
			Element interaction = interactions.get(i);
			text = interaction.getAttribute(INTERACTION_TEXT);
			if (iCount < 2) {
				value[iCount] = getInteraction(interaction);
				iCount++;
			}
		}
		return text == null ? null : new Interaction(text, value);
	}
}
