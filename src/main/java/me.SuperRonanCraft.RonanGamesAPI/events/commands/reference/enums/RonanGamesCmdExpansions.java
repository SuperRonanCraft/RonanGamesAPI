package me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.enums;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.expansion.CmdExpansionHelp;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.expansion.CmdExpansionInfo;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeAll;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.expansion.*;
import org.bukkit.command.CommandSender;

public enum RonanGamesCmdExpansions implements RonanGamesCmdAll {
    //KEEP IN ALPHABETICAL ORDER
    HELP(           new CmdExpansionHelp()),
    INFO(        new CmdExpansionInfo());

    RonanGamesCmdTypeExpansion cmd;

    RonanGamesCmdExpansions(RonanGamesCmdTypeExpansion cmd) {
        this.cmd = cmd;
    }

    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        cmd.execute(sendi, args, label, exp);
    }

    @Override
    public RonanGamesCmdTypeAll getCmd() {
        return cmd;
    }

    /*public boolean perm(CommandSender sendi) {
        return cmd.perm(sendi);
    }

    public String help() {
        return cmd.help();
    }

    public void usage(CommandSender sendi, String label) {
        cmd.usage(sendi, label);
    }

    public List<String> tabComplete(CommandSender sendi, String[] args) {
        if (cmd instanceof RonanGamesCmdTabComplete)
            return ((RonanGamesCmdTabComplete) cmd).tabComplete(sendi, args);
        return null;
    }*/
}