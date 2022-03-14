package me.SuperRonanCraft.RonanGamesAPI.perexpansion.arena.teams;

import me.SuperRonanCraft.RonanGamesAPI.perexpansion.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Team {
    private Arena pl;

    private int teams = 1;

    public Team(Arena pl) {
        this.pl = pl;
    }

    public void load() {
        FileConfiguration config = pl.getArenaConfig();
        if (config.isInt("Teams.Amount"))
            teams = config.getInt("Teams.Amount");
    }

    public void addTeam(String name, ChatColor color) {
        String pre = "Teams.Names";
        List<String> teamNames = pl.getArenaConfig().getStringList(pre);
        teamNames.add(color.toString());
        set("Teams.Names", teamNames);
    }

    private void set(String path, Object value) {
        FileConfiguration arena = pl.getArenaConfig();
        pl.getArenaConfig().set(path, value);
        //pl.saveArenaFile(arena);
    }

    public int getTeams() {
        return teams;
    }
}
