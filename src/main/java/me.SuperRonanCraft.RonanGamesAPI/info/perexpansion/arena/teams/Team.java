package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.teams;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
