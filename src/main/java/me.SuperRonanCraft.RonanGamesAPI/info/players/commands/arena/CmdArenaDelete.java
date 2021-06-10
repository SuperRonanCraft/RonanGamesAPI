package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdLoadable;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CmdArenaDelete implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete, RonanGamesCmdLoadable {

    private HashMap<CommandSender, Integer> confirmDelay = new HashMap<>();

    @Override
    public void load() {

    }

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (args.length == 3) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[2]);
                if (arena != null) {
                    if (confirmDelay.get(sendi) == null)
                        pl.getText().getLang().getArena().getSaveInvalid(sendi, arena.getName(), exp.getNameCustom());
                } else
                    pl.getText().getLang().getArena().getExistArena(sendi, args[1], exp.getNameCustom());
            } else
                pl.getText().getLang().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label);
    }

    @Override
    public List<String> tabComplete(CommandSender sendi, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 2)
            //<game>
            getListGame(list, args[1]);
        else if (args.length == 3)
            //<arena>
            list = getArenas(RonanGamesCore.getRegisteredGames().get(args[1]));
        return list;
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaEnable();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    private List<String> getArenas(Expansion exp) {
        return new ArrayList<>(exp.getArena().getArenaIDs());
    }
}
