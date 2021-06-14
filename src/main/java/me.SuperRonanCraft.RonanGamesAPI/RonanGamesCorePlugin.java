package me.SuperRonanCraft.RonanGamesAPI;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.events.ArenaProtection;
import me.SuperRonanCraft.RonanGamesAPI.info.events.WandSelector;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.Board;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.CommandArena;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.CommandGames;
import me.SuperRonanCraft.RonanGamesAPI.info.players.PlayerInfo;
import me.SuperRonanCraft.RonanGamesAPI.references.Permissions;
import me.SuperRonanCraft.RonanGamesAPI.references.Points;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileBasics;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RonanGamesCorePlugin extends JavaPlugin {

    private final Points points = new Points(this);
    private final Permissions perms = new Permissions();
    private static RonanGamesCorePlugin instance;
    private WandSelector localSelector;
    private Board signBoard;
    private final PlayerInfo pInfo = new PlayerInfo();
    //private CMDArenas arenasCmd = new CMDArenas(this);
    //private CMDGames gamesCmd = new CMDGames(this);
    private boolean debug;
    private final FileManager yamlFiles = new FileManager();
    //COMMANDS
    private final CommandGames games = new CommandGames();
    private final CommandArena arena = new CommandArena();

    @Override
    public void onEnable() {
        //if (getWorldGuard() == null)
        //    getLogger().log(Level.WARNING, this.getNameCustom() + " WorldGuard not found! Arenas will not be protected!");
        instance = this;
        reload(false);
        registerCommands();
        new ArenaProtection(this);
    }

    @Override
    public void onDisable() {
        try {
            for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
                for (String arena : exp.getArena().getArenaIDs())
                    exp.getArena().getArena(arena).saveChanges();
            if (!RonanGamesCore.unregisterAll())
                outputDebugMessage(Bukkit.getConsoleSender(), "&e" + getName() + "&7: &fNo expansions to unloaded!");
        } catch (NoClassDefFoundError e) {
            outputDebugMessage(Bukkit.getConsoleSender(), getName() + ": No expansions to unloaded!");
        }
    }

    public CommandGames getGamesCmd() {
        return games;
    }

    public WandSelector getWand() {
        try {
            if (localSelector == null)
                localSelector = new WandSelector(this);
        } catch (AuthorNagException e) {
            //
        }
        return localSelector;
    }

    public Board getBoard() {
        if (signBoard == null)
            signBoard = new Board(this);
        return signBoard;
    }

    public PlayerInfo getPlayerInfo() {
        return pInfo;
    }

	/*public void registerProtection(String id, Location pos1, Location pos2, String game) {
		if (getWorldGuard() != null) {
			WorldGuardPlugin wg = getWorldGuard();
			ProtectedCuboidRegion region = new Protected
			'uboidRegion(id + "_" + game,
					new BlockVector(pos1.getBlockX(), pos1.getBlockY(), pos1.getBlockZ()),
					new BlockVector(pos2.getBlockX(), pos2.getBlockY(), pos2.getBlockZ()));
			wg.getRegionManager(pos1.getWorld()).addRegion(region);
		}
	}*/

    public List<Location> getSelection(Player p, String label) {
        /*if (getWorldEdit() != null) {
            Selection sel = getWorldEdit().getSession(p).getSelection(p.getWorld());
            List<Location> list = new ArrayList<>();
            list.add(sel.);
            list.add(sel.getMinimumPoint());
            return list;
        } else
            return getWand().getSelection(p, label);*/
        return null;
    }

    /*private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null)// || !(plugin instanceof WorldGuardPlugin))
            return null;
        return (WorldGuardPlugin) plugin;
    }*/

    /*public WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (plugin == null || !(plugin instanceof WorldEditPlugin))
            return null;
        return (WorldEditPlugin) plugin;
    }*/

    private void registerCommands() {
        this.getCommand("arena").setExecutor(arena);
        this.getCommand("games").setExecutor(games);
        this.getCommand("arena").setTabCompleter(arena);
        this.getCommand("games").setTabCompleter(games);
    }

    public static RonanGamesCorePlugin getInstance() {
        return instance;
    }

    public FileManager getYaml() {
        return yamlFiles;
    }

    public Permissions getPerms() {
        return perms;
    }

    public Points getPoints() {
        return points;
    }

    public void reload(boolean reload) {
        yamlFiles.loadAll();
        this.debug = getYaml().getType(FileBasics.FILETYPE.CONFIG).getBoolean("Settings.DebugMessages");
        points.load();
        for (Expansion exp : RonanGamesCore.getRegisteredGames().values()) {
            exp.getArena().load();
            exp.reload();
        }
        getWand().load();
    }

    void outputDebugMessage(CommandSender sendi, String msg) {
        if (debug)
            sendi.sendMessage(msg);
    }
}
