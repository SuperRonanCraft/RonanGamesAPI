package me.SuperRonanCraft.RonanGamesAPI.events.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
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
        List<String> str = new ArrayList<>();
        str.add(MessagesArena.LIST_FOUND_HEADER.get(sendi, null));
        for (Expansion exp : arenas.keySet())
            str.add(MessagesArena.LIST_FOUND_CORE.get(sendi, exp)
                    .replaceAll("%arenas%", arenas.get(exp).toString()));
        str.add(MessagesArena.LIST_FOUND_FOOTER.get(sendi, null));
        Message.sms(sendi, str, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_LIST.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
