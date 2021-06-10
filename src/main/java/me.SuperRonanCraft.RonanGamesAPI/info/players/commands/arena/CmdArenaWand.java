package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdArenaWand implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        //if (pl.getWorldEdit() == null) {
        if (isPlayer(sendi)) {
            Player p = (Player) sendi;
            if (p.getInventory().contains(pl.getWand().getWandItem()))
                pl.getText().getLang().getArena().getWandAlready(sendi);
            else if (p.getInventory().firstEmpty() != -1) {
                //Bukkit.getWorld(p.getWorld().getNameCustom()).dropItem(p.getLocation(), pl.getWand().getWandItem());
                p.getInventory().addItem(pl.getWand().getWandItem());
                pl.getText().getLang().getArena().getWandSuccess(sendi);
            } else
                pl.getText().getLang().getArena().getWandFull(sendi);
        }
        //} else
        //invalid(sendi, label);
        else
            MessagesCore.INVALID.send(sendi, label);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaWand();
    }

    @Override
    public void usage(CommandSender sendi, String label) {

    }

    private boolean isPlayer(CommandSender sendi) {
        if (sendi instanceof Player)
            return true;
        else
            Message.sms(sendi, "&cWARNING&7: &eYou are not able to execute this command " +
                    "from here!", null);
        return false;
    }
}
