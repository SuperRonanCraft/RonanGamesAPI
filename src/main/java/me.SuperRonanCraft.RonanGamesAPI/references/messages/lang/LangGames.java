package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class LangGames extends Language {
    private String pre = "Game.";

    FileLang getLang() {
        return RonanGamesCorePlugin.getInstance().getYaml().getLang();
    }

    //List
    public void getListNone(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "List.None"));
    }

    public void getListFound(CommandSender sendi, List<String> games) {
        sms(sendi, getLang().getString(pre + "List.Found").replaceAll("%games%", games.toString()));
    }

    public String getVersion() {
        return getLang().getString(pre + "Version");
    }

    public String getAuthor() {
        return getLang().getString(pre + "Author");
    }
}
