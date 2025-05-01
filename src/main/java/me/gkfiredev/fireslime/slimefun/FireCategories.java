package me.gkfiredev.fireslime.slimefun;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.gkfiredev.fireslime.FireSlime;

public class FireCategories {

    public static ItemGroup FIRESLIME = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "FireSlime"), new CustomItemStack(Material.BLAZE_POWDER, "&c碳泥&6科技"), 1);
    public static ItemGroup FIREMACHINES = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "FireMachine"), new CustomItemStack(Material.FURNACE, "&c碳泥&7机器"), 1);
    public static ItemGroup SDMC = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "SDMC"), new CustomItemStack(Material.BOOKSHELF, "&6碳泥物品"), 2);

}
