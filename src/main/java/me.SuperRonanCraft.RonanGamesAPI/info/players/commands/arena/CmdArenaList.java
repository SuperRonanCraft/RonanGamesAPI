package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
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
            pl.getText().getLang().getArena().getListFound(sendi, gameArenas);
        else
            pl.getText().getLang().getArena().getListNone(sendi);
        //} else
        //    pl.getText().getLang().getCore().getInvalidCommand(sendi, label);
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
