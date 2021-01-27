package ms55.roughtweaksrevamped;

import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.RegistryHandler;
import ms55.roughtweaksrevamped.common.data.DataGenerators;
import ms55.roughtweaksrevamped.common.events.RoughEvents;
import ms55.roughtweaksrevamped.common.utils.BooleanCondition;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RoughTweaksRevamped.MODID)
public class RoughTweaksRevamped {
    public static final String MODID = "roughtweaks";
	public static final String NAME = "Rough Tweaks";

	public RoughTweaksRevamped() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        ModLoadingContext.get().registerConfig(Type.COMMON, RoughConfig.COMMON_SPEC, "roughtweaks.toml");

        RegistryHandler.register();

        MinecraftForge.EVENT_BUS.register(DataGenerators.class);
        MinecraftForge.EVENT_BUS.register(new RoughEvents());

        //doModelStuff();
    }

	@SubscribeEvent
    public void registerRecipeSerialziers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(BooleanCondition.Serializer.INSTANCE);
    }
	
	/*public void doModelStuff() {
		ItemModelsProperties.func_239418_a_(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "medkit")), new ResourceLocation(MODID, "switch"),
			(stack, world, living) -> { return RoughConfig.GENERAL.OLD_TEXTURES.get() == true ? 1 : 0;}
		);
		ItemModelsProperties.func_239418_a_(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "medkit_enchanted")), new ResourceLocation(MODID, "switch"),
			(stack, world, living) -> { return RoughConfig.GENERAL.OLD_TEXTURES.get() == true ? 1 : 0;}
		);

		System.out.println("old textures? " + RoughConfig.GENERAL.OLD_TEXTURES.get());
	}*/
}