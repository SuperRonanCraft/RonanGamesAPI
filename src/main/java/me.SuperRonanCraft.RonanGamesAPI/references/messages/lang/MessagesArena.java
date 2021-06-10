package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public enum MessagesArena {
    RELOAD("Reload"),
    NOPERMISSION("NoPermission"),
    INVALID("Invalid"),
    ;

    String section;

    MessagesArena(String section) {
        this.section = section;
    }

    private static final String pre = "Messages.";

    public void send(CommandSender sendi) {
        Message.sms(sendi, Message.getLang().getString(pre + section), null);
    }

    public void send(CommandSender sendi, Object placeholderInfo) {
        Message.sms(sendi, Message.getLang().getString(pre + section), placeholderInfo);
    }

    public String get(@Nonnull CommandSender p, Object placeholderInfo) {
        return Message.placeholder(p, Message.getLang().getString(pre + section), placeholderInfo);
    }
}
