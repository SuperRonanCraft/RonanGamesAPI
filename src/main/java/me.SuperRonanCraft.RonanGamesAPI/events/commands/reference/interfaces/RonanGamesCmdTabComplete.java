package me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface RonanGamesCmdTabComplete {

    List<String> tabComplete(CommandSender sendi, String[] args);

    default void getListGame(List<String> list, String arg) {
        for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
            if (exp.getNameCustom().toLowerCase().startsWith(arg.toLowerCase()))
                list.add(exp.getNameCustom());
    }
}
