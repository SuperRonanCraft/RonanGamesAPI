package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import org.bukkit.command.CommandSender;

public class CmdGamesList implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {

    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getCoreList();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
