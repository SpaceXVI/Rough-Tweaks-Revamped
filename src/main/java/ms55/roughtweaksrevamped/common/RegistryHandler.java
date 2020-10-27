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

	public static final RegistryObject<Item> SALVE = RegistryHandler.ITEMS.register(
		"salve", () -> new HealItem("salve", RoughConfig.ITEMS.SALVE_HEAL_COUNT.get(), 5, RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT.get().floatValue(), null, new ItemStack(Items.BOWL)));
	public static final RegistryObject<Item> PLASTER = RegistryHandler.ITEMS.register(
		"plaster", () -> new HealItem("plaster", RoughConfig.ITEMS.PLASTER_HEAL_COUNT.get(), 10, RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT.get().floatValue(), null, null));
	public static final RegistryObject<Item> BANDAGE = RegistryHandler.ITEMS.register(
		"bandage", () -> new HealItem("bandage", RoughConfig.ITEMS.BANDAGE_HEAL_COUNT.get(), 20, RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT.get().floatValue(), null, null));
	public static final RegistryObject<Item> MEDKIT = RegistryHandler.ITEMS.register(
		"medkit", () -> new HealItem("medkit", RoughConfig.ITEMS.MEDKIT_HEAL_COUNT.get(), 40, RoughConfig.HEAL_AMOUNT.MEDKIT_HEAL_AMOUNT.get().floatValue(), null, null));
	public static final RegistryObject<Item> ENCHANTED_MEDKIT = RegistryHandler.ITEMS.register(
		"medkit_enchanted", () -> new HealItem("medkit_enchanted", RoughConfig.ITEMS.MEDKIT_ENCHANTED_HEAL_COUNT.get(), 40, RoughConfig.HEAL_AMOUNT.MEDKIT_ENCHANTED_HEAL_AMOUNT.get().floatValue(), Effects.ABSORPTION, null));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}