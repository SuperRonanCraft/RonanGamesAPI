package me.SuperRonanCraft.RonanGamesAPI.expansion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import me.SuperRonanCraft.RonanGamesAPI.expansion.interfaces.ExpConfigurable;
import me.SuperRonanCraft.RonanGamesAPI.expansion.interfaces.ExpRenameable;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.RonanGamesGamemode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.LoadArenas;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.lobby.LoadLobby;
import me.SuperRonanCraft.RonanGamesAPI.references.Permissions;

public abstract class Expansion {
    private File configFile = null;
    private FileConfiguration config = null;
    private final LoadArenas arenas = new LoadArenas(this);
    private final LoadLobby lobbys = new LoadLobby(this);

    public Expansion() {
        if (register())
            load();
        if (this instanceof ExpConfigurable)
            loadConfigFile();
        arenas.load();
    }

    //Expansion requirements

    /**
     * Anything the game needs to load upon server startup
     */
    @SuppressWarnings("all")
    public abstract void load();

    @SuppressWarnings("all")
    public abstract void reload();

    /**
     * What to do once an arena is called, from here the API will only protect
     * the area and check if a player leaves, not handle events
     */
    public abstract void eventArenaStart(Arena arena);

    /**
     * What to do once an arena ends, from here the API will only teleport the
     * players back to their original location
     */
    public abstract void eventArenaEnd(Arena arena);

    /**
     * Get the name that this game expansion uses
     *
     * @return game identifier that is associated with this class
     */
    @SuppressWarnings("all")
    public abstract String getName();

    /**
     * Create any short hand name for the expansion
     *
     * @return game identifier that is associated with this class
     */
    public abstract List<String> getNameAliases();

    /**
     * Get the author of this GameExpansion
     *
     * @return name of the author for this expansion
     */
    public abstract String getAuthor();

    /**
     * Get the version of this GameExpansion
     *
     * @return current version of this expansion
     */
    public abstract String getVersion();

    public abstract List<RonanGamesGamemode> getGamemodeTypes();

    //Expansion getters/setup

    public String getNameCustom() {
        String str = null;
        if (this instanceof ExpRenameable)
            str = getConfigFile().getString("Name");
        if (str == null)
            str = getName();
        return str.replaceAll(" ", "").trim();
    }

    /**
     * Attempt to register this GameExpansion with RonanGamesCore
     *
     * @return true if this class and identifier have been successfully
     * registered with RonanGamesCore
     */
    private boolean register() {
        return RonanGamesCore.registerGame(this);
    }

    /**
     * Quick getter for the RonanGamesCore instance
     */
    public RonanGamesCorePlugin getAPI() {
        return RonanGamesCorePlugin.getInstance();
    }

    public Permissions getAPIPermissions() {
        return getAPI().getPerms();
    }

    public LoadArenas getArena() {
        return arenas;
    }

    public LoadLobby getLobbys() {
        return lobbys;
    }

    public Integer getPoints(Player p) {
        return getAPI().getPoints().getPoints(p);
    }

    private void loadConfigFile() {
        if (configFile == null)
            configFile = new File(getAPI().getDataFolder(), getName() + File.separator + "src2/main/resources/config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    @SuppressWarnings("all")
    public FileConfiguration getConfigFile() {
        if (config == null)
            loadConfigFile();
        return config;
    }

    @SuppressWarnings("all")
    public void saveConfigFile() {
        if (config == null)
            return;
        try {
            getConfigFile().save(configFile);
        } catch (IOException ex) {
            RonanGamesCorePlugin.getInstance().getLogger().log(Level.SEVERE,
                    "Could not save to " + configFile.getName(), ex);
        }
    }

    public void saveConfigFile(FileConfiguration config) {
        if ((config == null))
            return;
        try {
            config.save(configFile);
        } catch (IOException ex) {
            RonanGamesCorePlugin.getInstance().getLogger().log(Level.SEVERE,
                    "Could not save to " + configFile.getName(), ex);
        }
    }
}
