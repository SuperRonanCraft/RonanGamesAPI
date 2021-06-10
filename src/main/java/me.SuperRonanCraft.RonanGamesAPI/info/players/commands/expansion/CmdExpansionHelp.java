package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.expansion;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.LangHelpCore;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdExpansionHelp implements RonanGamesCmdTypeExpansion {

    @Override
    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        List<String> list = new ArrayList<>();
        list.add(getHelpMsg().getGameHeader());
        list.add(getHelpMsg().getGameAuthor().replaceAll("%command%", label).replaceAll("%game%", exp.getNameCustom()));
        list.add(getHelpMsg().getGameVersion().replaceAll("%command%", label).replaceAll("%game%", exp.getNameCustom()));
        //for (String cmd : cmds)
        //    list.add(getHelpMsg().getGameCore().replaceAll("%command%", cmd).replaceAll("%game%", label));
        list.add(getHelpMsg().getGameFooter());
        list.forEach(str ->
                list.set(list.indexOf(str), str.replaceAll("%game%", exp.getNameCustom()))
        );
        Message.sms(sendi, list, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }

    private LangHelpCore getHelpMsg() {
        return RonanGamesCorePlugin.getInstance().getText().getLang().getHelp();
    }
}
