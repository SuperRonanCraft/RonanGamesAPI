package me.SuperRonanCraft.RonanGamesAPI.events.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdArenaCreate implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (isPlayer(sendi))
            if (args.length == 3) {
                String expName = args[1];
                for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
                    if (exp.getNameCustom().equalsIgnoreCase(args[1])) {
                        expName = exp.getNameCustom();
                        break;
                    }
                Expansion exp = RonanGamesCore.getRegisteredGames().get(expName);
                if (exp != null) {
                    Arena arena = exp.getArena().createArena(args[2]);
                    if (arena != null)
                        MessagesArena.CREATE_SUCCESS.send(sendi, arena);
                    else
                        MessagesArena.CREATE_ALREADY.send(sendi, exp.getArena().getArena(args[2]));
                } else
                    MessagesArena.EXIST_GAME.send(sendi);
            } else
                usage(sendi, label);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_CREATE.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {
        MessagesUsage.ARENA_CREATE.send(sendi, label);
    }

    @Override
    public List<String> tabComplete(CommandSender sendi, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 2)
            //Create <game>
            getListGame(list, args[1]);
        return list;
    }

    private boolean isPlayer(CommandSender sendi) {
        if (sendi instanceof Player)
            return true;
        else
            Message.sms(sendi, "&cWARNING&7: &eYou are not able to " +
                    "execute this command from here!", null);
        return false;
    }
}
