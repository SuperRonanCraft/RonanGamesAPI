package me.SuperRonanCraft.RonanGamesAPI.info.players.commands.OLD;

public class CMDExpansion {
}/*    private Expansion pl;

    public CMDExpansion(Expansion pl) {
        this.pl = pl;
        RonanGamesCorePlugin.getInstance().getGamesCmd().addCmd(pl.getNameCustom());
    }

    private String[] cmds = {"version", "author"};

    void onCommand(CommandSender sendi, String label, String[] args) {
        List<String> arg = removeFirst(args);
        if (arg.size() >= 1)
            if (arg.get(0).equalsIgnoreCase(cmds[0]))
                sms(sendi, getGameMsg().getVersion().replaceAll("%game%", pl.getNameCustom()).replaceAll("%version%", pl.getVersion()).replaceAll("%command%", label));
            else if (arg.get(0).equalsIgnoreCase(cmds[1]))
                sms(sendi, getGameMsg().getAuthor().replaceAll("%game%", pl.getNameCustom()).replaceAll("%author%", pl.getAuthor()).replaceAll("%command%", label));
            else
                ((Commands) pl).onCommand(sendi, label, arg);
        else
            help(sendi, label, args[0]);
    }

    private void help(CommandSender sendi, String label, String game) {
        List<String> list = new ArrayList<>();
        list.add(getHelpMsg().getGameHeader());
        list.add(getHelpMsg().getGameAuthor().replaceAll("%command%", label).replaceAll("%game%", game));
        list.add(getHelpMsg().getGameVersion().replaceAll("%command%", label).replaceAll("%game%", game));
        //for (String cmd : cmds)
        //    list.add(getHelpMsg().getGameCore().replaceAll("%command%", cmd).replaceAll("%game%", label));
        list.add(getHelpMsg().getGameFooter());
        list.forEach(str ->
                list.set(list.indexOf(str), str.replaceAll("%game%", pl.getNameCustom()))
            );
        sms(sendi, list);
    }

    List<String> onTabComplete(CommandSender sendi, String label, String[] args) {
        List<String> list = new ArrayList<>(), arg = removeFirst(args);
        if (arg.size() == 1) {
            for (String cmd : cmds)
                if (cmd.startsWith(arg.get(0).toLowerCase()))
                    list.add(cmd);
            try {
                list.addAll(((Commands) pl).onTabComplete(sendi, label, arg));
            } catch (NullPointerException e) {
                //
            }
        }
        return list;
    }

    private List<String> removeFirst(String[] args) {
        List<String> arg = new LinkedList<>(Arrays.asList(args));
        arg.remove(0);
        return arg;
    }

    private void sms(CommandSender sendi, String str) {
        RonanGamesCorePlugin.getInstance().getMessages().sms(sendi, str);
    }

    private void sms(CommandSender sendi, List<String> str) {
        RonanGamesCorePlugin.getInstance().getMessages().sms(sendi, str);
    }

    private Help getHelpMsg() {
        return RonanGamesCorePlugin.getInstance().getHelp();
    }

    private LangGames getGameMsg() {
        return RonanGamesCorePlugin.getInstance().getMessages().getGame();
    }
}*/
