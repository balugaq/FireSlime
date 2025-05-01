package me.gkfiredev.fireslime.slimefun;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.gkfiredev.fireslime.FireSlimeHeads;
import me.gkfiredev.fireslime.tools.FiniteGauntlet;

public class FireItemStack {

    public static final SlimefunItemStack MAGIC_ROD_I = new SlimefunItemStack("MAGIC_ROD_I", Material.BLAZE_ROD, "&6魔法棒 &7- &eI");
    public static final SlimefunItemStack MAGIC_ROD_II = new SlimefunItemStack("MAGIC_ROD_II", Material.BLAZE_ROD, "&6魔法棒 &7- &eII");

    public static final SlimefunItemStack LUCK_STONE = new SlimefunItemStack("LUCK_STONE", HeadTexture.SAPPHIRE, "&b幸运石头");

    public static final SlimefunItemStack MOB_DEX = new SlimefunItemStack("MOB_DEX", Material.BOOK, "&c生物&f百科", "", "&e右键 &7一个生物", "&7查询生物信息", "", LoreBuilder.powerCharged(0, 15));


    //Finite Stones
    public static final SlimefunItemStack FINITE_GAUNTLET = new SlimefunItemStack("FINITE_GAUNTLET", FireSlimeHeads.GAUNTLET, "&e灭霸手套", "", "&7一种大规模杀伤性武器。 &e右键 &7", "&7清除服务器内一半的玩家", "&7但这是有代价的..", "", "&7剩余次数: &e" + FiniteGauntlet.MAX_USES);
    public static final SlimefunItemStack REALITY_ROCK = new SlimefunItemStack("REALITY_ROCK", FireSlimeHeads.REALITY_ROCK, "&c世界之石", "", "&7一块有能力改变现实的石头");
    public static final SlimefunItemStack SPACE_ROCK = new SlimefunItemStack("SPACE_ROCK", FireSlimeHeads.SPACE_ROCK, "&b空间之石", "", "&7一块能够穿越空间的石头");
    public static final SlimefunItemStack POWER_ROCK = new SlimefunItemStack("POWER_ROCK", FireSlimeHeads.POWER_ROCK, "&d能量之石", "", "&7可给予使用者巨大能量的石头");
    public static final SlimefunItemStack TIME_ROCK = new SlimefunItemStack("TIME_ROCK", FireSlimeHeads.TIME_ROCK, "&a时间之石", "", "&7一块能够改变任何时间线的石头");
    public static final SlimefunItemStack MIND_ROCK = new SlimefunItemStack("MIND_ROCK", FireSlimeHeads.MIND_ROCK, "&e理智之石", "", "&7一块有能力控制任何玩家的石头");
    public static final SlimefunItemStack SOUL_ROCK = new SlimefunItemStack("SOUL_ROCK", FireSlimeHeads.SOUL_ROCK, "&6灵魂之石", "", "&7一块能让玩家起死回生的石头");
    public static final SlimefunItemStack HYLIAN_SHIELD = new SlimefunItemStack("HYLIAN_SHIELD", Hylian_Shield_ItemStack(), "&b海利亚盾", "");
    public static final SlimefunItemStack MASTER_SWORD = new SlimefunItemStack("MASTER_SWORD", Master_Sword_ItemStack(), "&b大师之剑", "");
    public static final SlimefunItemStack DECOMPILER = new SlimefunItemStack("DECOMPILER", Material.CRAFTING_TABLE, "&6原版反向工作台", "", "&7可将物品拆解成合成材料", "&7仅限原版物品");

    //Zelda
    public static ItemStack Hylian_Shield_ItemStack() {
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta meta = shield.getItemMeta();
//		BlockStateMeta meta = (BlockStateMeta) shield.getItemMeta();
//		BlockState state = meta.getBlockState();
//		Banner banner = (Banner) state;
//		banner.setBaseColor(DyeColor.BLUE);
//		List<Pattern> patterns = new ArrayList<>();
//		patterns.add(new Pattern(DyeColor.RED, PatternType.FLOWER));
//		patterns.add(new Pattern(DyeColor.BLUE, PatternType.HALF_HORIZONTAL));
//		patterns.add(new Pattern(DyeColor.YELLOW, PatternType.TRIANGLE_TOP));
//		patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_TOP));
//		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
//		patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLES_TOP));
//		banner.setPatterns(patterns);
//		meta.setBlockState(state);
        meta.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_ATTRIBUTES);
        shield.setItemMeta(meta);
        shield.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        return shield;
    }

    public static ItemStack Master_Sword_ItemStack() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 8);
        sword.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        return sword;
    }
}