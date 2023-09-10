package ms55.roughtweaksrevamped.common.events;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.item.ModItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = RoughTweaksRevamped.MODID, bus = Bus.MOD)
public class RoughEvents {

	@SubscribeEvent
    public void logIn(PlayerLoggedInEvent event) {
		GameRules rules = event.getEntity().getLevel().getGameRules();
        if(!RoughConfig.GENERAL.HEALTH_REGEN.get() && event.getEntity().getLevel().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
        	rules.getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getEntity().getLevel().getServer());
			RoughTweaksRevamped.LOGGER.info("set natural regeneration to false");
        }
    }

	@SubscribeEvent
	public void tickEvent(PlayerTickEvent event) {
		if (event.player.isSleepingLongEnough()) {
			event.player.heal(RoughConfig.GENERAL.SLEEP_HEAL_AMOUNT.get().floatValue());
		}
	}

	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event) {
		if (RoughConfig.GENERAL.DROP_CHANCE.get() > 0 && event.getEntity() instanceof Monster) {
			ItemStack stack = ItemStack.EMPTY;

			switch(event.getEntity().level.random.nextInt(RoughConfig.GENERAL.DROP_CHANCE.get() * getEnabledItems())) {
				case 0: 
					if (RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.SALVE.get()); break;
				case 1: 
					if (RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.PLASTER.get()); break;
				case 2: 
					if (RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.BANDAGE.get()); break;
			}

			if (stack != null)
				event.getDrops().add(new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), stack));
		}
	}

	private int getEnabledItems() {
		int items = 0;
		if (RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT.get() > 0)
			items++;
		if (RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT.get() > 0)
			items++;
		if (RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT.get() > 0)
			items++;

		return items;
	}
}