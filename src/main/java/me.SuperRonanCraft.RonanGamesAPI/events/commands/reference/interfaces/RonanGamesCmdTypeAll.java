package me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.CommandSender;

public interface RonanGamesCmdTypeAll {

    RonanGamesCorePlugin pl = RonanGamesCorePlugin.getInstance();

    boolean perm(CommandSender sendi);

    String help(CommandSender sendi, Object info);

    void usage(CommandSender sendi, String label);

    default void noPerm(CommandSender sendi) {
        MessagesCore.NOPERMISSION.send(sendi);
    }
}
