package me.SuperRonanCraft.RonanGamesAPI.events.commands.expansion;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdExpansionHelp implements RonanGamesCmdTypeExpansion {

    @Override
    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        List<String> list = new ArrayList<>();
        Object[] info = {label, exp};
        list.add(MessagesHelp.EXPANSION_HEADER.get(sendi, info));
        list.add(help(sendi, info));
        list.add(MessagesHelp.EXPANSION_INFO.get(sendi, info));
        list.add(MessagesHelp.EXPANSION_FOOTER.get(sendi, info));
        Message.sms(sendi, list, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.EXPANSION_HELP.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
