package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public enum MessagesArena {
    LIST_NONE("List.None"),
    LIST_FOUND_HEADER("List.Found.Header"),
    LIST_FOUND_CORE("List.Found.Core"),
    LIST_FOUND_FOOTER("List.Found.Footer"),
    CREATE_SUCCESS("Create.Success"),
    CREATE_ALREADY("Create.Already"),
    SET_PROTECTION_SUCCESS("Set.Protection.Success"),
    SET_PROTECTION_ERROR("Set.Protection.Error"),
    SET_PROTECTION_WORLD("Set.Protection.World"),
    SET_GAMEMODE_EXIST("Set.Gamemode.Exist"),
    SET_GAMEMODE_SUCCESS("Set.Gamemode.Success"),
    WAND_SUCCESS("Wand.Success"),
    WAND_FULL("Wand.Full"),
    WAND_ALREADY("Wand.Already"),
    ENABLE_SUCCESS("Enable.Success"),
    ENABLE_INVALID("Enable.Invalid"),
    ENABLE_ALREADY("Enable.Already"),
    SAVE_SUCCESS("Save.Success"),
    SAVE_INVALID("Save.Invalid"),
    DELETE_SUCCESS("Delete.Success"),
    DELETE_CONFIRM("Delete.Confirm"),
    DISABLE_SUCCESS("Delete.Success"),
    DISABLE_ALREADY("Delete.Already"),
    EXIST_ARENA("Exist.Arena"),
    EXIST_GAME("Exist.Game"),
    ;

    String section;

    MessagesArena(String section) {
        this.section = section;
    }

    private static final String pre = "Arena.";

    public void send(CommandSender sendi) {
        Message.sms(sendi, Message.getLang().getString(pre + section), null);
    }

    public void send(CommandSender sendi, Object placeholderInfo) {
        Message.sms(sendi, Message.getLang().getString(pre + section), placeholderInfo);
    }

    public String get(@Nonnull CommandSender p, Object placeholderInfo) {
        return Message.placeholder(p, Message.getLang().getString(pre + section), placeholderInfo);
    }

    public List<String> getList(@Nonnull CommandSender p, Object placeholderInfo) {
        return Message.placeholder(p, Message.getLang().getStringList(pre + section), placeholderInfo);
    }
}
