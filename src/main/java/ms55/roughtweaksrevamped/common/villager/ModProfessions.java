package ms55.roughtweaksrevamped.common.villager;

import com.google.common.collect.ImmutableSet;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModProfessions {

	public static DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, RoughTweaksRevamped.MODID);

	public static final RegistryObject<VillagerProfession> MEDIC = PROFESSIONS.register("medic",
			() -> new VillagerProfession("medic", ModPOIs.MEDIC_TYPE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER));

	public static void register() {
		PROFESSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}