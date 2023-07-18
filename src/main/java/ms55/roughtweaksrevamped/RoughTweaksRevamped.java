package ms55.roughtweaksrevamped;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import ms55.roughtweaksrevamped.client.RoughConfig;

import ms55.roughtweaksrevamped.common.events.RoughEvents;
import ms55.roughtweaksrevamped.common.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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

        MinecraftForge.EVENT_BUS.register(new RoughEvents());
        
        modEventBus.addListener(this::addCreative);
    }
	
	
	private void addCreative(CreativeModeTabEvent.BuildContents event) {
		if(event.getTab() == CreativeModeTabs.COMBAT) {
			event.accept(ModItems.BANDAGE, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.accept(ModItems.ENCHANTED_MEDKIT, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.accept(ModItems.MEDKIT, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.accept(ModItems.PLASTER, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.accept(ModItems.SALVE, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


		}
	}

	/*private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModPOIs.registerPOIs();
		});
	}*/
}