package me.SuperRonanCraft.RonanGamesAPI.events.commands.OLD;

import java.util.*;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;

public class CMDLobby {}/* implements CommandExecutor {
    RonanGamesCorePlugin pl;

    public CMDLobby(RonanGamesCorePlugin pl) {
        this.pl = pl;
    }

    public boolean onCommand(CommandSender sendi, Command cmd, String label, String[] args) {
        if (perm(pl.getPermissions().getArenaAdmin(sendi), sendi)) {
            if (args.length == 0)
                help(sendi, label);
            else {
                if (args[0].equalsIgnoreCase("help"))
                    help(sendi, label);
                else if (args[0].equalsIgnoreCase("create"))
                    create(sendi, label, args);
                else if (args[0].equalsIgnoreCase("list"))
                    list(sendi);
                else if (args[0].equalsIgnoreCase("wand"))
                    wand(sendi, label, args);
                else
                    invalid(sendi, label);
            }
        }
        return false;
    }

    private void wand(CommandSender sendi, String label, String[] args) {
        //if (pl.getWorldEdit() == null)
            if (sendi instanceof Player) {
                Player p = (Player) sendi;
                p.getInventory().addItem(pl.getWand().getWandItem());
            } else
                notPlayer(sendi);
        //else
            //invalid(sendi, label);
    }

    private void create(CommandSender sendi, String label, String[] args) {
        if (sendi instanceof Player)
            if (args.length == 3) {
                Map<String, Expansion> e = RonanGamesCore.getRegisteredGames();
                if (e.containsKey(args[1]))
                    e.get(args[1]).getArena().createArena(args[2]);
                //else
                //pl.getMessages().getExist(sendi, args[1]);
            } else
                usage(sendi, label, "create");
        else
            notPlayer(sendi);
    }

    private void list(CommandSender sendi) {
        HashMap<Expansion, Set<String>> gameArenas = new HashMap<>();
        for (Entry<String, Expansion> pl : RonanGamesCore.getRegisteredGames().entrySet()) {
            Set<String> arenas = pl.getValue().getArena().getArenaIDs();
            if (!arenas.isEmpty())
                gameArenas.put(pl.getValue(), arenas);
        }
        if (!gameArenas.isEmpty())
            pl.getMessages().getArena().getListFound(sendi, gameArenas);
        else
            pl.getMessages().getArena().getListNone(sendi);
    }

    private void usage(CommandSender sendi, String label, String str) {
        if (str.equals("create"))
            pl.getUsage().getArenaCreate(sendi, label);
    }

    private void help(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &fNot all commands are executable from here!"));
        pl.getHelp().getArenaHelp(sendi, label);
        pl.getHelp().getArenaCreate(sendi, label);
        pl.getHelp().getArenaList(sendi, label);
        pl.getHelp().getArenaWand(sendi, label);
    }

    boolean perm(boolean perm, CommandSender sendi) {
        if (perm)
            return true;
        else
            pl.getMessages().getNoPermission(sendi);
        return false;
    }

    private void notPlayer(CommandSender sendi) {
        sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &eYou are not able to execute this command from " +
                "here!"));
    }

    void invalid(CommandSender sendi, String label) {
        pl.getMessages().getInvalid(sendi, label);
    }
}*/
