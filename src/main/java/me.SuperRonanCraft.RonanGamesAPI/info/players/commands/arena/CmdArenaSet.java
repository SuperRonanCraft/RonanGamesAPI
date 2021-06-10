package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.arena;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.RonanGamesGamemode;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTypePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.players.commands.reference.interfaces.RonanGamesCmdTabComplete;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.LangHelpCore;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdArenaSet implements RonanGamesCmdTypePlugin, RonanGamesCmdTabComplete {

    private String[] setcmds = {"protection", "gamemode", "teams", "help"};

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
                                pl.getText().getLang().getArena().getSetProtectionSuccess(sendi);
                            }
                        } else
                            pl.getText().getLang().getArena().getSetProtectionError(sendi, label);
                    } else if (args[1].equalsIgnoreCase(setcmds[1]) && args.length == 5) { //Gamemode
                        if (arena.setGamemode(args[4].toUpperCase()))
                            pl.getText().getLang().getArena().getSetGamemodeSuccess(sendi,
                                    RonanGamesGamemode.valueOf(args[4].toUpperCase()).getDesc());
                        else
                            pl.getText().getLang().getArena().getSetGamemodeExist(sendi, args[4].toUpperCase());
                    } else if (args[1].equalsIgnoreCase(setcmds[2]) && args.length == 5) { //Teams
                        //TEAMS TO-DO!
                        return;
                    } else
                        usage(sendi, label);
                } else
                    pl.getText().getLang().getArena().getExistArena(sendi, args[3], exp.getNameCustom());
            } else
                pl.getText().getLang().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label);
    }

    private void helpSet(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(Message.color("&cWARNING&7: &fNot all commands are " +
                    "executable from here!"));
        LangHelpCore lang = pl.getText().getLang().getHelp();
        List<String> list = lang.getHeader();
        list.add(lang.getArenaSetHelp());
        list.add(lang.getArenaSetGamemode());
        list.add(lang.getArenaSetProtection());
        list.add(lang.getArenaSetTeams());
        list.addAll(lang.getFooter());
        list.forEach(str ->
                list.set(list.indexOf(str), Message.color(str.replace("%command%", label))));
        sendi.sendMessage(list.toArray(new String[0]));
    }

    @Override
    public boolean perm(CommandSender sendi) {
        return true;
    }

    @Override
    public String help() {
        return pl.getText().getLang().getHelp().getArenaSet();
    }

    @Override
    public void usage(CommandSender sendi, String label) {
        pl.getText().getLang().getUsage().getArenaSet(sendi, label);
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
