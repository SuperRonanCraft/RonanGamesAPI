package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesUsage;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdArenaDisable implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (args.length == 3) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[2]);
                if (arena != null) {
                    if (!arena.isEnabled())
                        MessagesArena.DISABLE_ALREADY.send(sendi, arena);
                        //pl.getText().getLang().getArena().getDisableAlready(sendi, arena.getName(), exp.getNameCustom());
                    else {
                        arena.disable();
                        MessagesArena.DISABLE_SUCCESS.send(sendi, arena);
                        //pl.getText().getLang().getArena().getDisableSuccess(sendi, arena.getName(), exp.getNameCustom());
                    }
                } else
                    MessagesArena.EXIST_ARENA.send(sendi, exp);
                    //pl.getText().getLang().getArena().getExistArena(sendi, args[2], exp.getNameCustom());
            } else
                MessagesArena.EXIST_GAME.send(sendi);
                //pl.getText().getLang().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label);
    }

    //Disable <game> <arena>
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
        return pl.getPerms().getArenaAdmin(sendi);
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_DISABLE.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {
        MessagesUsage.ARENA_DISABLE.send(sendi, label);
    }

    private List<String> getArenas(Expansion exp) {
        return new ArrayList<>(exp.getArena().getArenaIDs());
    }
}
