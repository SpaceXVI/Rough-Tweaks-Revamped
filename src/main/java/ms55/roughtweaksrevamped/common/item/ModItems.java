package ms55.roughtweaksrevamped.common.item;

import static ms55.roughtweaksrevamped.RoughTweaksRevamped.MODID;

import ms55.roughtweaksrevamped.client.RoughConfig;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static RegistryObject<Item> SALVE = ITEMS.register(
		"salve", () -> new HealItem(RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.SALVE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT, 
				null, 
				Items.BOWL));

	public static RegistryObject<Item> PLASTER = ITEMS.register(
		"plaster", () -> new HealItem(RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT,
				RoughConfig.HEAL_TIME.PLASTER_USE_TIME,
				RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT,
				null,
				null));

	public static RegistryObject<Item> BANDAGE = ITEMS.register(
		"bandage", () -> new HealItem(RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.BANDAGE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT,
				null,
				null));

	public static RegistryObject<Item> MEDKIT = ITEMS.register(
		"medkit", () -> new HealItem(RoughConfig.HEAL_COUNT.MEDKIT_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_HEAL_AMOUNT,
				null,
				null));

	public static RegistryObject<Item> ENCHANTED_MEDKIT = ITEMS.register(
		"medkit_enchanted", () -> new HealItem(RoughConfig.HEAL_COUNT.MEDKIT_ENCHANTED_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_ENCHANTED_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_ENCHANTED_HEAL_AMOUNT,
				MobEffects.ABSORPTION,
				null));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}