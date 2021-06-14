package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.expansion;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import org.bukkit.command.CommandSender;

public class CmdExpansionAuthor implements RonanGamesCmdTypeExpansion {

    @Override
    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return null;
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
