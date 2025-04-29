package me.gkfiredev.fireslime.slimefun;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.gkfiredev.fireslime.FireSlime;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class FireCategories {

    public static ItemGroup FIRESLIME = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "FireSlime"), new CustomItemStack(Material.BLAZE_POWDER, "&cFire&6Slime"), 1);
    public static ItemGroup FIREMACHINES = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "FireMachine"), new CustomItemStack(Material.FURNACE, "&cFire&7Machines"), 1);
    public static ItemGroup SDMC = new ItemGroup(new NamespacedKey(FireSlime.getPlugin(FireSlime.class), "SDMC"), new CustomItemStack(Material.BOOKSHELF, "&6SDMC"), 2);

}
