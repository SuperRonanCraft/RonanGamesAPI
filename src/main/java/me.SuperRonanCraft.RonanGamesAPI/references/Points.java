package me.SuperRonanCraft.RonanGamesAPI.references;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;

public class Points {
	RonanGamesCorePlugin pl;
	HashMap<Player, Integer> points = new HashMap<Player, Integer>();

	public Points(RonanGamesCorePlugin pl) {
		this.pl = pl;
	}

	public void load() {

	}

	public int getPoints(Player p) {
		if (points.get(p) == null)
			return 0;
		return points.get(p);
	}

	public void addPoints(int i, Player p) {
		if (points.get(p) == null)
			points.put(p, i);
		else
			points.put(p, points.get(p) + i);
	}

	public void removePoints(int i, Player p) {
		if (points.get(p) != null)
			if (points.get(p) >= i)
				points.put(p, points.get(p) - i);
			else
				points.put(p, 0);
	}

}
