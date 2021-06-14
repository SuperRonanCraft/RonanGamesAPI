package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public interface MessagesMain {

    default void send(CommandSender sendi) {
        send(sendi, null);
    }

    default void send(CommandSender sendi, Object placeholderInfo) {
        Message.sms(sendi, Message.getLang().getString(getPrefix() + getSection()), placeholderInfo);
    }

    default String get(@Nonnull CommandSender p, Object info) {
        return Message.placeholder(p, Message.getLang().getString(getPrefix() + getSection()), info);
    }

    /*default List<String> getList(@Nonnull CommandSender p, Object placeholderInfo) {
        return Message.placeholder(p, Message.getLang().getStringList(getPrefix() + getSection()), placeholderInfo);
    }*/

    String getPrefix();

    String getSection();
}
