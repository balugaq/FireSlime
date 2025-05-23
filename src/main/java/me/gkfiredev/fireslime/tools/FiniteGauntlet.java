package me.gkfiredev.fireslime.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;

public class FiniteGauntlet extends SlimefunItem {

    public static final int MAX_USES = 2;
    public static final int delay = 3;
    private static final NamespacedKey usageKey = new NamespacedKey(Slimefun.instance(), "finitegauntlet_usage");


    public FiniteGauntlet(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }


    @Override
    public void preRegister() {
        super.preRegister();
        ItemUseHandler ItemUse = this::getItemHandler;
        addItemHandler(ItemUse);

    }


    private ItemUseHandler getItemHandler(PlayerRightClickEvent event) {
        Player p = event.getPlayer();
        ItemStack item = event.getItem();
        event.cancel();
        if (p.getHealth() != p.getHealthScale()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Slimefun.getCfg().getString("options.chat-prefix") + "&c你太虚弱了，无法再使用灭霸手套了！你必须完全恢复健康才能再次使用它！"));
            return null;
        }
        if (Bukkit.getOnlinePlayers().size() == 1) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Slimefun.getCfg().getString("options.chat-prefix") + "&c服务器上没有其他人！你为什么要浪费灭霸手套的能量?"));
            return null;
        }
        if (item.getAmount() > 1) {
            p.sendMessage(ChatColors.color(Slimefun.getCfg().getString("options.chat-prefix") + "&c你无法堆叠使用灭霸手套!"));
            return null;
        }
        useGauntlet(p, item);
        return null;
    }


    private void useGauntlet(Player p, ItemStack item) {
        if (p.getGameMode() != GameMode.CREATIVE) {
            EntityDamageEvent event = new EntityDamageEvent(p, DamageCause.CUSTOM, 1.0);
            Bukkit.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                p.setHealth(event.getDamage());
                p.updateInventory();
            }
        }

        ItemMeta meta = item.getItemMeta();
        int usesLeft = meta.getPersistentDataContainer().getOrDefault(usageKey, PersistentDataType.INTEGER, MAX_USES);
        if (usesLeft == 1) {
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            item.setAmount(0);
        }

        usesLeft--;
        meta.getPersistentDataContainer().set(usageKey, PersistentDataType.INTEGER, usesLeft);

        List<String> lore = meta.getLore();
        lore.set(5, ChatColors.color("&7剩余次数: &e" + usesLeft));
        meta.setLore(lore);
        item.setItemMeta(meta);

        for (Player target : Bukkit.getOnlinePlayers())
            target.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 2, 1);
        Bukkit.broadcastMessage(ChatColors.color(Slimefun.getCfg().getString("options.chat-prefix") + ChatColor.GOLD + p.getName() + ChatColor.LIGHT_PURPLE + " 打了一个响指!"));
        List<Player> DustPlayers = new ArrayList<Player>();
        List<Player> players = new ArrayList<Player>();
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.getGameMode() != GameMode.SPECTATOR) {
                players.add(target);
            }
        }
        int amountToKick = (int) Math.ceil((players.size() / 2.0));
        players.remove(p);
        for (int i = 0; i < amountToKick; i++) {
            int ran = new Random().nextInt(players.size());
            DustPlayers.add(players.get(ran));
            players.get(ran).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (delay + 2) * 20, 1));
            players.remove(ran);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Slimefun.instance(), () -> {
            for (Player target : DustPlayers) {
                target.kickPlayer("你应该瞄准头部。");
            }

        }, delay * 20);
    }


}
