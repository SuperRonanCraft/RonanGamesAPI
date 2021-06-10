package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class LangBasic extends Language {
    private String pre = "Messages.Arena.";

    FileLang getLang() {
        return RonanGamesCorePlugin.getInstance().getYaml().getLang();
    }

    public void getListNone(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "List.None"));
    }

    public void getListFound(CommandSender sendi, String game, Set<String> set) {
        sms(sendi, getLang().getString(pre + "List.Found").replaceAll("%game%", game).replaceAll("%arenas%", set
                .toString()));
    }

    public void getCreateSuccess(CommandSender sendi, String game, String arena) {
        sms(sendi, getLang().getString(pre + "Arena.Success").replaceAll("%game%", game).replaceAll("%arena%",
                arena));
    }

    public void getCreateErrorSelection(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Create.Error.Selection"));
    }

    public void getCreateErrorGamemode(CommandSender sendi, String gamemode) {
        sms(sendi, getLang().getString(pre + "Create.Error.Gamemode").replaceAll("%gamemode%", gamemode));
    }

    public void getCreateErrorTeams(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Create.Error.Teams"));
    }

    //private void sms(CommandSender sendi, String str) {
    //    sms(sendi, str);
    //}
}
