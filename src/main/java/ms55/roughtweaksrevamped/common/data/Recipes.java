package ms55.roughtweaksrevamped.common.data;

import java.util.function.Consumer;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

	@Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		Item SALVE = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "salve"));
		Item PLASTER = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "plaster"));
		Item BANDAGE = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "bandage"));
		Item MEDKIT = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "medkit"));
		Item ENCHANTED_MEDKIT = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "medkit_enchanted"));

		//Salve
		ShapelessRecipeBuilder.shapeless(SALVE)
        		.requires(Items.BOWL)
        		.requires(Tags.Items.SEEDS)
        		.requires(Blocks.CACTUS)
        		.requires(Items.POPPY)
        		.requires(Items.DANDELION)
        		.requires(Items.VINE)
				.unlockedBy("has_bowl", has(Items.BOWL))
                .group("")
                .save(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "salve"));

    	//Plaster
		ShapedRecipeBuilder.shaped(PLASTER)
        		.pattern("SBS")
        		.pattern("PWP")
        		.pattern("SBS")
        		.define('S', Tags.Items.STRING)
        		.define('B', Tags.Items.SLIMEBALLS)
        		.define('P', Items.PAPER)
        		.define('W', ItemTags.create(new ResourceLocation("wool")))
				.unlockedBy("has_paper", has(Items.PAPER))
                .group("")
                .save(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "plaster"));

    	//Bandage
		ShapedRecipeBuilder.shaped(BANDAGE)
        		.pattern("GPG")
        		.pattern("CWC")
        		.pattern("GPG")
        		.define('G', Tags.Items.INGOTS_GOLD)
        		.define('P', Items.PAPER)
        		.define('C', Items.CLAY_BALL)
        		.define('W', ItemTags.create(new ResourceLocation("wool")))
                .group("")
				.unlockedBy("has_paper", has(Items.PAPER))
				.save(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "bandage"));

    	//Medkit
		ShapedRecipeBuilder.shaped(MEDKIT)
        		.pattern("QNQ")
        		.pattern("SPB")
        		.pattern("QNQ")
        		.define('Q', Tags.Items.GEMS_QUARTZ)
        		.define('N', Tags.Items.CROPS_NETHER_WART)
        		.define('S', SALVE)
        		.define('P', PLASTER)
        		.define('B', BANDAGE)
                .group("")
                .unlockedBy("has_salve", has(SALVE))
                .unlockedBy("has_plaster", has(PLASTER))
                .unlockedBy("has_bandage", has(BANDAGE))
                .save(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "medkit"));

    	//Enchanted Medkit
    	ShapelessRecipeBuilder.shapeless(ENCHANTED_MEDKIT)
        		.requires(MEDKIT)
        		.requires(Items.GOLDEN_APPLE)
        		.requires(Items.GHAST_TEAR)
                .group("")
                .unlockedBy("has_medkit", has(MEDKIT))
                .save(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "medkit_enchanted"));
    }
}