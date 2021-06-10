package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import java.util.List;

import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.command.CommandSender;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;

public class Help {
    private RonanGamesCorePlugin pl;
    private String pre = "Help.";

    public Help(RonanGamesCorePlugin pl) {
        this.pl = pl;
    }

    public void getHeader(CommandSender sendi, String label) {
        sms(sendi, getLang().getStringList(pre + "Header"), label);
    }

    public void getFooter(CommandSender sendi, String label) {
        sms(sendi, getLang().getStringList(pre + "Footer"), label);
    }

    //Arena
    public void getArenaHelp(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Help"), label);
    }

    public void getArenaCreate(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Create"), label);
    }

    public void getArenaList(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.List"), label);
    }

    public void getArenaWand(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Wand"), label);
    }

    public void getArenaSet(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Arena.Set"), label);
    }

    //Core
    public void getCoreHelp(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Core.Help"), label);
    }

    public void getCoreList(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Core.List"), label);
    }

    public void getCoreOther(CommandSender sendi, String label, String game) {
        sms(sendi, replace(getLang().getString(pre + "Core.Other"), "%game%", game), label);
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

    //Arena Set
    public void getSetHelp(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Set.Help"), label);
    }

    public void getSetProtection(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Set.Protection"), label);
    }

    public void getSetGamemode(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Set.Gamemode"), label);
    }

    public void getSetTeams(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Set.Teams"), label);
    }

    //ALL
    public void getReload(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Reload"), label);
    }
    
    private FileLang getLang() {
        return pl.getYaml().getLang();
    }

    //General
    private String replace(String str, String change, String to) {
        return str.replaceAll(change, to);
    }

    private void sms(CommandSender sendi, String msg, String label) {

    }

    private void sms(CommandSender sendi, List<String> msg, String label) {

    }
}
