package ms55.roughtweaksrevamped.common.block;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RoughTweaksRevamped.MODID);

    public static RegistryObject<Block> MEDIC = registerBlock("medic", Material.IRON);

	public static RegistryObject<Block> registerBlock(String name, Material material) {
		final RegistryObject<Block> BLOCK = BLOCKS.register(name, () -> new Block(Block.Properties.create(material)));

		ModItems.ITEMS.register(name, () -> new BlockItem(BLOCK.get(), new Item.Properties()
			.group(ItemGroup.MISC).maxStackSize(64)));

		return BLOCK;
	}

    public static void register() {
    	BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}