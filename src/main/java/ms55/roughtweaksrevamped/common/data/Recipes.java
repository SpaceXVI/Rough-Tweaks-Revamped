package ms55.roughtweaksrevamped.common.data;

import java.util.function.Consumer;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.utils.BooleanCondition;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.registries.ForgeRegistries;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		Item SALVE = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "salve"));
		Item PLASTER = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "plaster"));
		Item BANDAGE = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "bandage"));
		Item MEDKIT = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "medkit"));
		Item ENCHANTED_MEDKIT = ForgeRegistries.ITEMS.getValue(new ResourceLocation(RoughTweaksRevamped.MODID, "medkit_enchanted"));

		//Salve
    	ConditionalRecipe.builder()
        .addCondition(
        	new BooleanCondition(() -> RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT.get() > 0, BooleanCondition.Type.SALVE.get())
        )
        .addRecipe(
        	ShapelessRecipeBuilder.shapelessRecipe(SALVE)
        		.addIngredient(Items.BOWL)
        		.addIngredient(Tags.Items.SEEDS)
        		.addIngredient(Blocks.CACTUS)
        		.addIngredient(Items.POPPY)
        		.addIngredient(Items.DANDELION)
        		.addIngredient(Items.VINE)
                .setGroup("")
                .addCriterion("has_item", hasItem(Items.BOWL))
                ::build
        )
        .build(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "salve"));

    	//Plaster
    	ConditionalRecipe.builder()
        .addCondition(
        	new BooleanCondition(() -> RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT.get() > 0, BooleanCondition.Type.PLASTER.get())
        )
        .addRecipe(
        	ShapedRecipeBuilder.shapedRecipe(PLASTER)
        		.patternLine("SBS")
        		.patternLine("PWP")
        		.patternLine("SBS")
        		.key('S', Tags.Items.STRING)
        		.key('B', Tags.Items.SLIMEBALLS)
        		.key('P', Items.PAPER)
        		.key('W', ItemTags.createOptional(new ResourceLocation("forge", "wools")))
                .setGroup("")
                .addCriterion("has_item", hasItem(Items.PAPER))
                ::build
        )
        .build(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "plaster"));

    	//Bandage
    	ConditionalRecipe.builder()
        .addCondition(
        	new BooleanCondition(() -> RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT.get() > 0, BooleanCondition.Type.BANDAGE.get())
        )
        .addRecipe(
        	ShapedRecipeBuilder.shapedRecipe(BANDAGE)
        		.patternLine("GPG")
        		.patternLine("CWC")
        		.patternLine("GPG")
        		.key('G', Tags.Items.INGOTS_GOLD)
        		.key('P', Items.PAPER)
        		.key('C', Items.CLAY_BALL)
        		.key('W', ItemTags.createOptional(new ResourceLocation("forge", "wools")))
                .setGroup("")
                .addCriterion("has_item", hasItem(Items.PAPER))
                ::build
        )
        .build(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "bandage"));

    	//Medkit
    	ConditionalRecipe.builder()
        .addCondition(
        	new BooleanCondition(() -> RoughConfig.HEAL_COUNT.MEDKIT_HEAL_COUNT.get() > 0, BooleanCondition.Type.MEDKIT.get())
        )
        .addRecipe(
        	ShapedRecipeBuilder.shapedRecipe(MEDKIT)
        		.patternLine("QNQ")
        		.patternLine("SPB")
        		.patternLine("QNQ")
        		.key('Q', Tags.Items.GEMS_QUARTZ)
        		.key('N', Tags.Items.CROPS_NETHER_WART)
        		.key('S', SALVE)
        		.key('P', PLASTER)
        		.key('B', BANDAGE)
                .setGroup("")
                .addCriterion("has_salve", hasItem(SALVE))
                .addCriterion("has_plaster", hasItem(PLASTER))
                .addCriterion("has_bandage", hasItem(BANDAGE))
                ::build
        )
        .build(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "medkit"));

    	//Enchanted Medkit
    	ConditionalRecipe.builder()
        .addCondition(
        	new BooleanCondition(() -> RoughConfig.HEAL_COUNT.MEDKIT_ENCHANTED_HEAL_COUNT.get() > 0, BooleanCondition.Type.MEDKIT_ENCHANTED.get())
        )
        .addRecipe(
        	ShapelessRecipeBuilder.shapelessRecipe(ENCHANTED_MEDKIT)
        		.addIngredient(MEDKIT)
        		.addIngredient(Items.GOLDEN_APPLE)
        		.addIngredient(Items.GHAST_TEAR)
                .setGroup("")
                .addCriterion("has_medkit", hasItem(MEDKIT))
                ::build
        )
        .build(consumer, new ResourceLocation(RoughTweaksRevamped.MODID, "medkit_enchanted"));
    }
}