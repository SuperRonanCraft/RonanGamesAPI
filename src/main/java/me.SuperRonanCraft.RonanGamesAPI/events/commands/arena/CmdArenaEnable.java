package me.SuperRonanCraft.RonanGamesAPI.events.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdArenaEnable implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (args.length == 3) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[2]);
                if (arena != null) {
                    if (arena.isEnabled())
                        MessagesArena.ENABLE_ALREADY.send(sendi, arena);
                        //pl.getText().getLang().getArena().getEnableAlready(sendi, arena.getName(), exp.getNameCustom());
                    else if (arena.enable())
                        MessagesArena.ENABLE_SUCCESS.send(sendi, arena);
                        //pl.getText().getLang().getArena().getEnableSuccess(sendi, arena.getName(), exp.getNameCustom());
                    else
                        MessagesArena.ENABLE_INVALID.send(sendi, arena);
                        //pl.getText().getLang().getArena().getEnableInvalid(sendi, arena.getName(), exp.getNameCustom());
                } else
                    MessagesArena.EXIST_ARENA.send(sendi, exp);
                    //pl.getText().getLang().getArena().getExistArena(sendi, args[1], exp.getNameCustom());
            } else
                MessagesArena.EXIST_GAME.send(sendi);
                //pl.getText().getLang().getArena().getExistGame(sendi, args[1]);
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
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_ENABLE.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    private List<String> getArenas(Expansion exp) {
        return new ArrayList<>(exp.getArena().getArenaIDs());
    }
}
