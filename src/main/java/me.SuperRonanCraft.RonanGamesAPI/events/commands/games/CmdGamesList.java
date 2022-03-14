package me.SuperRonanCraft.RonanGamesAPI.events.commands.games;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesGame;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdGamesList implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        List<String> list = new ArrayList<>();
        if (RonanGamesCore.getRegisteredGames().values().isEmpty())
            MessagesGame.GAME_LIST_NONE.get(sendi, null);
        else {
            for (Expansion expansion : RonanGamesCore.getRegisteredGames().values())
                list.add(expansion.getNameCustom());
            String msg = MessagesGame.GAME_LIST.get(sendi, null);
            msg = msg.replace("%game_list%", Arrays.toString(list.toArray()));
            Message.sms(sendi, msg, null);
        }
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.CORE_LIST.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
