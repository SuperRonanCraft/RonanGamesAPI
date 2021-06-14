package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.teams.Team;
import org.bukkit.*;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Arena {
    private final Expansion pl;
    private Boolean enabled = false;
    private World world;
    // HashMap<String, List<Location>> teamSpawns = new HashMap<String,
    // List<Location>>();
    private FileConfiguration arenaConfig;
    private File arenaFile;
    private String path;
    //Setable
    private RonanGamesGamemode mode;
    private Team team = new Team(this);
    private Location pos1, pos2;
    private String name;


    public Arena(Expansion pl, String path) {
        this.pl = pl;
        this.path = path;
        name = path.replaceAll("\\.yml", "");
    }

    public boolean isValid() {
        return (name != null && pos1 != null && pos2 != null);
    }

    public void load() {
        FileConfiguration config = getArenaConfig();
        if (config != null) {
            enabled = config.getBoolean("Enabled");
            team.load();
            if (config.isConfigurationSection("Protection")) {
                world = Bukkit.getWorld(config.getString("Protection.World"));
                pos1 = new Location(world, config.getDouble("Protection.Pos1.X"), config.getDouble("Protection" + ""
                        + "" + ".Pos1.Y"), config.getDouble("Protection.Pos1.Z"));
                pos2 = new Location(world, config.getDouble("Protection.Pos2.X"), config.getDouble("Protection" + ""
                        + "" + ".Pos2.Y"), config.getDouble("Protection.Pos2.Z"));
            }
            if (config.isString("Gamemode"))
                mode = RonanGamesGamemode.valueOf(config.getString("Gamemode"));
        }
    }

    public void setProtection(Location pos1, Location pos2) {
        this.world = pos1.getWorld();
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public RonanGamesGamemode setGamemode(String gm) {
        try {
            this.mode = RonanGamesGamemode.valueOf(gm);
            return this.mode;
        } catch (IllegalArgumentException e) {
            //Errored
        }
        return null;
    }

    public void addTeam(String name, ChatColor color) {
        team.addTeam(name, color);
    }

    public boolean enable() {
        if (isValid())
            enabled = true;
        return enabled;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public RonanGamesGamemode getMode() {
        return mode;
    }

    public FileConfiguration getArenaConfig() {
        if (arenaFile == null)
            loadArenaConfig();
        return arenaConfig;
    }

    public void saveChanges() {
        FileConfiguration arenaConfig = getArenaConfig();
        arenaConfig.set("Enabled", isEnabled());
        try {
            arenaConfig.set("Protection.World", world.getName());
            arenaConfig.set("Protection.Pos1.X", getPos1().getBlockX());
            arenaConfig.set("Protection.Pos1.Y", getPos1().getBlockY());
            arenaConfig.set("Protection.Pos1.Z", getPos1().getBlockZ());
            arenaConfig.set("Protection.Pos2.X", getPos2().getBlockX());
            arenaConfig.set("Protection.Pos2.Y", getPos2().getBlockY());
            arenaConfig.set("Protection.Pos2.Z", getPos2().getBlockZ());
        } catch (NullPointerException e) {
            //Not fully set?
        }
        try {
            arenaConfig.set("Gamemode", getMode().name());
        } catch (NullPointerException e) {
            //Not fully set?
        }
        saveArenaFile(arenaConfig);
    }

    private void saveArenaFile(FileConfiguration arena) {
        try {
            arena.save(arenaFile);
            loadArenaConfig();
        } catch (IOException ex) {
            RonanGamesCorePlugin.getInstance().getLogger().log(Level.SEVERE, "Could not save to " + arenaFile.getName
                    (), ex);
        }
    }

    //Load File
    private void loadArenaConfig() {
        if (arenaFile == null)
            arenaFile = new File(pl.getAPI().getDataFolder().getPath() + File.separator + "games" + File.separator
                    + pl.getName() + File.separator + "arenas", path);
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
    }

    //Delete File
    public void deleteArenaFile() {
        if (arenaFile != null)
            arenaFile.delete();
    }

    public Expansion getExpansion() {
        return pl;
    }
}
