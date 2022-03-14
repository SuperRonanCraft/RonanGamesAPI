package me.SuperRonanCraft.RonanGamesAPI.events.commands.games;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.CommandGamesExpansion;
import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.enums.RonanGamesCmdGames;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CmdGamesHelp implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (!(sendi instanceof Player))
            pl.getLogger().log(Level.WARNING, "WARNING: Console may not be able to execute some of these commands!");

        //LIST OF ALLOWED COMMANDS
        List<String> list = new ArrayList<>();
        list.add(MessagesHelp.CORE_HEADER.get(sendi, label));
        for (RonanGamesCmdGames cmd : RonanGamesCmdGames.values())
            if (cmd.perm(sendi)) {
                String str = cmd.help(sendi, cmd);
                if (str != null)
                    list.add(str);
            }
        for (CommandGamesExpansion cmd : pl.getGamesCmd().getExpansionCmd())
            //if () //CHECK FOR PERMS
                list.add(MessagesHelp.EXPANSION_HELP.get(sendi, cmd.getExpansion()));
        list.add(MessagesHelp.CORE_FOOTER.get(sendi, label));
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
        return MessagesHelp.CORE_HELP.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
