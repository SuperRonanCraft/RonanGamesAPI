package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.OLD;

public class CMDGames{}/* implements CommandExecutor, TabCompleter {
    private RonanGamesCorePlugin pl;

    public CMDGames(RonanGamesCorePlugin pl) {
        this.pl = pl;
    }

    private List<String> cmds = new LinkedList<>(Arrays.asList("help", "list", "reload", "leave"));
    private List<String> subcmds = new LinkedList<>(Arrays.asList("author", "version", "join"));

    public boolean onCommand(CommandSender sendi, Command cmd, String label, String[] args) {
        Permissions perms = pl.getPermissions();
        if (!perm(perms.getUse(sendi), sendi))
            return false;
        if (args.length == 0)
            help(sendi, label);
        else {
            if (args[0].equalsIgnoreCase(cmds.get(0)))
                help(sendi, label);
            else if (args[0].equalsIgnoreCase(cmds.get(1)))
                list(sendi);
            else if (args[0].equalsIgnoreCase(cmds.get(2)))
                reload(sendi);
            else {
                if (cmds.contains(args[0].toLowerCase()))
                    for (Expansion ex : RonanGamesCore.getRegisteredGames().values())
                        if (ex.getNameCustom().equalsIgnoreCase(args[0])) {
                            RonanGamesCore.getGamesCmd(ex).onCommand(sendi, label, args);
                            return false;
                        }
                invalid(sendi, label);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sendi, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (String s : cmds)
                if (s.startsWith(args[0].toLowerCase()) && getPermOf(sendi, s))
                    list.add(s);
        } else if (args.length > 1)
            if (cmds.contains(args[0].toLowerCase()))
                for (Expansion ex : RonanGamesCore.getRegisteredGames().values())
                    if (ex.getNameCustom().equalsIgnoreCase(args[0])) {
                        List<String> games = RonanGamesCore.getGamesCmd(ex).onTabComplete(sendi, label, args);
                        if (games != null)
                            list.addAll(games);
                    }
        return list;
    }

    void addCmd(String cmd) {
        cmds.add(cmd.toLowerCase());
    }

    //COMMANDS
    private void list(CommandSender sendi) {
        if (perm(pl.getPermissions().getGamesList(sendi), sendi)) {
            List<String> gameNames = new ArrayList<>();
            for (Entry<String, Expansion> pl : RonanGamesCore.getRegisteredGames().entrySet())
                gameNames.add(pl.getValue().getNameCustom());
            if (!gameNames.isEmpty())
                pl.getMessages().getGame().getListFound(sendi, gameNames);
            else
                pl.getMessages().getGame().getListNone(sendi);
        }
    }

    private void reload(CommandSender sendi) {
        if (perm(pl.getPermissions().getReload(sendi), sendi))
            pl.reload(sendi);
    }

    private void help(CommandSender sendi, String label) {
        if (!(sendi instanceof Player))
            sendi.sendMessage(pl.getMessages().colorPre("&cWARNING&7: &fNot all commands are executable from here!"));
        pl.getHelp().getHeader(sendi, label);
        pl.getHelp().getCoreHelp(sendi, label);
        if (pl.getPermissions().getGamesList(sendi))
            pl.getHelp().getCoreList(sendi, label);
        if (pl.getPermissions().getGamesPlay(sendi))
            for (Expansion exp : RonanGamesCore.getRegisteredGames().values())
                if (RonanGamesCore.getGamesCmd(exp) != null)
                    pl.getHelp().getCoreExpansion(sendi, label, exp.getNameCustom());
        if (pl.getPermissions().getReload(sendi))
            pl.getHelp().getReload(sendi, label);
        pl.getHelp().getFooter(sendi, label);
    }

    private boolean perm(boolean perm, CommandSender sendi) {
        if (perm)
            return true;
        else
            pl.getMessages().getNoPermission(sendi);
        return false;
    }

    private boolean getPermOf(CommandSender sendi, String cmd) {
        if (cmd.equalsIgnoreCase(cmds.get(0)))
            return true;
        else if (cmd.equalsIgnoreCase(cmds.get(1)))
            return pl.getPermissions().getGamesList(sendi);
        else if (cmd.equalsIgnoreCase(cmds.get(2)))
            return pl.getPermissions().getReload(sendi);
        else {
            for (int i = 3; i < cmds.size(); i++)
                for (Expansion ex : RonanGamesCore.getRegisteredGames().values())
                    if (cmd.equalsIgnoreCase(ex.getNameCustom()))
                        return pl.getPermissions().getGamesPlay(sendi);
        }
        return false;
    }

    private void invalid(CommandSender sendi, String label) {
        pl.getMessages().getInvalid(sendi, label);
    }
}*/
