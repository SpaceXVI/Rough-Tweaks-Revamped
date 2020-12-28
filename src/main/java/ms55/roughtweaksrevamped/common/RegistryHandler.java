package ms55.roughtweaksrevamped.common;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.item.HealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoughTweaksRevamped.MODID);

	public static RegistryObject<Item> SALVE = RegistryHandler.ITEMS.register(
		"salve", () -> new HealItem("salve", RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.SALVE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT, 
				null, 
				new ItemStack(Items.BOWL)));

	public static RegistryObject<Item> PLASTER = RegistryHandler.ITEMS.register(
		"plaster", () -> new HealItem("plaster", RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT,
				RoughConfig.HEAL_TIME.PLASTER_USE_TIME,
				RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> BANDAGE = RegistryHandler.ITEMS.register(
		"bandage", () -> new HealItem("bandage", RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.BANDAGE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> MEDKIT = RegistryHandler.ITEMS.register(
		"medkit", () -> new HealItem("medkit", RoughConfig.HEAL_COUNT.MEDKIT_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> ENCHANTED_MEDKIT = RegistryHandler.ITEMS.register(
		"medkit_enchanted", () -> new HealItem("medkit_enchanted", RoughConfig.HEAL_COUNT.MEDKIT_ENCHANTED_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_ENCHANTED_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_ENCHANTED_HEAL_AMOUNT,
				Effects.ABSORPTION,
				ItemStack.EMPTY));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}