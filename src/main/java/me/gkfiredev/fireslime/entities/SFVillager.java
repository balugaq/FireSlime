package me.gkfiredev.fireslime.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent.ChangeReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SeasonalItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.gkfiredev.fireslime.FireSlime;
import net.md_5.bungee.api.ChatColor;

public class SFVillager implements Listener {

    private static final String KEY = "slimefunvillager";

    private static final List<ItemGroup> cheapSelection = new ArrayList<>();
    private static final List<ItemGroup> selection = new ArrayList<>();

    public static ItemGroup getRandomCategory() {
        int option = new Random().nextInt(2) + 1;
        List<ItemGroup> categories;
        if (option == 2) {
            categories = new ArrayList<>(cheapSelection);
        } else {
            categories = new ArrayList<>(selection);
        }
        return categories.get(new Random().nextInt(categories.size()));
    }

    public static void updateCategoryList() {
        cheapSelection.clear();
        selection.clear();
        for (ItemGroup category : Slimefun.getRegistry().getAllItemGroups()) {
            if (category instanceof SeasonalItemGroup) {
                cheapSelection.add(category);
                continue;
            }
            String name = ChatColor.stripColor(category.getUnlocalizedName()).toLowerCase();
            if (name.contains("useful items") || name.contains("food") || name.contains("plant") || name.contains("bush") || name.contains("tree") || name.contains("crop") || name.contains("ingredient") || name.contains("drink") || name.contains("resources") || name.contains("magical items") || name.contains("drinks") || name.contains("gear") || name.contains("storage") || name.contains("talismans") || name.contains("garden") || name.contains("miscellaneous")) {
                cheapSelection.add(category);
            } else if (name.contains("energy") || name.contains("machine") || name.contains("gadget") || name.contains("tool") || name.contains("magic") || name.contains("storage") || name.contains("component") || name.contains("armor") || name.contains("weapon") || name.contains("electric") || name.contains("xpansion") || name.contains("cargo") || name.contains("tech")) {
                selection.add(category);
            }
        }
        Bukkit.getLogger().info("------ Selected Categories [cheap] -------");
        for (ItemGroup category : cheapSelection) {
            Bukkit.getLogger().info(category.getUnlocalizedName());
        }
        Bukkit.getLogger().info("------ Selected Categories [expencive] -------");
        for (ItemGroup category : selection) {
            Bukkit.getLogger().info(category.getUnlocalizedName());
        }
    }

    public static ItemGroup getCategoryFromUnlocalizedName(String unlocalizedName) {
        for (ItemGroup category : cheapSelection) {
            String name = getIDName(category);
            if (name.equals(unlocalizedName)) {
                return category;
            }
        }
        for (ItemGroup category : selection) {
            String name = getIDName(category);
            if (name.equals(unlocalizedName)) {
                return category;
            }
        }
        return null;
    }

    public static String getIDName(ItemGroup category) {
        String name = "";
        name = ChatColor.stripColor(category.getUnlocalizedName());
        name = name.toLowerCase();
        name.replaceAll(" ", "_");

        return name;
    }

    public static boolean isCheapCategory(ItemGroup category) {
        for (ItemGroup cheap : cheapSelection) {
            String cheapID = getIDName(cheap);
            if (cheapID.equals(getIDName(category))) return true;
        }
        return false;
    }

    public static List<SlimefunItem> getUnusedSFItems(ItemGroup category, List<MerchantRecipe> trades) {
        List<SlimefunItem> usableTrades = new ArrayList<>(category.getItems());
        for (MerchantRecipe trade : trades) {
            ItemStack item = trade.getResult();
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem == null) continue;
            usableTrades.remove(sfItem);
        }
        if (usableTrades.isEmpty()) return null;

        return usableTrades;
    }

    @EventHandler
    public void villagerGainProfession(VillagerCareerChangeEvent event) {
        Villager vil = event.getEntity();
        if (event.getReason().equals(ChangeReason.EMPLOYED)) {
            if (event.getProfession().equals(Profession.TOOLSMITH)) {
                int chance = new Random().nextInt(100) + 1;
                if (chance <= FireSlime.getCfg().getInt("options.slimefunVillagerChance")) {
                    ItemGroup category = getRandomCategory();
                    vil.getPersistentDataContainer().set(new NamespacedKey(FireSlime.getPlugin(), KEY), PersistentDataType.STRING, getIDName(category));
                    List<MerchantRecipe> trades = getRandomTrades(vil, getIDName(category), vil.getRecipeCount(), ((vil.getVillagerLevel() * 5) + 1));
                    vil.setRecipes(trades);
                }
            } else if (event.getProfession().equals(Profession.NONE)) {
                if (vil.getPersistentDataContainer().has(new NamespacedKey(FireSlime.getPlugin(), KEY), PersistentDataType.STRING)) {
                    vil.getPersistentDataContainer().remove(new NamespacedKey(FireSlime.getPlugin(), KEY));
                }
            }
        }
    }

    @EventHandler
    public void sfVillagerAcquireTrade(VillagerAcquireTradeEvent ev) {
        Villager vil = (Villager) ev.getEntity();
        if (vil.getPersistentDataContainer().has(new NamespacedKey(FireSlime.getPlugin(), KEY), PersistentDataType.STRING)) {
            String id = vil.getPersistentDataContainer().get(new NamespacedKey(FireSlime.getPlugin(), KEY), PersistentDataType.STRING);

            List<SlimefunItem> sfItems = getUnusedSFItems(getCategoryFromUnlocalizedName(id), vil.getRecipes());
            if (sfItems == null) {
                ItemGroup NCategory;
                do {
                    NCategory = getRandomCategory();
                } while (getIDName(NCategory).equals(id));
                vil.getPersistentDataContainer().set(new NamespacedKey(FireSlime.getPlugin(), KEY), PersistentDataType.STRING, getIDName(NCategory));
            }
            MerchantRecipe trade = getRandomTrade(vil, id, (vil.getVillagerLevel() * 5) + 1);
            if (trade != null) {
                ev.setRecipe(trade);
            }

        }
    }

    private List<MerchantRecipe> getRandomTrades(Villager vil, String id, int size, int xp) {
        List<MerchantRecipe> recipes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var recipe = getRandomTrade(vil, id, xp);
            if (recipe != null) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private MerchantRecipe getRandomTrade(Villager vil, String id, int xp) {
        ItemGroup category = getCategoryFromUnlocalizedName(id);
        List<SlimefunItem> sfItems = getUnusedSFItems(category, vil.getRecipes());
        if (sfItems == null) {
            return null;
        }
        boolean cheap = isCheapCategory(category);
        int maxUses;
        if (cheap) {
            maxUses = new Random().nextInt(48) + 8;
        } else {
            maxUses = new Random().nextInt(8) + 1;
        }

        SlimefunItem sfItem;
        do {
            sfItem = sfItems.get(new Random().nextInt(sfItems.size()));
        } while (sfItem.getRecipeType().equals(RecipeType.MULTIBLOCK));
        ItemStack item = new ItemStack(sfItem.getItem());
        boolean isFood = false;

        if (item.hasItemMeta()) {
            var meta = item.getItemMeta();
            if (meta != null && meta.hasLore()) {
                List<String> lore = meta.getLore();
                if (lore != null) {
                    for (String s : lore) {
                        if (ChatColor.stripColor(s).toLowerCase().contains("饥饿值")) {
                            isFood = true;
                            break;
                        }
                    }
                }
            }
        }
        if (isFood && !sfItem.getItem().getType().equals(Material.POTION)) {
            item.setAmount(new Random().nextInt(5) + 1);
        }

        if (sfItem.getItemName().toLowerCase().contains("粉")) {
            item.setAmount(new Random().nextInt(6) + 1);
        }
        MerchantRecipe trade = new MerchantRecipe(item, maxUses);
        List<ItemStack> payment = new ArrayList<>();
        if (cheap) {
            if (sfItem.getItemName().toLowerCase().contains("粉")) {
                payment.add(new ItemStack(Material.EMERALD));
                trade.setIngredients(payment);
            } else if (isFood) {
                payment.add(new ItemStack(Material.EMERALD, (new Random().nextInt(3) + 1)));
                trade.setIngredients(payment);
            } else {
                payment.add(new ItemStack(Material.EMERALD, (new Random().nextInt(16) + 1)));
                trade.setIngredients(payment);
            }
        } else {
            payment.add(new ItemStack(Material.EMERALD_BLOCK, (new Random().nextInt(42) + 16)));
            trade.setIngredients(payment);
        }
        trade.setExperienceReward(true);
        trade.setVillagerExperience(xp);
        return trade;

    }


}
