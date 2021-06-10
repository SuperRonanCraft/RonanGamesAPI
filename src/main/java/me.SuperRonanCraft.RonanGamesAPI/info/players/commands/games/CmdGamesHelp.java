package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.CommandGamesExpansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.enums.RonanGamesCmdGames;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.LangHelpCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class CmdGamesHelp implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        LangHelpCore help = pl.getText().getLang().getHelp();
        if (!(sendi instanceof Player))
            pl.getLogger().log(Level.WARNING, "WARNING: Console may not be able to execute some of these commands!");

        //LIST OF ALLOWED COMMANDS
        List<String> list = help.getHeader();
        for (RonanGamesCmdGames cmd : RonanGamesCmdGames.values())
            if (cmd.perm(sendi)) {
                String str = cmd.help();
                if (str != null)
                    list.add(str);
            }
        for (CommandGamesExpansion cmd : pl.getGamesCmd().getExpansionCmd())
            //if () //CHECK FOR PERMS
                list.add(help.getCoreExpansion(cmd.getExpansion().getNameCustom()));
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
        return pl.getText().getLang().getHelp().getCoreHelp();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
