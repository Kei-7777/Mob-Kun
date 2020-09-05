package kei.com.mob;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

import java.util.Collections;
import java.util.List;

public class Mob extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginCommand("mobkun").setExecutor(new MainCommandExecutor(this));
    }


    static Material material = Material.STICK;
    static String prefix_z = "Zombie ";
    static String prefix_ws = "WitherSkeleton ";
    static String prefix_s = "Skeleton ";

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            try {
                if (e.getItem().getType() == Material.AIR) return;
            } catch (NullPointerException ex) {
                return;
            }
            ItemStack item = e.getItem();
            if (item.getType() == material) {
                if (item.getItemMeta().hasDisplayName()) {
                    String s = item.getItemMeta().getDisplayName();
                    if (s.startsWith(prefix_s)) { // Skeleton
                        BlockIterator iter = new BlockIterator(e.getPlayer(), 50);
                        Block hold = null;
                        Block lastBlock = iter.next();
                        while (iter.hasNext()) {
                            lastBlock = iter.next();
                            if (lastBlock.getType() == Material.AIR) {
                                hold = lastBlock;
                                continue;
                            }
                            break;
                        }
                        if (hold == null || lastBlock.getType() == Material.AIR) return;
                        Location loc = hold.getLocation();
                        try {
                            int count = Integer.parseInt(s.replaceFirst(prefix_s, "").substring(0));
                            for (int i = 0; i < count; i++) {
                                Skeleton skeleton = loc.getWorld().spawn(loc, Skeleton.class);
                                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                                List<String> owners = this.getConfig().getStringList("skeleton-skull");
                                Collections.shuffle(owners);
                                meta.setOwningPlayer(Bukkit.getOfflinePlayer(owners.get(0)));
                                skull.setItemMeta(meta);
                                skeleton.getEquipment().setHelmet(skull);
                            }

                        } catch (Exception ex) {
                            return; // threw
                        }
                    } else if (s.startsWith(prefix_ws)) { // Wither Skeleton
                        BlockIterator iter = new BlockIterator(e.getPlayer(), 50);
                        Block hold = null;
                        Block lastBlock = iter.next();
                        while (iter.hasNext()) {
                            lastBlock = iter.next();
                            if (lastBlock.getType() == Material.AIR) {
                                hold = lastBlock;
                                continue;
                            }
                            break;
                        }
                        if (hold == null || lastBlock.getType() == Material.AIR) return;
                        Location loc = hold.getLocation();
                        try {
                            int count = Integer.parseInt(s.replaceFirst(prefix_ws, "").substring(0));
                            for (int i = 0; i < count; i++) {
                                WitherSkeleton witherSkeleton = hold.getWorld().spawn(hold.getLocation(), WitherSkeleton.class);
                                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                                List<String> owners = this.getConfig().getStringList("wskeleton-skull");
                                Collections.shuffle(owners);
                                meta.setOwningPlayer(Bukkit.getOfflinePlayer(owners.get(0)));
                                skull.setItemMeta(meta);
                                witherSkeleton.getEquipment().setHelmet(skull);
                            }
                        } catch (Exception ex) {
                            return; // threw
                        }
                    } else if (s.startsWith(prefix_z)) { // Wither Skeleton
                        BlockIterator iter = new BlockIterator(e.getPlayer(), 50);
                        Block hold = null;
                        Block lastBlock = iter.next();
                        while (iter.hasNext()) {
                            lastBlock = iter.next();
                            if (lastBlock.getType() == Material.AIR) {
                                hold = lastBlock;
                                continue;
                            }
                            break;
                        }
                        if (hold == null || lastBlock.getType() == Material.AIR) return;
                        Location loc = hold.getLocation();
                        try {
                            int count = Integer.parseInt(s.replaceFirst(prefix_z, "").substring(0));
                            for (int i = 0; i < count; i++) {
                                Zombie zombie = hold.getWorld().spawn(hold.getLocation(), Zombie.class);
                                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                                List<String> owners = this.getConfig().getStringList("zombie-skull");
                                Collections.shuffle(owners);
                                meta.setOwningPlayer(Bukkit.getOfflinePlayer(owners.get(0)));
                                skull.setItemMeta(meta);
                                zombie.getEquipment().setHelmet(skull);
                            }
                        } catch (Exception ex) {
                            return; // threw
                        }
                    }
                }
            }
        }
    }
}
