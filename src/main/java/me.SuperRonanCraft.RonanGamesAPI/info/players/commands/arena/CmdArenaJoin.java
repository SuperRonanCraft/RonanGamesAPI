package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import org.bukkit.command.CommandSender;

public class CmdArenaJoin implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {

    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaJoin();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
