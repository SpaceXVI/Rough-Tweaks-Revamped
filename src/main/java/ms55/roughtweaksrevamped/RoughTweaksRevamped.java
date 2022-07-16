package ms55.roughtweaksrevamped;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.data.DataGenerators;
import ms55.roughtweaksrevamped.common.events.RoughEvents;
import ms55.roughtweaksrevamped.common.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RoughTweaksRevamped.MODID)
public class RoughTweaksRevamped {
	public static final String NAME = "Rough Tweaks";
    public static final String MODID = "roughtweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

	public RoughTweaksRevamped() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.register(this);

        ModLoadingContext.get().registerConfig(Type.COMMON, RoughConfig.COMMON_SPEC, "roughtweaks.toml");

        ModItems.ITEMS.register(modEventBus);
        //ModBlocks.BLOCKS.register(modEventBus);
        //ModPOIs.POI_TYPES.register(modEventBus);
        //ModProfessions.PROFESSIONS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(DataGenerators.class);
        MinecraftForge.EVENT_BUS.register(new RoughEvents());
    }

	/*private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModPOIs.registerPOIs();
		});
	}*/
}