package me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.enums;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.arena.*;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeAll;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena.*;
import me.SuperRonanCraft.RonanGamesAPI.players.commands.arena.*;
import org.bukkit.command.CommandSender;

public enum RonanGamesCmdArenas implements RonanGamesCmdAll {
    //KEEP IN ALPHABETICAL ORDER
    CREATE(     new CmdArenaCreate()),
    DELETE(     new CmdArenaDelete()),
    DISABLE(    new CmdArenaDisable()),
    ENABLE(     new CmdArenaEnable()),
    HELP(       new CmdArenaHelp()),
    JOIN(       new CmdArenaJoin()),
    LIST(       new CmdArenaList()),
    SAVE(       new CmdArenaSave()),
    //RELOAD(     new CmdArenaReload()),
    SET(        new CmdArenaSet()),
    WAND(       new CmdArenaWand());

    RonanGamesCmdTypePlugin cmd;

    RonanGamesCmdArenas(RonanGamesCmdTypePlugin cmd) {
        this.cmd = cmd;
    }

    @Override
    public RonanGamesCmdTypeAll getCmd() {
        return cmd;
    }

    public void execute(CommandSender sendi, String[] args, String label) {
        cmd.execute(sendi, args, label);
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