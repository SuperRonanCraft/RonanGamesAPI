package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.SuperRonanCraft.RonanGamesAPI.references.files.FileBasics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.BoardType;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class Board implements Listener {
    // Key is the board id
    private HashMap<String, List<Location>> boards = new HashMap<>();
    private HashMap<String, Expansion> boardGame = new HashMap<>();
    private BoardType boardType;
    private List<String> lines;
    private String waiting, active, disabled;

    public Board(RonanGamesCorePlugin pl) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, pl);
        load(pl);
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if (e.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
            Location loc = e.getClickedBlock().getLocation();
            for (String id : boards.keySet())
                for (Location board : boards.get(id))
                    if (board.equals(loc)) {
                        if (boardType.equals(BoardType.ARENA))
                            return;
                        //
                    }
        }
    }

    private void updateSign() {
    }

    private void load(RonanGamesCorePlugin pl) {
        lines = pl.getConfig().getStringList("Board.Format");
        waiting = pl.getConfig().getString("Board.Status.Waiting");
        active = pl.getConfig().getString("Board.Status.Active");
        disabled = pl.getConfig().getString("Board.Status.Disabled");
        FileConfiguration file = pl.getYaml().getType(FileBasics.FILETYPE.BOARD).getFile();
        for (String sec : file.getKeys(false)) {
            List<Location> locs = new ArrayList<>();
            for (String pos : file.getConfigurationSection(sec).getKeys(false)) {
                String[] array = file.getString(sec + "." + pos).split(",");
                Location loc = new Location(Bukkit.getWorld(array[0]), toInt(array[1]), toInt(array[2]), toInt
						(array[3]));
                if (loc != null)
                    locs.add(loc);
            }
            Expansion ex = RonanGamesCore.getRegisteredGames().get(file.getString(sec + ".Game"));
            if (!locs.isEmpty() && ex != null) {
                boards.put(sec, locs);
                boardGame.put(sec, ex);
            }
        }
    }

    private Integer toInt(String str) {
        return Integer.valueOf(str);
    }
}
