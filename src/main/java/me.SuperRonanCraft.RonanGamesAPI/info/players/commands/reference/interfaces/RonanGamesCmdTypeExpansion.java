package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces;

import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import org.bukkit.command.CommandSender;

public interface RonanGamesCmdTypeExpansion extends RonanGamesCmdTypeAll { //ONLY EXPANSION COMMANDS!

    void execute(CommandSender sendi, String[] args, String label, Expansion exp);
}
