package me.SuperRonanCraft.RonanGamesAPI.info.events;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.Message;
import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.MessagesArena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;

import java.util.*;

public class WandSelector implements Listener {

    private final HashMap<Player, Boolean> selection = new HashMap<>();
    private final HashMap<Player, Location> pos1 = new HashMap<>();
    private final HashMap<Player, Location> pos2 = new HashMap<>();
    private ItemStack item;

    public WandSelector(RonanGamesCorePlugin pl) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, pl);
        load();
    }

    public void load() {
        item = mat();
    }

    private ItemStack mat() {
        Material mat = Material.getMaterial(RonanGamesCorePlugin.getInstance().getConfig().getString("Settings.Wand")
                .toUpperCase());
        if (mat == null)
            mat = Material.getMaterial("GOLDEN_AXE");
        if (mat == null)
            mat = Material.getMaterial("LEGACY_GOLD_AXE");
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Message.color("&6&oWand Selector"));
        meta.setLore(Arrays.asList("",
                Message.color("&7Left-Click the first corner and "),
                Message.color("&7right-click the second corner"),
                Message.color("&7to create a selection!")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getWandItem() {
        return item;
    }

    @EventHandler
    @SuppressWarnings("unused")
    private void wandEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getClickedBlock() != null && e.getItem() != null && e.getItem().equals(item))
            if (RonanGamesCorePlugin.getInstance().getPerms().getArenaAdmin(p)) {
                Location loc = e.getClickedBlock().getLocation();
                if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) && (pos1.get(p) == null || !pos1.get(p).equals(loc)
                )) {
                    Message.sms(p, "&aPosition 1 Selected! &fx = " + loc
                            .getBlockX() + ", y = " + loc.getBlockY() + ", z = " + loc.getBlockZ(), null);
                    pos1.put(p, e.getClickedBlock().getLocation());
                } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (pos2.get(p) == null || !pos2.get(p)
                        .equals(loc))) {
                    Message.sms(p, "&aPosition 2 Selected! &fx = " + loc
                            .getBlockX() + ", y = " + loc.getBlockY() + ", z = " + loc.getBlockZ(), null);
                    pos2.put(p, e.getClickedBlock().getLocation());
                }
                if (pos1.get(p) != null && pos2.get(p) != null)
                    selection.put(p, true);
                sendBlockChange(p, e.getClickedBlock().getLocation());
                e.setCancelled(true);
            }
    }

    public List<Location> getSelection(Player p, String label) {
        if (selection.get(p) == null || !selection.get(p))
            return null;
        List<Location> loc = new ArrayList<>();
        loc.add(pos1.get(p));
        loc.add(pos2.get(p));
        if (loc.get(0).getWorld().equals(loc.get(1).getWorld()))
            return loc;
        else
            MessagesArena.SET_PROTECTION_WORLD.send(p);
            //RonanGamesCorePlugin.getInstance().getText().getLang().getArena().getSetProtectionWorld(p, label);
        return new ArrayList<>();
    }

    private void sendBlockChange(Player p, Location blockLoc) {
        if (selection.get(p) == null || !selection.get(p)) { //Incomplete Selection
            Bukkit.getScheduler().scheduleSyncDelayedTask(RonanGamesCorePlugin.getInstance(),
                    () -> p.sendBlockChange(blockLoc, Material.GLOWSTONE.createBlockData()));
            Bukkit.getScheduler().scheduleSyncDelayedTask(RonanGamesCorePlugin.getInstance(),
                    () -> p.sendBlockChange(blockLoc, blockLoc.getWorld().getBlockAt(blockLoc).getBlockData()), 60);
        } else { //Complete selection
            Location pos1 = this.pos1.get(p);
            Location pos2 = this.pos2.get(p);
            Location[] locs = {
                    pos1,
                    pos2,
                    new Location(pos1.getWorld(), pos1.getBlockX(), pos1.getBlockY(), pos2.getBlockZ()),
                    new Location(pos1.getWorld(), pos1.getBlockX(), pos2.getBlockY(), pos2.getBlockZ()),
                    new Location(pos1.getWorld(), pos1.getBlockX(), pos2.getBlockY(), pos1.getBlockZ()),
                    new Location(pos1.getWorld(), pos2.getBlockX(), pos2.getBlockY(), pos1.getBlockZ()),
                    new Location(pos1.getWorld(), pos2.getBlockX(), pos1.getBlockY(), pos1.getBlockZ()),
                    new Location(pos1.getWorld(), pos2.getBlockX(), pos1.getBlockY(), pos2.getBlockZ())
            };
            Bukkit.getScheduler().scheduleSyncDelayedTask(RonanGamesCorePlugin.getInstance(),
                    () -> {
                for (Location loc : locs)
                    p.sendBlockChange(loc, Material.GLOWSTONE.createBlockData());
            });
            Bukkit.getScheduler().scheduleSyncDelayedTask(RonanGamesCorePlugin.getInstance(),
                    () -> {
                for (Location loc : locs)
                    Bukkit.getScheduler().scheduleSyncDelayedTask(RonanGamesCorePlugin.getInstance(),
                            () -> {
                        p.sendBlockChange(loc, loc.getWorld().getBlockAt(loc).getBlockData());
                    }, (new Random()).nextInt(40));
            }, 60);
        }

    }
}
