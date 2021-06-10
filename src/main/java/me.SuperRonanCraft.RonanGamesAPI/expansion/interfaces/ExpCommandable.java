package me.SuperRonanCraft.RonanGamesAPI.expansion.interfaces;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ExpCommandable {

    void onCommand(CommandSender sendi, String cmd, List<String> args);

    List<String> onTabComplete(CommandSender sendi, String label, List<String> args);
}
