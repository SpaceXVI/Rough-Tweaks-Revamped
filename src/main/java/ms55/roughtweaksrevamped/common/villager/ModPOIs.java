package ms55.roughtweaksrevamped.common.villager;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPOIs {

	public static DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, RoughTweaksRevamped.MODID);

    public static Set<BlockState> getAllStates(Block block) {
        return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
    }

    public static final RegistryObject<PointOfInterestType> MEDIC_TYPE = POI_TYPES.register("medic_type",
    		() -> new PointOfInterestType("medic_type", getAllStates(ModBlocks.MEDIC.get()), 1, 1));

    public static void register() {
    	POI_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}