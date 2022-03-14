package me.SuperRonanCraft.RonanGamesAPI.events.commands;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.enums.RonanGamesCmdArenas;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandArena implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sendi, Command command, String label, String[] args) {
        if (!perm(RonanGamesCorePlugin.getInstance().getPerms().getUse(sendi), sendi))
            return false;
        if (args.length >= 1) {
            for (RonanGamesCmdArenas cmd : RonanGamesCmdArenas.values()) {
                if (cmd.name().equalsIgnoreCase(args[0])) {
                    cmd.execute(sendi, args, label);
                    return false;
                }
            }
            MessagesCore.INVALID.send(sendi);
        } else
            RonanGamesCmdArenas.HELP.execute(sendi, args, label);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sendi, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (RonanGamesCmdArenas cmd : RonanGamesCmdArenas.values())
                if (cmd.toString().startsWith(args[0].toUpperCase()) && cmd.perm(sendi))
                    list.add(cmd.toString().toLowerCase());
        } else if (args.length > 1) {
            for (RonanGamesCmdArenas cmd : RonanGamesCmdArenas.values())
                if (cmd.toString().equalsIgnoreCase(args[0]) && cmd.perm(sendi)) {
                    List<String> customTab = cmd.tabComplete(sendi, args);
                    if (customTab != null)
                        list.addAll(customTab);
                    break;
                }

        }
        return list;
    }

    //PROCESSING

    private boolean perm(boolean perm, CommandSender sendi) {
        if (!perm) {
            MessagesCore.NOPERMISSION.send(sendi);
            return false;
        }
        return true;
    }
}