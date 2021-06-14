package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.RonanGamesGamemode;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.*;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdArenaSet implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete {

    private final String[] setcmds = {"protection", "gamemode", "teams", "help"};

    @Override
    public void execute(CommandSender sendi, String[] args, String label) {
        if (args.length == 1)
            helpSet(sendi, label);
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase(setcmds[3]))
                helpSet(sendi, label);
            else
                usage(sendi, label);
        } else if (args.length >= 4 && args.length <= 5) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[2]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[3]);
                if (arena != null) {
                    if (args[1].equalsIgnoreCase(setcmds[0]) && args.length == 4 && isPlayer(sendi)) { //Protection
                        List<Location> loc = pl.getWand().getSelection((Player) sendi, label);
                        if (loc != null) {
                            if (!loc.isEmpty()) {
                                arena.setProtection(loc.get(0), loc.get(1));
                                MessagesArena.SET_PROTECTION_SUCCESS.send(sendi);
                            }
                        } else
                            MessagesArena.SET_PROTECTION_ERROR.send(sendi);
                            //pl.getText().getLang().getArena().getSetProtectionError(sendi, label);
                    } else if (args[1].equalsIgnoreCase(setcmds[1]) && args.length == 5) { //Gamemode
                        RonanGamesGamemode gamemode = arena.setGamemode(args[4].toUpperCase());
                        if (gamemode != null)
                            MessagesArena.SET_GAMEMODE_SUCCESS.send(sendi, gamemode);
                        else
                            MessagesArena.SET_GAMEMODE_EXIST.send(sendi);
                    } else if (args[1].equalsIgnoreCase(setcmds[2]) && args.length == 5) { //Teams
                        //TEAMS TO-DO!
                        return;
                    } else
                        usage(sendi, label);
                } else
                    MessagesArena.EXIST_ARENA.send(sendi, exp);
                    //pl.getText().getLang().getArena().getExistArena(sendi, args[3], exp.getNameCustom());
            } else
                MessagesArena.EXIST_GAME.send(sendi);
                //pl.getText().getLang().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label);
    }

    private void helpSet(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(Message.color("&cWARNING&7: &fNot all commands are " +
                    "executable from here!"));
        List<String> list = new ArrayList<>();
        list.add(MessagesHelp.ARENA_HEADER.get(sendi, label));
        list.add(MessagesHelp.ARENA_SET_HELP.get(sendi, label));
        list.add(MessagesHelp.ARENA_SET_GAMEMODE.get(sendi, label));
        list.add(MessagesHelp.ARENA_SET_PROTECTION.get(sendi, label));
        list.add(MessagesHelp.ARENA_SET_TEAMS.get(sendi, label));
        list.add(MessagesHelp.ARENA_FOOTER.get(sendi, label));
        list.forEach(str ->
                list.set(list.indexOf(str), Message.color(str.replace("%command%", label))));
        Message.sms(sendi, list, null);
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help(CommandSender sendi, Object info) {
        return MessagesHelp.ARENA_SET_HELP.get(sendi, info);
    }

    @Override
    public void usage(CommandSender sendi, String label) {
        MessagesUsage.ARENA_SET.send(sendi, label);
    }

    @Override
    public List<String> tabComplete(CommandSender sendi, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 2) {
            //Set <subcmd>
            for (String subcmd : setcmds)
                if (subcmd.startsWith(args[1].toLowerCase()))
                    list.add(subcmd);
        } else if (args.length >= 3 && !args[1].equalsIgnoreCase(setcmds[3])) {
            if (args.length == 3) {
                //Set <subcmd> <game>
                getListGame(list, args[2]);
            } else if (args.length == 4) {
                //Set <subCmd> <game> <arena>
                getListArena(list, args[3]);
            } else if (args.length == 5) {
                if (args[1].equalsIgnoreCase(setcmds[1])) {
                    //Set <subcmd> <game> <arena> <gamemode>
                    for (RonanGamesGamemode gm : RonanGamesGamemode.values())
                        if (gm.name().toLowerCase().startsWith(args[4].toLowerCase()))
                            list.add(gm.name());
                } //else - Set <subcmd> <game> <arena> <teams-amount>
            }
        }
        return list;
    }

    private void getListArena(List<String> list, String arg) {
        for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
            for (String arena : exp.getArena().getArenaIDs())
                if (arena.toLowerCase().startsWith(arg.toLowerCase()))
                    list.add(arena);
    }

    //COMMANDS

    private boolean isPlayer(CommandSender sendi) {
        if (sendi instanceof Player)
            return true;
        else
            sendi.sendMessage(Message.color("&cWARNING&7: &eYou are not able to " +
                    "execute this command from here!"));
        return false;
    }
}
