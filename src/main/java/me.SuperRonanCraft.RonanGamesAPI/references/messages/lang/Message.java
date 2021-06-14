package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.RonanGamesGamemode;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Arrays;
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
            if (p instanceof Player && str.contains("%player_uuid%"))
                str = str.replaceAll("%player_uuid%", ((Player) p).getUniqueId().toString());
            //Placeholders based off info
            if (info instanceof String && str.contains("%command%"))
                str = str.replace("%command%", (String) info);
            if (info instanceof Arena)
                str = arena(str, (Arena) info);
            if (info instanceof RonanGamesGamemode)
                str = gamemode(str, (RonanGamesGamemode) info);
            if (info instanceof Expansion)
                str = expansion(str, (Expansion) info);
            if (info instanceof Object[])
                for (Object i : (Object[]) info)
                    str = placeholder(p, str, i);
        }
        if (str != null)
            return color(str);
        return null;
    }

    private static String arena(String str, Arena arena) {
        if (str.contains("%arena%"))
            str = str.replace("%arena%", arena.getName());
        return expansion(str, arena.getExpansion());
    }

    private static String gamemode(String str, RonanGamesGamemode gamemode) {
        if (str.contains("%gamemode%"))
            str = str.replace("%gamemode%", gamemode.name());
        return str;
    }

    private static String expansion(String str, Expansion exp) {
        if (str.contains("%game_name%"))
            str = str.replace("%game_name%", exp.getNameCustom());
        if (str.contains("%game_version%"))
            str = str.replace("%game_version%", exp.getVersion());
        if (str.contains("%game_author%"))
            str = str.replace("%game_author%", exp.getAuthor());
        return str;
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
