package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.games;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.CommandSender;

public class CmdGamesReload implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        pl.reload(true);
        MessagesCore.RELOAD.send(sendi);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return pl.getPerms().getReload(sendi);
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getReload();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    @Override
    public void noPerm(CommandSender sendi) {

    }
}
