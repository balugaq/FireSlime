package me.gkfiredev.fireslime;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import me.gkfiredev.fireslime.entities.SFVillager;
import me.gkfiredev.fireslime.slimefun.FireCategories;
import me.gkfiredev.fireslime.slimefun.FireRegistry;
import me.gkfiredev.fireslime.tools.FiniteRock;

public class FireSlime extends JavaPlugin implements SlimefunAddon {

    private static Config cfg;

    public static Config getCfg() {
        return cfg;
    }

    public static Plugin getPlugin() {
        return FireSlime.getPlugin(FireSlime.class);
    }

    @Override
    public void onEnable() {
        getLogger().info("FireSlime Active!");
        cfg = new Config(this);
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Setting up Items...");
        FireCategories.FIRESLIME.register(this);
        FireCategories.FIREMACHINES.register(this);
        FireCategories.SDMC.register(this);
        registerFireSlimeItems();
        registerFireMachines();
        registerSDMCItems();
        registerMoreTools();

        getServer().getPluginManager().registerEvents(new SFVillager(), this);
        SFVillager.updateCategoryList();

        for (FiniteRock rock : FiniteRock.rocks) {
            rock.register(this);
        }
    }

    @Override
    public void onDisable() {
        // TODO provide information on reasons to fix
    }


    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/balugaq/FireSlime/issues";
    }

    public void registerFireSlimeItems() {
        FireRegistry.HYLIAN_SHIELD.register(this);
        FireRegistry.MASTER_SWORD.register(this);
        FireRegistry.MOB_DEX.register(this);
        FireRegistry.FINITE_GAUNTLET.register(this);
    }

    public void registerFireMachines() {
        FireRegistry.DECOMPILER.register(this);
    }

    public void registerSDMCItems() {
        FireRegistry.LUCKSTONE.register(this);
        FireRegistry.MAGIC_ROD_I.register(this);
        FireRegistry.MAGIC_ROD_II.register(this);
    }


    private void registerPickaxe(Material type, String component, ItemStack item, List<Pair<Enchantment, Integer>> enchantments) {
        SlimefunItemStack is = new SlimefunItemStack(component + "_PICKAXE_FIRESLIME", type, "&r" + ChatUtils.humanize(component) + "镐");

        for (Pair<Enchantment, Integer> enchantment : enchantments) {
            is.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
        }

        SlimefunItem slimefunItem = new SlimefunItem(FireCategories.FIRESLIME, is, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{item, item, item, null, new ItemStack(Material.STICK), null, null, new ItemStack(Material.STICK), null});
        slimefunItem.register(this);
    }

    private void registerAxe(Material type, String component, ItemStack item, List<Pair<Enchantment, Integer>> enchantments) {
        SlimefunItemStack is = new SlimefunItemStack(component + "_AXE_FIRESLIME", type, "&r" + ChatUtils.humanize(component) + "斧");

        for (Pair<Enchantment, Integer> enchantment : enchantments) {
            is.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
        }

        SlimefunItem slimefunItem = new SlimefunItem(FireCategories.FIRESLIME, is, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{item, item, null, item, new ItemStack(Material.STICK), null, null, new ItemStack(Material.STICK), null});
        slimefunItem.register(this);
    }

    private void registerMoreTools() {
        registerPickaxe(Material.GOLDEN_PICKAXE, "COPPER", SlimefunItems.COPPER_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 2)));
        registerPickaxe(Material.IRON_PICKAXE, "TIN", SlimefunItems.TIN_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 4), new Pair<>(Enchantment.DURABILITY, 1)));
        registerPickaxe(Material.IRON_PICKAXE, "SILVER", SlimefunItems.SILVER_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 4), new Pair<>(Enchantment.DURABILITY, 5)));
        registerPickaxe(Material.IRON_PICKAXE, "ALUMINUM", SlimefunItems.ALUMINUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 7)));
        registerPickaxe(Material.IRON_PICKAXE, "LEAD", SlimefunItems.LEAD_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 2)));
        registerPickaxe(Material.IRON_PICKAXE, "ZINC", SlimefunItems.ZINC_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 4)));
        registerPickaxe(Material.IRON_PICKAXE, "MAGNESIUM", SlimefunItems.MAGNESIUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 5), new Pair<>(Enchantment.DURABILITY, 2)));

        registerPickaxe(Material.IRON_PICKAXE, "STEEL", SlimefunItems.STEEL_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 5), new Pair<>(Enchantment.DURABILITY, 4)));
        registerPickaxe(Material.GOLDEN_PICKAXE, "BRONZE", SlimefunItems.BRONZE_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 3)));
        registerPickaxe(Material.IRON_PICKAXE, "BILLON", SlimefunItems.BILLON_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 4), new Pair<>(Enchantment.DURABILITY, 2)));
        registerPickaxe(Material.GOLDEN_PICKAXE, "BRASS", SlimefunItems.BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 4), new Pair<>(Enchantment.DURABILITY, 3)));
        registerPickaxe(Material.GOLDEN_PICKAXE, "ALUMINUM_BRASS", SlimefunItems.ALUMINUM_BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 5), new Pair<>(Enchantment.DURABILITY, 2)));

        registerAxe(Material.GOLDEN_AXE, "COPPER", SlimefunItems.COPPER_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 2)));
        registerAxe(Material.IRON_AXE, "TIN", SlimefunItems.TIN_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 1)));
        registerAxe(Material.IRON_AXE, "SILVER", SlimefunItems.SILVER_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 5)));
        registerAxe(Material.IRON_AXE, "ALUMINUM", SlimefunItems.ALUMINUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 5)));
        registerAxe(Material.IRON_AXE, "LEAD", SlimefunItems.LEAD_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 2)));
        registerAxe(Material.IRON_AXE, "ZINC", SlimefunItems.ZINC_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 4)));
        registerAxe(Material.IRON_AXE, "MAGNESIUM", SlimefunItems.MAGNESIUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 2)));

        registerAxe(Material.IRON_AXE, "STEEL", SlimefunItems.STEEL_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 4)));
        registerAxe(Material.GOLDEN_AXE, "BRONZE", SlimefunItems.BRONZE_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 1), new Pair<>(Enchantment.DURABILITY, 3)));
        registerAxe(Material.IRON_AXE, "BILLON", SlimefunItems.BILLON_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 2)));
        registerAxe(Material.GOLDEN_AXE, "BRASS", SlimefunItems.BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 2), new Pair<>(Enchantment.DURABILITY, 3)));
        registerAxe(Material.GOLDEN_AXE, "ALUMINUM_BRASS", SlimefunItems.ALUMINUM_BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DIG_SPEED, 3), new Pair<>(Enchantment.DURABILITY, 2)));
    }

}
