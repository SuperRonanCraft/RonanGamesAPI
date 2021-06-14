package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums.RonanGamesCmdArenas;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CmdArenaHelp implements RonanGamesCmdTypePlugin {

    private final String[] cmds = {"reload", "help", "create", "set", "list", "wand", "enable", "disable", "join"};

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (!(sendi instanceof Player))
            pl.getLogger().log(Level.WARNING, "WARNING: Console may not be able to execute some of these commands!");

        //LIST OF ALLOWED COMMANDS
        List<String> list = new ArrayList<>();
        list.add(MessagesHelp.ARENA_HEADER.get(sendi, label));
        for (RonanGamesCmdArenas cmd : RonanGamesCmdArenas.values())
            if (cmd.perm(sendi)) {
                String str = cmd.help(sendi, cmd);
                if (str != null)
                    list.add(str);
            }

        list.add(MessagesHelp.ARENA_FOOTER.get(sendi, label));
        list.forEach(str -> list.set(list.indexOf(str),
                str.replace("%command%", label)));
        Message.sms(sendi, list, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_HELP.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
