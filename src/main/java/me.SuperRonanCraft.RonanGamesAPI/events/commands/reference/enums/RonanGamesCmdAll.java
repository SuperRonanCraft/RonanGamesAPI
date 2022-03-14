package me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.enums;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeAll;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface RonanGamesCmdAll {

    RonanGamesCmdTypeAll getCmd();

    default boolean perm(CommandSender sendi) {
        return getCmd().perm(sendi);
    }

    default String help(CommandSender sendi, Object info) {
        return getCmd().help(sendi, info);
    }

    default void usage(CommandSender sendi, String label) {
        getCmd().usage(sendi, label);
    }

    default List<String> tabComplete(CommandSender sendi, String[] args) {
        if (getCmd() instanceof RonanGamesCmdTabComplete)
            return ((RonanGamesCmdTabComplete) getCmd()).tabComplete(sendi, args);
        return null;
    }
}