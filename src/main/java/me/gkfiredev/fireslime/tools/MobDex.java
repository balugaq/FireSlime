package me.gkfiredev.fireslime.tools;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import net.md_5.bungee.api.ChatColor;

public class MobDex extends SlimefunItem implements Rechargeable {

    public static final float COST = 0.3f;
    private static final String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&f生物&c百科&7] &r");


    public MobDex(ItemGroup category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, id, recipeType, recipe);

    }

    public MobDex(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }


    private EntityInteractHandler getEntityInteractionHandler() {
        return (e, item, offhand) -> {
            Player p = e.getPlayer();
            Entity entity = e.getRightClicked();
            e.setCancelled(true);
            if (MobDex.getByItem(item) == null) return;
            MobDex dex = (MobDex) MobDex.getByItem(item);
            if (dex.getItemCharge(item) <= 0.0f) return;
            switch (entity.getType()) {
                case MUSHROOM_COW:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 非常稀有，主要生成于蘑菇岛，可用来获取牛奶和蘑菇煲。(试着让闪电劈中它)");
                    break;
                case BAT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 又吵又无用，它有什么存在的意义吗?");
                    break;
                case BEE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 早出晚归，雨天放假，可通过规模养殖来获取蜂蜜。");
                    break;
                case BLAZE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 没有烈焰棒，神也无法进入末地。");
                    break;
                case CAT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 可爱的哈基米，可用鱼来驯服，它会在你睡醒时带给你礼物。");
                    break;
                case CAVE_SPIDER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 有毒的小蜘蛛。他们很难对付。");
                    break;
                case CHICKEN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 收集鸡蛋制作食物的绝佳资源。");
                    break;
                case COD:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 杀死掉落鳕鱼，也可用水桶将其装入桶里。");
                    break;
                case COW:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 可用来获取皮革和牛奶。");
                    break;
                case CREEPER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": Awww man.");
                    break;
                case DOLPHIN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 会给予你海豚的恩惠，让你在水中游得更快。");
                    break;
                case DONKEY:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 勤劳的小毛驴，可以把箱子放在它的身上。");
                    break;
                case DROWNED:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 其实是一只不会游泳的僵尸，可用来获取铜锭和三叉戟。");
                    break;
                case ELDER_GUARDIAN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 你认为这是boss吗?其实是海底神殿的守护神。");
                    break;
                case ENDERMAN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 小黑塔的最佳打工人，用来获取大量经验和末影珍珠。");
                    break;
                case ENDERMITE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 和蠹虫一样烦，但是能在小黑塔中吸引末影人。");
                    break;
                case ENDER_DRAGON:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 我的世界的最终boss，击败它，然后掠夺它的龙蛋。");
                    break;
                case EVOKER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 杀死之后给予你一个不死图腾，把不死图腾拿在手中可以为你抵挡一次死亡。");
                    break;
                case FOX:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 如果有机会，他们会偷你的东西，但一次只能偷一个。他们嘴里含着钻石的样子真可爱");
                    break;
                case GHAST:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它常常在下界中游荡，会向你吐火球，它们死亡时会掉落恶魂之泪，可用来酿造再生药水。");
                    break;
                case GIANT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 一种超自然生物，不对，你是怎么发现它的!?");
                    break;
                case GUARDIAN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们可以通过眼睛发射激光，也是鱼塔的绝佳打工人。");
                    break;
                case HOGLIN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 一种下界的动物。它们会向你发起冲锋并造成巨大伤害，小心点！");
                    break;
                case HORSE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 非常适合长途旅行，它们不会感到疲劳，可用苹果和药水治疗它。");
                    break;
                case HUSK:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 这些僵尸会在攻击你时给予你饥饿。");
                    break;
                case ILLUSIONER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 极其危险的生物，它会召唤分身并给予你失明效果。幸好mojang没有将其正式添加入游戏中。");
                    break;
                case IRON_GOLEM:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 村庄的守护者，如果你伤害村民，它就会不远万里的追过来将你击飞。");
                    break;
                case LLAMA:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 如果你伤害它们，它们就会朝你吐口水，可以将地毯装备在它们身上。");
                    break;
                case MAGMA_CUBE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 火焰感染的史莱姆。如果不小心靠近它们，它们会对你造成伤害。");
                    break;
                case MULE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 马和驴杂交的后代。且不能与另一头骡子繁殖。");
                    break;
                case OCELOT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 猫的前身。");
                    break;
                case PANDA:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们喜欢吃竹子。而且它们吃东西的样子非常可爱。");
                    break;
                case PARROT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们会模仿不同生物的声音，可能会吓到你。");
                    break;
                case PHANTOM:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 会攻击三天或更长时间未睡觉的玩家。");
                    break;
                case PIG:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 食物。仅此而已。");
                    break;
                case PIGLIN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 来自下界的商人。它们会用金锭进行交易，但交易结果完全取决于它们的心情。");
                    break;
                case PIGLIN_BRUTE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 加强版的猪灵。它们可以被视为一种“迷你Boss”，能够造成巨大伤害且拥有极高的生命值。你需要出色的技巧和装备才能击败它们。");
                    break;
                case PILLAGER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们不惜一切代价击败村民并占领村庄。");
                    break;
                case PLAYER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 能够用双手砍树。通过高级药水酿造抵御任何疾病。但可能会因饥饿而死亡。(也有可能是社畜)");
                    break;
                case POLAR_BEAR:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 生活在雪地生物群系中。如果幼崽受到伤害，它们会奋起反击并保护自己的幼崽。");
                    break;
                case PUFFERFISH:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 靠近它们时，它们会膨胀并使你中毒。但它们也可以用于制造致命陷阱。");
                    break;
                case RABBIT:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 嘘！我在猎捕兔子。");
                    break;
                case RAVAGER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们的来源未知。但你可能不会喜欢它们。");
                    break;
                case SALMON:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 比鳕鱼更美味。");
                    break;
                case SHEEP:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 可用于制作床。床可以击杀恶龙。");
                    break;
                case SHULKER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们会发射子弹，将你击飞到大气层中。");
                    break;
                case SILVERFISH:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们生活在末地传送门所在的要塞中。它们造成的伤害不高，但很难打中。");
                    break;
                case SKELETON:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们手持弓箭，会一直骚扰玩家，直到世界尽头。");
                    break;
                case SKELETON_HORSE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 非常罕见。靠近它时，会生成一波骷髅骑手并试图杀死你。");
                    break;
                case SLIME:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 可爱又有趣。但是它们只能跳跃。");
                    break;
                case SNOWMAN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 想堆雪人吗？");
                    break;
                case SPIDER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们会跳到你身上造成伤害，并会通过爬墙来到达你的位置。");
                    break;
                case SQUID:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它是一只鱿鱼。它的梦想是统治世界，而我们对此无能为力。");
                    break;
                case STRAY:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 更强大的骷髅。它们会射出缓慢箭矢，防止目标逃脱。");
                    break;
                case STRIDER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们可以在岩浆上行走，并且可以骑乘。在下界中旅行非常有用。");
                    break;
                case TRADER_LLAMA:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 流浪商人的伙伴。如果主人受伤，它会保护主人。");
                    break;
                case TROPICAL_FISH:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 生成在海中的漂亮鱼类!");
                    break;
                case TURTLE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 当它们的幼崽长大后，会掉落海龟壳，可以用它来制作海龟头盔。海龟头盔能在有限时间内提供水下呼吸效果。");
                    break;
                case VEX:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 由唤魔者召唤的灵魂。它们可以穿过墙壁，非常令人讨厌。");
                    break;
                case VILLAGER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 十分重要的生物，可以用物品与绿宝石进行交易，反之亦然。然而，运输这些生物非常麻烦。");
                    break;
                case VINDICATOR:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": “杰克来了！”");
                    break;
                case WANDERING_TRADER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 在这片土地上，流浪商人会寻找毫无戒心的玩家，用物品换取绿宝石。但他的交易并不总是那么划算。");
                    break;
                case WITCH:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 一个沉迷于酿造的村民，被排斥为女巫。它变得疯狂，背叛了自己的同类。它会向玩家投掷药水来伤害他们。");
                    break;
                case WITHER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 这是一种如此强大的生物，只有强大的人才能召唤出这个怪物。它会发射凋零头颅，这些头颅会在撞击时爆炸。");
                    break;
                case WITHER_SKELETON:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 收集这些生物的头颅可以用来召唤凋灵，它是我的世界中最强大的Boss。");
                    break;
                case WOLF:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 史蒂夫的最佳伙伴。用骨头驯服它们……");
                    break;
                case ZOGLIN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它曾经是疣猪兽，当疣猪兽在阳光下暴露太久时，就会变成僵尸疣猪兽。");
                    break;
                case ZOMBIE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它曾经是一名玩家，但在被亡灵实体杀死后变成了僵尸。");
                    break;
                case ZOMBIE_HORSE:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 极其罕见，以至于玩家对它的了解少之又少。");
                    break;
                case ZOMBIE_VILLAGER:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 它们曾经是一个普通的村民，但在被僵尸或其他僵尸村民感染后变成了亡灵生物。");
                    break;
                case ZOMBIFIED_PIGLIN:
                    p.sendMessage(prefix + entity.getType().name().toUpperCase() + ": 这种生物原本是猪灵，但它在主世界被阳光烧伤致死，现在复活成了亡灵版本的自己。");
                    break;
                default:
                    p.sendMessage(prefix + "超出百科范畴: 也许高版本的生物并未收录至生物百科中...");
                    break;
            }
            removeItemCharge(item, COST);
        };
    }


    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(getEntityInteractionHandler());

    }


    @Override
    public float getMaxItemCharge(ItemStack item) {
        // TODO Auto-generated method stub
        return 15.0f;
    }

}
