package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.expansion;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypeExpansion;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import org.bukkit.command.CommandSender;

public class CmdExpansionVersion implements RonanGamesCmdTypeExpansion {

    @Override
    public void execute(CommandSender sendi, String[] args, String label, Expansion exp) {
        Message.sms(sendi, RonanGamesCorePlugin.getInstance().getText().getLang().getGame().getVersion()
                .replaceAll("%game%", exp.getNameCustom()).replaceAll("%version%", exp.getVersion()).replaceAll("%command%", label), null);
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
}
