package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class LangArena extends Language {
    private String pre = "Arena.";

    FileLang getLang() {
        return RonanGamesCorePlugin.getInstance().getYaml().getLang();
    }
    
    //List
    public void getListNone(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "List.None"));
    }

    public void getListFound(CommandSender sendi, HashMap<Expansion, Set<String>> arenas) {
        sms(sendi, getLang().getString(pre + "List.Found.Header"));
        for (Expansion exp : arenas.keySet())
            sms(sendi, getLang().getString(pre + "List.Found.Core").replaceAll("%game%", exp.getNameCustom()).replaceAll
                    ("%arenas%", arenas.get(exp).toString()));
        sms(sendi, getLang().getString(pre + "List.Found.Footer"));
    }

    //Create
    public void getCreateSuccess(CommandSender sendi, String game, String arena, String label) {
        sms(sendi, getLang().getString(pre + "Create.Success").replaceAll("%game%", game)
                .replaceAll("%arena%", arena).replaceAll("%command%", label));
    }

    public void getCreateAlready(CommandSender sendi, String arena) {
        sms(sendi, getLang().getString(pre + "Create.Already").replaceAll("%arena%", arena));
    }

    //Set
    public void getSetProtectionSuccess(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Set.Protection.Success"));
    }

    public void getSetProtectionError(CommandSender sendi, String label) {
        sms(sendi, getLang().getString(pre + "Set.Protection.Error").replaceAll("%command%", label));
    }

    public void getSetProtectionWorld(CommandSender sendi, String world) {
        sms(sendi, getLang().getString(pre + "Set.Protection.World").replaceAll("%world%", world));
    }

    public void getSetGamemodeExist(CommandSender sendi, String gm) {
        sms(sendi, getLang().getString(pre + "Set.Gamemode.Exist").replaceAll("%gamemode%", gm));
    }

    public void getSetGamemodeSuccess(CommandSender sendi, String gm) {
        sms(sendi, getLang().getString(pre + "Set.Gamemode.Success").replaceAll("%gamemode%", gm));
    }

    //Wand
    public void getWandSuccess(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Wand.Success"));
    }

    public void getWandFull(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Wand.Full"));
    }

    public void getWandAlready(CommandSender sendi) {
        sms(sendi, getLang().getString(pre + "Wand.Already"));
    }

    //Enable
    public void getEnableSuccess(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Enable.Success").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    public void getEnableInvalid(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Enable.Invalid").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    public void getEnableAlready(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Enable.Already").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    //Save
    public void getSaveSuccess(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Save.Success").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    public void getSaveInvalid(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Save.Invalid").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    //Delete
    public void getDeleteSuccess(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Delete.Success").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    public void getDeleteConfirm(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Delete.Confirm").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    //Disable
    public void getDisableSuccess(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Disable.Success").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    public void getDisableAlready(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Disable.Already").replaceAll("%arena%", arena).replaceAll("%game%",
                game));
    }

    //GENERAL
    public void getExistArena(CommandSender sendi, String arena, String game) {
        sms(sendi, getLang().getString(pre + "Exist.Arena").replaceAll("%arena%", arena).replaceAll("%game%", game));
    }

    public void getExistGame(CommandSender sendi, String game) {
        sms(sendi, getLang().getString(pre + "Exist.Game").replaceAll("%game%", game));
    }
}
