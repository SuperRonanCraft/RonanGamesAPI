package me.SuperRonanCraft.RonanGamesAPI.info.events;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCore;
import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.expansion.Expansion;
import me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

public class ArenaProtection implements Listener {

    public ArenaProtection(RonanGamesCorePlugin pl) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, pl);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void interact(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))
            e.setCancelled(blockPlayer(e.getPlayer(), e.getClickedBlock().getLocation()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void blockPlace(BlockPlaceEvent e) {
        e.setCancelled(blockPlayer(e.getPlayer(), e.getBlockPlaced().getLocation()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void blockExplosionBlock(EntityExplodeEvent e) {
        Object[] results = getInLocation(e.getEntity().getLocation());
        if (results != null)
            e.setCancelled((Boolean) results[1]);
    }

    private boolean blockPlayer(Player player, Location loc) {
        Object[] results = getInLocation(loc);
        if (results != null) {
            if ((Boolean) results[1]) {
                Arena arena = (Arena) results[0];
                if (RonanGamesCorePlugin.getInstance().getPerms().getBypassProtection(player)) //Bypass?
                    return arena.isEnabled(); //Enabled?
                else //Normal player
                    return true;
            } else
                return false;
        } else
            return false;
    }

    private Object[] getInLocation(Location loc) {
        for (Expansion exp : RonanGamesCore.getRegisteredGames().values()) {
            for (String arenaName : exp.getArena().getArenaIDs()) {
                Arena arena = exp.getArena().getArena(arenaName);
                Location loc1 = arena.getPos1();
                Location loc2 = arena.getPos2();
                if (loc1 != null && loc2 != null) {
                    int ax = loc1.getBlockX(), ay = loc1.getBlockY(), az = loc1.getBlockZ();
                    int bx = loc2.getBlockX(), by = loc2.getBlockY(), bz = loc2.getBlockZ();
                    int px = loc.getBlockX(), py = loc.getBlockY(), pz = loc.getBlockZ();
                    if (bx < ax) {
                        int tmp = Math.min(ax, bx);
                        bx = Math.max(ax, bx);
                        ax = tmp;
                    }
                    if (by < ay) {
                        int tmp = Math.min(ay, by);
                        by = Math.max(ay, by);
                        ay = tmp;
                    }
                    if (bz < az) {
                        int tmp = Math.min(az, bz);
                        bz = Math.max(az, bz);
                        az = tmp;
                    }
                    return new Object[]{arena, (px >= ax && px <= bx && py >= ay && py <= by && pz >= az && pz <= bz)};
                }
            }
        }
        return null;
    }
}
