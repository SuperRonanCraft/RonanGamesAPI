package me.SuperRonanCraft.RonanGamesAPI.info.players;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerInfo {
	HashMap<Player, Location> originalPos = new HashMap<Player, Location>();

	public void savePosition(Player p) {
		originalPos.put(p, p.getLocation());
	}

	public void teleportBack(Player p) {
		if (originalPos.containsKey(p))
			p.teleport(originalPos.get(p));
	}
}
