package me.SuperRonanCraft.RonanGamesAPI.events.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.events.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesCore;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesHelp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdArenaWand implements RonanGamesCmdTypePlugin {

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        //if (pl.getWorldEdit() == null) {
        if (isPlayer(sendi)) {
            Player p = (Player) sendi;
            if (p.getInventory().contains(pl.getWand().getWandItem()))
                MessagesArena.WAND_ALREADY.send(sendi);
                //pl.getText().getLang().getArena().getWandAlready(sendi);
            else if (p.getInventory().firstEmpty() != -1) {
                //Bukkit.getWorld(p.getWorld().getNameCustom()).dropItem(p.getLocation(), pl.getWand().getWandItem());
                p.getInventory().addItem(pl.getWand().getWandItem());
                MessagesArena.WAND_SUCCESS.send(sendi);
                //pl.getText().getLang().getArena().getWandSuccess(sendi);
            } else
                MessagesArena.WAND_FULL.send(sendi);
                //pl.getText().getLang().getArena().getWandFull(sendi);
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
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_WAND.get(sendi, info);
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
