package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class LoadLobby {

    Expansion pl;
    List<String> lobbyIDs = new ArrayList<String>();
    HashMap<String, Lobby> lobbys = new HashMap<String, Lobby>();

    public LoadLobby(Expansion pl) {
        this.pl = pl;
        //load();
    }

    /*private void load() {
        FileConfiguration file = getArenaConfig();
        ConfigurationSection config = file.getConfigurationSection("Lobby");
        if (config != null)
            for (String sec : config.getKeys(false)) {
                String arenaName = config.getString(sec + ".getName");
                // pos1
                World worldpos = Bukkit.getWorld(config.getString(sec + ".Protection.World"));
                Location pos1 = new Location(worldpos, config.getDouble(sec + ".Protection.Pos1.X"), config.getDouble
						(sec + ".Protection.Pos1.Y"), config.getDouble(sec + ".Protection.Pos1.Z"));
                // pos2
                Location pos2 = new Location(worldpos, config.getDouble(sec + ".Protection.Pos2.X"), config.getDouble
						(sec + ".Protection.Pos2.Y"), config.getDouble(sec + ".Protection.Pos2.Z"));
                Lobby lobby = new Lobby(pl, arenaName, pos1, pos2);
                if (lobby.isValid()) {
                    lobbys.put(sec, lobby);
                    //pl.getAPI().registerProtection(sec, pos1, pos2, pl.getName());
                } else
                    Bukkit.getConsoleSender().sendMessage("WARNING! Error with the lobby " + sec + ", please delete " +
							"or fix accordingly!");
            }
    }*/

    public String getNextAvailableLobby() {
        return null;
    }

    public List<String> getAllLobbies() {
        return lobbyIDs;
    }

    public List<String> getAvailableLobbies() {
        List<String> lobbies = new ArrayList<String>();
        for (String lobby : lobbyIDs)
            if (lobbys.get(lobby).isAvailable())
                lobbies.add(lobby);
        return lobbies;
    }
}
