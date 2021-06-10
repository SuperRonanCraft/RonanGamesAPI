package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import org.bukkit.command.CommandSender;

import java.util.*;

public class CmdArenaList implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        //if (args.length == 1) {
        HashMap<Expansion, Set<String>> gameArenas = new HashMap<>();
        for (Map.Entry<String, Expansion> pl : RonanGamesCore.getRegisteredGames().entrySet()) {
            Set<String> arenas = pl.getValue().getArena().getArenaIDs();
            if (!arenas.isEmpty())
                gameArenas.put(pl.getValue(), arenas);
        }
        if (!gameArenas.isEmpty())
            getListFound(sendi, gameArenas);
        else
            MessagesArena.LIST_NONE.send(sendi);
        //} else
        //    pl.getText().getLang().getCore().getInvalidCommand(sendi, label);
    }

    private void getListFound(CommandSender sendi, HashMap<Expansion, Set<String>> arenas) {
       List<String> str = MessagesArena.LIST_FOUND_HEADER.getList(sendi, null);
        for (Expansion exp : arenas.keySet())
            str.add(MessagesArena.LIST_FOUND_CORE.get(sendi, null)
                    .replaceAll("%game%", exp.getNameCustom())
                    .replaceAll("%arenas%", arenas.get(exp).toString()));
        str.addAll(MessagesArena.LIST_FOUND_FOOTER.getList(sendi, null));
        Message.sms(sendi, str, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaList();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
