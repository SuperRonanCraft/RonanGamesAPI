package me.SuperRonanCraft.RonanGamesAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.CommandGamesExpansion;
import org.bukkit.Bukkit;

import me.SuperRonanCraft.RonanGamesAPI.expansion.interfaces.ExpCommandable;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class RonanGamesCore {
    private static Map<String, Expansion> games = new HashMap<>();
    private static Map<Expansion, CommandGamesExpansion> cmds = new HashMap<>();

    /**
     * Register the game with the API to have a reference to it later
     *
     * @return if the game is able to be registered
     **/
    public static boolean registerGame(Expansion exp) {
        if (exp.getNameCustom() == null || games.containsKey(exp.getNameCustom())) {
            RonanGamesCorePlugin.getInstance().getLogger().warning("Unable to register game " + exp.getName());
            return false;
        }
        if (exp instanceof ExpCommandable)
            cmds.put(exp, new CommandGamesExpansion(exp));
        games.put(exp.getNameCustom(), exp);
        return true;
    }

    static boolean unregisterAll() {
        if (games == null || games.isEmpty())
            return false;
        int size = 0;
        for (Entry<String, Expansion> pl : games.entrySet())
            if (unregisterGameHook(pl.getValue()))
                size++;
        RonanGamesCorePlugin pl = RonanGamesCorePlugin.getInstance();
        pl.outputDebugMessage(Bukkit.getConsoleSender(), "[" + RonanGamesCorePlugin
                .getInstance().getName() + "] " + size + " &fexpansions unloaded!");
        games.clear();
        cmds.clear();
        return true;
    }

    private static boolean unregisterGameHook(Expansion plugin) {
        return (plugin != null && unregisterGameHook(plugin.getNameCustom()));
    }

    private static boolean unregisterGameHook(String plugin) {
        if (plugin == null || games == null || games.isEmpty() || !games.containsKey(plugin))
            return false;
        games.remove(plugin);
        return true;
    }

    public static Map<String, Expansion> getRegisteredGames() {
        return games;
    }
}
