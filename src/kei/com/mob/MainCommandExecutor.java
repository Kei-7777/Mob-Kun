package kei.com.mob;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainCommandExecutor implements CommandExecutor, TabCompleter {
    Mob plugin;
    public MainCommandExecutor(Mob mob) {
        this.plugin = mob;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "Require OP.");
            return true;
        }

        if(args.length < 2){
            sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " <zombie | skeleton | wskeleton> <数>");
            return true;
        }
        if("skeleton".equalsIgnoreCase(args[0])){
            ItemStack stick = new ItemStack(Mob.material);
            ItemMeta meta = stick.getItemMeta();
            meta.setDisplayName(Mob.prefix_s + args[1]);
            stick.setItemMeta(meta);
            ((Player) sender).getInventory().addItem(stick);
        } else if("zombie".equalsIgnoreCase(args[0])) {
            ItemStack stick = new ItemStack(Mob.material);
            ItemMeta meta = stick.getItemMeta();
            meta.setDisplayName(Mob.prefix_z + args[1]);
            stick.setItemMeta(meta);
            ((Player) sender).getInventory().addItem(stick);
        } else if("wskeleton".equalsIgnoreCase(args[0])){
            ItemStack stick = new ItemStack(Mob.material);
            ItemMeta meta = stick.getItemMeta();
            meta.setDisplayName(Mob.prefix_ws + args[1]);
            stick.setItemMeta(meta);
            ((Player) sender).getInventory().addItem(stick);
        } else {
            sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " <zombie | skeleton | wskeleton> <数>");
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return Arrays.asList("skeleton", "wskeleton", "zombie");
    }
}
