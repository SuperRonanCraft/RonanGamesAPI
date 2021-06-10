package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class Message {
    static FileLang getLang() {
        return RonanGamesCorePlugin.getInstance().getYaml().getLang();
    }

    public static void sms(CommandSender sendi, String msg, Object placeholderInfo) {
        if (!msg.equals(""))
            sendi.sendMessage(placeholder(sendi, getPrefix() + msg, placeholderInfo));
    }

    public static void sms(CommandSender sendi, List<String> msg, Object placeholderInfo) {
        if (msg != null && !msg.isEmpty()) {
            msg.forEach(str -> msg.set(msg.indexOf(str), placeholder(sendi, str, placeholderInfo)));
            sendi.sendMessage(msg.toArray(new String[0]));
        }
    }

    private static String getPrefix() {
        return getLang().getString("Messages.Prefix");
    }

    /**
    * @param info: Accepts String, PersistentDataContainer
    * **/
    public static List<String> placeholder(CommandSender p, List<String> str, Object info) {
        for (int i = 0; i < str.size(); i++) {
            String s = placeholder(p, str.get(i), info);
            if (s != null)
                str.set(i, s);
            else {
                str.remove(i);
                i--;
            }
        }
        //str.forEach(s -> str.set(str.indexOf(s), placeholder(p, s, info)));
        return str;
    }

    public static String placeholder(@Nullable CommandSender p, String str, Object info) {
        if (str != null) {
            /*if (p != null && RonanGamesCorePlugin.getInstance().papiEnabled())
                try {
                    str = PlaceholderAPI.setPlaceholders((Player) p, str);
                } catch (Exception e) {
                    //Something went wrong with PAPI
                }*/
            if (p != null && str.contains("%player_name%"))
                str = str.replaceAll("%player_name%", p.getName());
            if (str.contains("%player_uuid%"))
                if (p instanceof Player)
                    str = str.replaceAll("%player_uuid%", ((Player) p).getUniqueId().toString());
            //Placeholders based off info
            if (info instanceof String && str.contains("%command%"))
                str = str.replace("%command%", (String) info);
            if (info instanceof Arena)
                str = arena(str, (Arena) info);
            //if (info instanceof AmethystGearInfo)
            //    str = gear(p, str, (AmethystGearInfo) info);
            /*if (info instanceof PersistentDataContainer)
                str = data(p, str, (PersistentDataContainer) info);
            if (info instanceof FriendInfo)
                str = friends(p, str, (FriendInfo) info);
            if (info instanceof FriendRequest)
                str = friends(p, str, (FriendRequest) info);*/
        }
        if (str != null)
            return color(str);
        return null;
    }

    private static String arena(String str, Arena arena) {
        if (str.contains("%arena%"))
            str = str.replace("%arena%", arena.getName());
        return str;
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
