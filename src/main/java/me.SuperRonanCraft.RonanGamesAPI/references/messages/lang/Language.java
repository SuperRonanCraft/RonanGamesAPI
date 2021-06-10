package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.files.FileLang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Language {
    //PLACEHOLDERS
    String tId = "%ticket_id%", tPlayer = "%ticket_player%";
    //PREFIX'S
    String preM = "Messages.", preU = "Usage.";

    FileLang getLang() {
        return RonanGamesCorePlugin.getInstance().getYaml().getLang();
    }

    //TOOLS
    public void sms(CommandSender sendi, String str) {
        sendi.sendMessage(colorPre(sendi, str));
    }

    public void sms(CommandSender sendi, List<String> str) {
        str.forEach(s -> str.set(str.indexOf(s), colorPre(sendi, s)));
        sendi.sendMessage(str.toArray(new String[0]));
    }

    private String getPrefix() {
        return getLang().getString(preM + "Prefix");
    }

    private String getError() {
        return getLang().getString(preM + "Error");
    }

    String colorPre(CommandSender sendi, String str) {
        try {
            return color(sendi, str.replaceAll("%prefix%", getPrefix()));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(colorPre(null, "&4WARNING&7: &eLooks like you have not updated " +
                    "your files.yml! &fPlease look at the wiki at &6https://github.com/SuperRonanCraft/AdvancedModreq"));
            String error = color(sendi, getError().replaceAll("%prefix%", getPrefix()));
            if (error == null)
                return null;
            return error;
        }
    }

    private String color(CommandSender sendi, String str) {
        try {
            //if (sendi instanceof Player && RonanGamesCorePlugin.getInstance().getSystems().getDepends().getPapi() != null)
            //    return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders((Player) sendi, str));
            return ChatColor.translateAlternateColorCodes('&', str);
        } catch (NullPointerException e) {
            return str;
        }
    }

    public String getColorPre(String str) {
        return colorPre(null, str);
    }

    public String getColorPre(CommandSender sendi, String str) {
        return colorPre(sendi, str);
    }

    public String getStrip(String str) {
        return ChatColor.stripColor(str);
    }
}
