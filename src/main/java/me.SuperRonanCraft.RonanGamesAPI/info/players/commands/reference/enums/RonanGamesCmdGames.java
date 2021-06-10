package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games.CmdGamesHelp;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games.CmdGamesList;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games.CmdGamesReload;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdLoadable;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTabComplete;
import org.bukkit.command.CommandSender;

import java.util.List;

public enum RonanGamesCmdGames {
    //KEEP IN ALPHABETICAL ORDER
    HELP(           new CmdGamesHelp()),
    LIST(           new CmdGamesList()),
    RELOAD(         new CmdGamesReload());

    private RonanGamesCmdTypePlugin cmd;

    RonanGamesCmdGames(RonanGamesCmdTypePlugin cmd) {
        this.cmd = cmd;
    }

    public void execute(CommandSender sendi, String[] args, String label) {
        cmd.execute(sendi, args, label);
    }

    public boolean perm(CommandSender sendi) {
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
    }

    public RonanGamesCmdTypePlugin getCmd() {
        return cmd;
    }

    public void load() {
        if (cmd instanceof RonanGamesCmdLoadable)
            ((RonanGamesCmdLoadable) cmd).load();
    }
}