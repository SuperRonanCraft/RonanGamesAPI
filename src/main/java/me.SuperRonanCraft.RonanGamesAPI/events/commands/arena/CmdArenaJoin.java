package me.SuperRonanCraft.RonanGamesAPI.events.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
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
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_JOIN.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
