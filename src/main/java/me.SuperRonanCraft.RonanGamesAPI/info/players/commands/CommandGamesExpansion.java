package me.SuperRonanCraft.RonanGamesAPI.info.players.commands;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums.RonanGamesCmdExpansions;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandGamesExpansion {

    private Expansion pl;

    public CommandGamesExpansion(Expansion pl) {
        this.pl = pl;
        RonanGamesCorePlugin.getInstance().getGamesCmd().addExpansionCmd(this);
    }

    public boolean onCommand(CommandSender sendi, String[] args, String label) {
        if (!perm(RonanGamesCorePlugin.getInstance().getPerms().getUse(sendi), sendi))
            return false;
        if (args.length >= 2) {
            for (RonanGamesCmdExpansions cmd : RonanGamesCmdExpansions.values()) {
                if (cmd.name().equalsIgnoreCase(args[1])) {
                    cmd.execute(sendi, args, label, pl);
                    return false;
                }
            }
            MessagesCore.INVALID.send(sendi);
        } else
            RonanGamesCmdExpansions.HELP.execute(sendi, args, label, pl);
        return true;
    }

    List<String> onTabComplete(CommandSender sendi, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 2) {
            for (RonanGamesCmdExpansions cmd : RonanGamesCmdExpansions.values())
                if (cmd.toString().startsWith(args[1].toUpperCase()) && cmd.perm(sendi))
                    list.add(cmd.toString().toLowerCase());
        } else if (args.length > 2) {
            for (RonanGamesCmdExpansions cmd : RonanGamesCmdExpansions.values())
                if (cmd.toString().equalsIgnoreCase(args[1]) && cmd.perm(sendi)) {
                    List<String> customTab = cmd.tabComplete(sendi, args);
                    if (customTab != null)
                        list.addAll(customTab);
                    break;
                }

        }
        return list;
    }

    public Expansion getExpansion() {
        return pl;
    }

    private boolean perm(boolean perm, CommandSender sendi) {
        if (!perm) {
            MessagesCore.NOPERMISSION.send(sendi);
            return false;
        }
        return true;
    }

}
