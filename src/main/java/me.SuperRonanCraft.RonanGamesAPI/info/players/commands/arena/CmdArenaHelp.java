package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums.RonanGamesCmdArenas;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.LangHelpCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class CmdArenaHelp implements RonanGamesCmdTypePlugin {

    private String[] cmds = {"reload", "help", "create", "set", "list", "wand", "enable", "disable", "join"};

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        LangHelpCore help = pl.getText().getLang().getHelp();
        if (!(sendi instanceof Player))
            pl.getLogger().log(Level.WARNING, "WARNING: Console may not be able to execute some of these commands!");

        //LIST OF ALLOWED COMMANDS
        List<String> list = help.getHeader();
        for (RonanGamesCmdArenas cmd : RonanGamesCmdArenas.values())
            if (cmd.perm(sendi)) {
                String str = cmd.help();
                if (str != null)
                    list.add(str);
            }

        list.addAll(help.getFooter());
        list.forEach(str -> list.set(list.indexOf(str),
                str.replace("%command%", label)));
        help.sms(sendi, list);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaHelp();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }
}
