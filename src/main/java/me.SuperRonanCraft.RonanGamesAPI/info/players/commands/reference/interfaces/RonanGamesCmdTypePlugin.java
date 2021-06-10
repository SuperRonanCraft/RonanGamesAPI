package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces;

import org.bukkit.command.CommandSender;

public interface RonanGamesCmdTypePlugin extends RonanGamesCmdTypeAll { //ONLY CORE COMMANDS!

    void execute(CommandSender sendi, String[] args, String label);
}
