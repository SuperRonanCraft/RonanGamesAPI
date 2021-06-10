package me.SuperRonanCraft.RonanGamesAPI.info.players.commands;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums.RonanGamesCmdGames;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandGames implements CommandExecutor, TabCompleter {

    private List<CommandGamesExpansion> expCmd = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sendi, Command command, String label, String[] args) {
        if (!perm(RonanGamesCorePlugin.getInstance().getPerms().getUse(sendi), sendi))
            return false;
        if (args.length >= 1) {
            for (RonanGamesCmdGames cmd : RonanGamesCmdGames.values())
                if (cmd.name().equalsIgnoreCase(args[0])) {
                    cmd.execute(sendi, args, label);
                    return false;
                }
            for (CommandGamesExpansion cmd : expCmd)
                if (cmd.getExpansion().getNameCustom().equalsIgnoreCase(args[0])) {
                    cmd.onCommand(sendi, args, label);
                    return false;
                }
            MessagesCore.INVALID.send(sendi);
        } else
            RonanGamesCmdGames.HELP.execute(sendi, args, label);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sendi, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (RonanGamesCmdGames cmd : RonanGamesCmdGames.values())
                if (cmd.toString().startsWith(args[0].toUpperCase()) && cmd.perm(sendi))
                    list.add(cmd.toString().toLowerCase());
            for (CommandGamesExpansion cmd : expCmd)
                if (cmd.getExpansion().getNameCustom().toLowerCase().startsWith(args[0].toLowerCase())) //CHECK FOR PERMS
                    list.add(cmd.getExpansion().getNameCustom().toLowerCase());
        } else if (args.length > 1) {
            for (RonanGamesCmdGames cmd : RonanGamesCmdGames.values())
                if (cmd.toString().equalsIgnoreCase(args[0]) && cmd.perm(sendi)) {
                    List<String> customTab = cmd.tabComplete(sendi, args);
                    if (customTab != null)
                        list.addAll(customTab);
                    break;
                }
            for (CommandGamesExpansion cmd : expCmd)
                if (cmd.getExpansion().getNameCustom().equalsIgnoreCase(args[0])) //CHECK FOR PERMS
                    list.addAll(cmd.onTabComplete(sendi, alias, args));

        }
        return list;
    }

    public List<CommandGamesExpansion> getExpansionCmd() {
        return expCmd;
    }

    void addExpansionCmd(CommandGamesExpansion cmd) {
        expCmd.add(cmd);
        System.out.println("Hooked " + cmd.getExpansion().getNameCustom() + " for command listener!");
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