package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class LoadArenas {

    private Expansion pl;
    private HashMap<String, Arena> arenas = new HashMap<>();

    public LoadArenas(Expansion pl) {
        this.pl = pl;
    }

    public void load() {
        String[] fileNames = getAllArenaFiles();
        if (fileNames != null)
            for (String f : fileNames) {
                Arena arena = new Arena(pl, f);
                arena.load();
                arenas.put(arena.getName(), arena);
            }
    }

    private String[] getAllArenaFiles() {
        return new File(pl.getAPI().getDataFolder().getPath() + File.separator + "games" + File.separator
                + pl.getName() + File.separator + "arenas").list();
    }

    public Set<String> getArenaIDs() {
        return arenas.keySet();
    }

    public Arena getArena(String arena) {
        return arenas.get(arena);
    }

    public boolean createArena(String arenaName) {
        for (String key : arenas.keySet())
            if (key.equalsIgnoreCase(arenaName))
                return false;
        arenas.put(arenaName, new Arena(pl, arenaName + ".yml"));
        return true;
    }
}

