package ms55.roughtweaksrevamped.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealItem extends Item {
	private final IntValue HEAL_RATE;
	private final IntValue MAX_DAMAGE;
	private final DoubleValue HEAL_AMOUNT;
	private final MobEffect EFFECT;
	private final ItemStack RETURN_STACK;

	public HealItem(IntValue useCount, IntValue healRate, DoubleValue healAmount, MobEffect effect, ItemStack returnStack) {
		super((new Item.Properties())
                .stacksTo(1)
				.durability(10));

		this.HEAL_RATE = healRate;
		this.HEAL_AMOUNT = healAmount;
		this.MAX_DAMAGE = useCount;
		this.EFFECT = effect;
		this.RETURN_STACK = returnStack;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player.isCreative()) {
			return InteractionResultHolder.pass(stack);
		}

		player.startUsingItem(hand);
		return InteractionResultHolder.success(stack);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return MAX_DAMAGE.get();
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return EFFECT != null;
	}

	@Override
	public void onUseTick(Level world, LivingEntity player, ItemStack stack, int count) {
		if (count % HEAL_RATE.get() == 1) {
			stack.hurtAndBreak(1, player, x -> {
				x.playSound(SoundEvents.WOOL_PLACE, 1.0F, 0.5F);
				x.broadcastBreakEvent(x.getUsedItemHand());
				ItemHandlerHelper.giveItemToPlayer((Player) x, RETURN_STACK);
			});

			player.heal(HEAL_AMOUNT.get().floatValue());
			player.playSound(SoundEvents.WOOL_PLACE, 1.0F, 1.5F);

			if (EFFECT != null) {
				player.addEffect(new MobEffectInstance(EFFECT, 1200));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		float hearts = HEAL_AMOUNT.get().floatValue() / 2;
		if (hearts % 1.0 == 0) {
			tooltip.add(Component.literal("Heal Amount: " + (int) hearts + " Hearts").withStyle(ChatFormatting.BLUE));
		} else {
			tooltip.add(Component.literal("Heal Amount: " + hearts + " Hearts").withStyle(ChatFormatting.BLUE));
		}
	}
}