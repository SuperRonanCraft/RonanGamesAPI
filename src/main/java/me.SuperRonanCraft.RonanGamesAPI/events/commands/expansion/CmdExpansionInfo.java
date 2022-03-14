package me.SuperRonanCraft.RonanGamesAPI.events.commands.expansion;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesGame;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;

public class CmdExpansionInfo implements RonanGamesCmdTypeExpansion {

    @Override
    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        MessagesGame.GAME_INFO.send(sendi, exp);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.EXPANSION_INFO.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
