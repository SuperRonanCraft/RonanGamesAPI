package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public interface MessagesMain {

    default void send(CommandSender sendi) {
        send(sendi, null);
    }

    default void send(CommandSender sendi, Object placeholderInfo) {
        Message.sms(sendi, Message.getLang().getList(getPrefix() + getSection()), placeholderInfo);
    }

    default String get(@Nonnull CommandSender p, Object info) {
        return Message.placeholder(p, Message.getLang().getString(getPrefix() + getSection()), info);
    }

    String getPrefix();

    String getSection();
}
