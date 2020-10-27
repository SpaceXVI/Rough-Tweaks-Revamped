package ms55.roughtweaksrevamped.common.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HealItem extends Item {
	private final int healRate;
	private final float healAmount;
	private final Effect effect;
	private final ItemStack returnStack;

	public HealItem(String name, int useCount, int healRate, float healAmount, Effect effect, ItemStack returnStack) {
		super(new Item.Properties()
				.group(ItemGroup.MISC)
                .maxStackSize(1)
                .maxDamage(useCount));

		this.healRate = healRate;
		this.healAmount = healAmount;
		this.effect = effect;
		this.returnStack = returnStack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (player.abilities.isCreativeMode) {
			return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
		}

		player.setActiveHand(hand);
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}

	@Override
    public int getDamage(ItemStack stack) {
        return !stack.hasTag() ? getMaxDamage(stack) : stack.getOrCreateTag().getInt("Damage");
    }

	@Override
	public int getUseDuration(ItemStack stack) {
        return 72000;
    }

	@Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

	@Override
	public boolean hasEffect(ItemStack stack) {
		return effect != null;
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (count % healRate == 1) {
			stack.damageItem(1, player, x -> {
				x.setHeldItem(player.getActiveHand(), returnStack == null ? ItemStack.EMPTY : returnStack);
				x.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0F, 0.5F);
				x.stopActiveHand();
			});

			player.heal(healAmount);
			player.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0F, 1.5F);

			if (effect != null) {
				player.addPotionEffect(new EffectInstance(effect, 1200));
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			float hearts = healAmount / 2;
			if (hearts % 1.0 == 0)
				tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Heal Amount: " + (int) hearts + " Hearts"));
			else
				tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Heal Amount: " + hearts + " Hearts"));
		}
	}
}