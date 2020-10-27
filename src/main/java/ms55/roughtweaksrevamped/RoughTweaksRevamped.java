package ms55.roughtweaksrevamped;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.RegistryHandler;
import ms55.roughtweaksrevamped.common.data.DataGenerators;
import ms55.roughtweaksrevamped.common.events.RoughEvents;
import ms55.roughtweaksrevamped.common.utils.BooleanCondition;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RoughTweaksRevamped.MODID)
public class RoughTweaksRevamped {
    public static final String MODID = "roughtweaks";
	public static final String NAME = "Rough Tweaks";

	public RoughTweaksRevamped() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        ModLoadingContext.get().registerConfig(Type.COMMON, RoughConfig.COMMON_SPEC, "roughtweaks.toml");

        RegistryHandler.register();

        MinecraftForge.EVENT_BUS.register(DataGenerators.class);
        MinecraftForge.EVENT_BUS.register(new RoughEvents());
    }

	@SubscribeEvent
    public void registerRecipeSerialziers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(BooleanCondition.Serializer.INSTANCE);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	private void clientSetup(FMLClientSetupEvent event) {
		System.out.print("Hi");
		try {
		   	Method createBoolean = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
		   	createBoolean.setAccessible(true);
		   	DeferredWorkQueue.runLater( () ->
			{
				try {
					Object boolTrue = createBoolean.invoke(GameRules.BooleanValue.class, false);
					GameRules.func_234903_a_("naturalRegeneration", Category.UPDATES, (GameRules.RuleType<GameRules.BooleanValue>) boolTrue);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		} catch (IllegalArgumentException e) {
		   	e.printStackTrace();
		   	throw e;
		}
    }
}