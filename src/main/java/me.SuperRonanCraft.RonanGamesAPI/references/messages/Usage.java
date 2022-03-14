package me.SuperRonanCraft.RonanGamesAPI.references.messages;

import java.util.ArrayList;
import java.util.List;

import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.command.CommandSender;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;

public class Usage {

    private RonanGamesCorePlugin pl;
    private String pre = "Usage.";

    public Usage(RonanGamesCorePlugin pl) {
        this.pl = pl;
    }

    public void getArenaCreate(CommandSender sendi, String label) {
        sms(sendi, format(getLang().getString(pre + "Arena.Create"), label));
    }

    public void getArenaSet(CommandSender sendi, String label) {
        sms(sendi, format(getLang().getString(pre + "Arena.Set"), label));
    }

    public void getArenaEnable(CommandSender sendi, String label) {
        sms(sendi, format(getLang().getString(pre + "Arena.Enable"), label));
    }

    public void getArenaDisable(CommandSender sendi, String label) {
        sms(sendi, format(getLang().getString(pre + "Arena.Disable"), label));
    }

    //General
    private void sms(CommandSender sendi, List<String> list) {
        if (list != null)
            sendi.sendMessage(list.toArray(new String[0]));
    }

    private List<String> format(String msg, String label) {
        List<String> list = new ArrayList<>();
        if (!getHeader().isEmpty())
            list = getHeader();
        list.add(msg);
        if (!getFooter().isEmpty())
            list.addAll(getFooter());
        return color(list, label);
    }

    private List<String> color(List<String> list, String label) {
        if (list != null && !list.isEmpty())
            for (int i = 0; i < list.size(); i++)
                list.set(i, color(list.get(i).replaceAll("%command%", label)));
        return list;
    }

    private String color(String str) {
        return null;
    }

    private List<String> getHeader() {
        return getLang().getList(pre + "Header");
    }

    private List<String> getFooter() {
        return getLang().getList(pre + "Footer");
    }

    FileLang getLang() {
        return pl.getYaml().getLang();
    }
}
