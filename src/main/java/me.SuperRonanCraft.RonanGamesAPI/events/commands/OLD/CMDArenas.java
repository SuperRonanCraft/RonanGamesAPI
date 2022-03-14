package me.SuperRonanCraft.RonanGamesAPI.events.commands.OLD;

public class CMDArenas {//implements CommandExecutor, TabCompleter {
}/*    private RonanGamesCorePlugin pl;

    public CMDArenas(RonanGamesCorePlugin pl) {
        this.pl = pl;
    }

    private String[] cmds = {"reload", "help", "create", "set", "list", "wand", "enable", "disable", "join"};

    @Override
    public boolean onCommand(CommandSender sendi, Command cmd, String label, String[] args) {
        if (!perm(pl.getPermissions().getArenaAdmin(sendi), sendi))
            return false;
        if (args.length == 0)
            help(sendi, label);
        else {
            if (args[0].equalsIgnoreCase(cmds[0]))
                reload(sendi);
            else if (args[0].equalsIgnoreCase(cmds[1]))
                help(sendi, label);
            else if (args[0].equalsIgnoreCase(cmds[2]))
                create(sendi, label, args);
            else if (args[0].equalsIgnoreCase(cmds[3]))
                set(sendi, label, args);
            else if (args[0].equalsIgnoreCase(cmds[4]))
                list(sendi, label, args);
            else if (args[0].equalsIgnoreCase(cmds[5]))
                wand(sendi, label, args);
            else if (args[0].equalsIgnoreCase(cmds[6]))
                enable(sendi, label, args);
            else if (args[0].equalsIgnoreCase(cmds[7]))
                disable(sendi, label, args);
            else
                invalid(sendi, label);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sendi, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            //ALL
            for (String s : cmds)
                if (s.startsWith(args[0]) && getPermOf(sendi, s))
                    list.add(s);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase(cmds[2]))
                //Create <game>
                list = getListGame(sendi, list, args[1], cmds[2]);
            else if (args[0].equalsIgnoreCase(cmds[6]))
                //Enable <game>
                list = getListGame(sendi, list, args[1], cmds[2]);
            else if (args[0].equalsIgnoreCase(cmds[7]))
                //Disable <game>
                list = getListGame(sendi, list, args[1], cmds[2]);
            else if (args[0].equalsIgnoreCase(cmds[3]))
                //Set <subcmd>
                for (String subcmd : setcmds)
                    if (subcmd.startsWith(args[1].toLowerCase()))
                        list.add(subcmd);
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase(cmds[6]) || args[0].equalsIgnoreCase(cmds[7]))
                //Enable <game> <arena>
                list = getListArena(sendi, list, args[2], cmds[6]);
            else if (args[0].equalsIgnoreCase(cmds[7]))
                //Disable <game> <arena>
                list = getListArena(sendi, list, args[2], cmds[7]);
            else if (args[0].equalsIgnoreCase(cmds[3]))
                //Set <subcmd> <game>
                list = getListGame(sendi, list, args[2], cmds[3]);
        } else if (args.length == 4) {
            //Set <subCmd> <game> <arena>
            if (args[0].equalsIgnoreCase(cmds[3]))
                list = getListArena(sendi, list, args[3], cmds[3]);
        } else if (args.length == 5) {
            //Set <subcmd> <game> <arena> <gamemode,teams>
            if (args[0].equalsIgnoreCase(cmds[3]) && args[1].equalsIgnoreCase(setcmds[1]) && getPermOf(sendi, cmds[3]))
                for (Gamemode gm : Gamemode.values())
                    if (gm.name().toLowerCase().startsWith(args[4].toLowerCase()))
                        list.add(gm.name());
        }
        return list;
    }

    private List<String> getListArena(CommandSender sendi, List<String> list, String arg, String cmd) {
        if (getPermOf(sendi, cmd))
            for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
                for (String arena : exp.getArena().getArenaIDs())
                    if (arena.toLowerCase().startsWith(arg.toLowerCase()))
                        list.add(arena);
        return list;
    }

    private List<String> getListGame(CommandSender sendi, List<String> list, String arg, String cmd) {
        if (getPermOf(sendi, cmd))
            for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
                if (exp.getNameCustom().toLowerCase().startsWith(arg.toLowerCase()))
                    list.add(exp.getNameCustom());
        return list;
    }
    //COMMANDS

    private void create(CommandSender sendi, String label, String[] args) {
        if (isPlayer(sendi))
            if (args.length == 3) {
                Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
                if (exp != null) {
                    if (exp.getArena().createArena(args[2]))
                        pl.getMessages().getArena().getCreateSuccess(sendi, exp.getNameCustom(), args[2], label);
                    else
                        pl.getMessages().getArena().getCreateAlready(sendi, args[2]);
                } else
                    pl.getMessages().getArena().getExistGame(sendi, args[1]);
            } else
                usage(sendi, label, args[0]);
    }

    private void list(CommandSender sendi, String label, String[] args) {
        if (args.length == 1) {
            HashMap<Expansion, Set<String>> gameArenas = new HashMap<>();
            for (Entry<String, Expansion> pl : RonanGamesCore.getRegisteredGames().entrySet()) {
                Set<String> arenas = pl.getValue().getArena().getArenaIDs();
                if (!arenas.isEmpty())
                    gameArenas.put(pl.getValue(), arenas);
            }
            if (!gameArenas.isEmpty())
                pl.getMessages().getArena().getListFound(sendi, gameArenas);
            else
                pl.getMessages().getArena().getListNone(sendi);
        } else
            invalid(sendi, label);
    }

    private void wand(CommandSender sendi, String label, String[] args) {
        if (args.length == 1)
            //if (pl.getWorldEdit() == null) {
                if (isPlayer(sendi)) {
                    Player p = (Player) sendi;
                    if (p.getInventory().contains(pl.getWand().getWandItem()))
                        pl.getMessages().getArena().getWandAlready(sendi);
                    else if (p.getInventory().firstEmpty() != -1) {
                        //Bukkit.getWorld(p.getWorld().getNameCustom()).dropItem(p.getLocation(), pl.getWand().getWandItem());
                        p.getInventory().addItem(pl.getWand().getWandItem());
                        pl.getMessages().getArena().getWandSuccess(sendi);
                    } else
                        pl.getMessages().getArena().getWandFull(sendi);
                }
            //} else
                //invalid(sendi, label);
        else
            invalid(sendi, label);
    }

    private void reload(CommandSender sendi) {
        if (perm(pl.getPermissions().getReload(sendi), sendi))
            pl.reload(sendi);
    }

    private String[] setcmds = {"protection", "gamemode", "teams", "help"};

    private void set(CommandSender sendi, String label, String[] args) {
        if (args.length == 1)
            helpSet(sendi, label);
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase(setcmds[3]))
                helpSet(sendi, label);
            else
                usage(sendi, label, args[0]);
        } else if (args.length >= 4 && args.length <= 5) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[2]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[3]);
                if (arena != null) {
                    if (args[1].equalsIgnoreCase(setcmds[0]) && args.length == 4 && isPlayer(sendi)) {
                        List<Location> loc = pl.getWand().getSelection((Player) sendi, label);
                        if (loc != null) {
                            if (!loc.isEmpty()) {
                                arena.setProtection(loc.get(0), loc.get(1));
                                pl.getMessages().getArena().getSetProtectionSuccess(sendi);
                            }
                        } else
                            pl.getMessages().getArena().getSetProtectionError(sendi, label);
                    } else if (args[1].equalsIgnoreCase(setcmds[1]) && args.length == 5) {
                        if (arena.setGamemode(args[4].toUpperCase()))
                            pl.getMessages().getArena().getSetGamemodeSuccess(sendi, Gamemode.valueOf(args[4]
                                    .toUpperCase()).getDesc());
                        else
                            pl.getMessages().getArena().getSetGamemodeExist(sendi, args[4].toUpperCase());
                    } else if (args[1].equalsIgnoreCase(setcmds[2]) && args.length == 5) {
                        //TO-DO
                        return;
                    } else
                        usage(sendi, label, args[0]);
                } else
                    pl.getMessages().getArena().getExistArena(sendi, args[2], exp.getNameCustom());
            } else
                pl.getMessages().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label, args[0]);
    }

    private void enable(CommandSender sendi, String label, String[] args) {
        if (args.length == 3) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[2]);
                if (arena != null) {
                    if (arena.isEnabled())
                        pl.getMessages().getArena().getEnableAlready(sendi, arena.getNameCustom(), exp.getNameCustom());
                    else if (arena.enable())
                        pl.getMessages().getArena().getEnableSuccess(sendi, arena.getNameCustom(), exp.getNameCustom());
                    else
                        pl.getMessages().getArena().getEnableInvalid(sendi, arena.getNameCustom(), exp.getNameCustom());
                } else
                    pl.getMessages().getArena().getExistArena(sendi, args[1], exp.getNameCustom());
            } else
                pl.getMessages().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label, cmds[6]);
    }

    private void disable(CommandSender sendi, String label, String[] args) {
        if (args.length == 3) {
            Expansion exp = RonanGamesCore.getRegisteredGames().get(args[1]);
            if (exp != null) {
                Arena arena = exp.getArena().getArena(args[2]);
                if (arena != null) {
                    if (!arena.isEnabled())
                        pl.getMessages().getArena().getDisableAlready(sendi, arena.getNameCustom(), exp.getNameCustom());
                    else {
                        arena.disable();
                        pl.getMessages().getArena().getDisableSuccess(sendi, arena.getNameCustom(), exp.getNameCustom());
                    }
                } else
                    pl.getMessages().getArena().getExistArena(sendi, args[1], exp.getNameCustom());
            } else
                pl.getMessages().getArena().getExistGame(sendi, args[1]);
        } else
            usage(sendi, label, cmds[7]);
    }

    private void usage(CommandSender sendi, String label, String str) {
        if (str.equalsIgnoreCase(cmds[2]))
            pl.getUsage().getArenaCreate(sendi, label);
        else if (str.equalsIgnoreCase(cmds[3]))
            pl.getUsage().getArenaSet(sendi, label);
        else if (str.equalsIgnoreCase(cmds[6]))
            pl.getUsage().getArenaEnable(sendi, label);
        else if (str.equalsIgnoreCase(cmds[6]))
            pl.getUsage().getArenaDisable(sendi, label);
        else
            sendi.sendMessage("Something went wrong!");
    }

    private void help(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &fNot all commands are executable from " +
                    "here!"));
        pl.getHelp().getHeader(sendi, label);
        pl.getHelp().getArenaHelp(sendi, label);
        pl.getHelp().getArenaCreate(sendi, label);
        pl.getHelp().getArenaList(sendi, label);
        pl.getHelp().getArenaWand(sendi, label);
        pl.getHelp().getArenaSet(sendi, label);
        if (pl.getPermissions().getReload(sendi))
            pl.getHelp().getReload(sendi, label);
        pl.getHelp().getFooter(sendi, label);
    }

    private void helpSet(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &fNot all commands are executable from " +
                    "here!"));
        pl.getHelp().getHeader(sendi, label);
        pl.getHelp().getSetHelp(sendi, label);
        pl.getHelp().getSetGamemode(sendi, label);
        pl.getHelp().getSetProtection(sendi, label);
        pl.getHelp().getSetTeams(sendi, label);
        pl.getHelp().getFooter(sendi, label);
    }

    private boolean getPermOf(CommandSender sendi, String cmd) {
        if (cmd.equalsIgnoreCase(cmds[0]))
            return true;
        else if (cmd.equalsIgnoreCase(cmds[4]))
            return pl.getPermissions().getReload(sendi);
        else
            return pl.getPermissions().getArenaAdmin(sendi);
    }

    private boolean perm(boolean perm, CommandSender sendi) {
        if (perm)
            return true;
        else
            pl.getMessages().getNoPermission(sendi);
        return false;
    }

    private void invalid(CommandSender sendi, String label) {
        pl.getMessages().getInvalid(sendi, label);
    }

    private boolean isPlayer(CommandSender sendi) {
        if (sendi instanceof Player)
            return true;
        else
            sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &eYou are not able to execute this command " +
                    "from" + " " + "here!"));
        return false;
    }
}*/
