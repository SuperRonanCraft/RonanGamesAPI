package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import java.util.List;

public class LangHelpCore extends Language {

    private String pre = "Help-Core.";

    public List<String> getHeader() {
        return getLang().getStringList(pre + "Header");
    }

    public List<String> getFooter() {
        return getLang().getStringList(pre + "Footer");
    }

    //Core
    public String getCoreHelp() {
        return getLang().getString(pre + "Core.Help");
    }

    public String getCoreList() {
        return getLang().getString(pre + "Core.List");
    }

    public String getCoreExpansion(String game) {
        return getLang().getString(pre + "Core.Expansion").replace("%game%", game);
    }

    //Game - Expansions
    public String getGameHeader() {
        return getLang().getString(pre + "Game.Header");
    }

    public String getGameCore() {
        return getLang().getString(pre + "Game.Core");
    }

    public String getGameFooter() {
        return getLang().getString(pre + "Game.Footer");
    }

    public String getGameAuthor() {
        return getLang().getString(pre + "Game.Author");
    }

    public String getGameVersion() {
        return getLang().getString(pre + "Game.Version");
    }

    //Reload
    public String getReload() {
        return getLang().getString(pre + "Reload");
    }

    //Arena
    public String getArenaHelp() {
        return getLang().getString(pre + "Arena.Help");
    }

    public String getArenaCreate() {
        return getLang().getString(pre + "Arena.Create");
    }

    public String getArenaList() {
        return getLang().getString(pre + "Arena.List");
    }

    public String getArenaWand() {
        return getLang().getString(pre + "Arena.Wand");
    }

    public String getArenaSet() {
        return getLang().getString(pre + "Arena.Set");
    }

    public String getArenaEnable() {
        return getLang().getString(pre + "Arena.Enable");
    }

    public String getArenaDisable() {
        return getLang().getString(pre + "Arena.Disable");
    }

    public String getArenaJoin() {
        return getLang().getString(pre + "Arena.Join");
    }

    //Arena Set
    public String getArenaSetHelp() {
        return getLang().getString(pre + "Set.Help");
    }

    public String getArenaSetProtection() {
        return getLang().getString(pre + "Set.Protection");
    }

    public String getArenaSetGamemode() {
        return getLang().getString(pre + "Set.Gamemode");
    }

    public String getArenaSetTeams() {
        return getLang().getString(pre + "Set.Teams");
    }

}
