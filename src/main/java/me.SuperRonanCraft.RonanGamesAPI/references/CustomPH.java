package me.SuperRonanCraft.RonanGamesAPI.references;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class CustomPH extends PlaceholderExpansion {
	RonanGamesCorePlugin pl;

	public CustomPH(RonanGamesCorePlugin pl) {
		//super(pl, "ronangamescore");
		this.pl = pl;
	}

	@Override
	public String onPlaceholderRequest(Player p, String id) {
		if (id.equals("points"))
			return String.valueOf(pl.getPoints().getPoints(p));
		return null;
	}

	@Override
	public String getIdentifier() {
		return "ronangamescore";
	}

	@Override
	public String getAuthor() {
		return pl.getDescription().getAuthors().get(0);
	}

	@Override
	public String getVersion() {
		return null;
	}
}
