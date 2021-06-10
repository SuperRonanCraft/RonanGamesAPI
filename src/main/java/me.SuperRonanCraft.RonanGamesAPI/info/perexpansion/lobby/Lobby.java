package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.lobby;

import org.bukkit.Location;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class Lobby {
	Expansion pl;
	String name;
	boolean available = false;
	Location pos1, pos2;

	public Location getPos1() {
		return pos1;
	}

	public Location getPos2() {
		return pos2;
	}

	public Lobby(Expansion pl, String name, Location pos1, Location pos2) {
		this.pl = pl;
		this.name = name;
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public boolean isValid() {
		if (name != null && pos1 != null && pos2 != null)
			return true;
		return false;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getName() {
		return name;
	}
}
