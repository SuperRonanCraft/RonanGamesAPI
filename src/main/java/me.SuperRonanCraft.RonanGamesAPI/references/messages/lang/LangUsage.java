package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

import java.util.List;

public class LangUsage extends Language {

    private String pre = "Usage.";

    public void getArenaCreate(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Create").replace("%command%", label));
    }

    public void getArenaSet(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Set").replace("%command%", label));
    }

    public void getArenaEnable(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Enable").replace("%command%", label));
    }

    public void getArenaDisable(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Disable").replace("%command%", label));
    }

    private List<String> getHeader() {
        return getLang().getStringList(pre + "Header");
    }

    private List<String> getFooter() {
        return getLang().getStringList(pre + "Footer");
    }
}
