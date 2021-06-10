package me.SuperRonanCraft.RonanGamesAPI.references;

import org.bukkit.command.CommandSender;

public class Permissions {

    private String pre = "ronangames.";

    public boolean getUse(CommandSender sendi) {
        return perm(pre + "use", sendi);
    }

    public boolean getReload(CommandSender sendi) {
        return perm(pre + "reload", sendi);
    }

    public boolean getUpdater(CommandSender sendi) {
        return perm(pre + "updater", sendi);
    }

    public boolean getGamesPlay(CommandSender sendi) {
        return perm(pre + "games.play", sendi);
    }

    public boolean getGamesList(CommandSender sendi) {
        return perm(pre + "games.list", sendi);
    }

    public boolean getArenaAdmin(CommandSender sendi) {
        return perm(pre + "arena.admin", sendi);
    }

    public boolean getBypassProtection(CommandSender sendi) {
        return perm(pre + "bypass.protection", sendi);
    }

    public boolean perm(String permission, CommandSender sendi) {
        return sendi.hasPermission(permission);
    }
}
