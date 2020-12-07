package ms55.roughtweaksrevamped.client;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = RoughTweaksRevamped.MODID, bus = Bus.MOD)
public class RoughConfig {

	public static class General {
		public final IntValue DROP_CHANCE;
		public final DoubleValue SLEEP_HEAL_AMOUNT;
		public final BooleanValue HEALTH_REGEN;

		public General(ForgeConfigSpec.Builder builder) {
			builder.comment("Here you can tweak some additional stuff this mod does")
			       .push("General");

			DROP_CHANCE = builder
					.comment("Chance 1 in X that a hostile entity drops either salve, a plaster or a bandage\\nSet this to 0 if you want to disable entity drops")
					.defineInRange("drop_chance", 30, 0, Short.MAX_VALUE);
			SLEEP_HEAL_AMOUNT = builder
					.comment("Amount of half hearts sleeping heals. Set to 0 to disable this feature")
					.defineInRange("sleep_heal_amount", 2F, 0F, Short.MAX_VALUE);
			HEALTH_REGEN = builder
					.comment("Set to false to prevent this mod from setting the natural health regeneration gamerule to false on entering a world (DOESN'T WORK YET, CHANGE IT MANUALLY VIA GAMERULES)")
					.define("health_regen", true);

	        builder.pop();
		}
	}

	public static class HealCount {
		public final IntValue SALVE_HEAL_COUNT;
		public final IntValue PLASTER_HEAL_COUNT;
		public final IntValue BANDAGE_HEAL_COUNT;
		public final IntValue MEDKIT_HEAL_COUNT;
		public final IntValue MEDKIT_ENCHANTED_HEAL_COUNT;

		public HealCount(ForgeConfigSpec.Builder builder) {
			builder.comment("Defines how often you're able to rightclick the item to heal yourself. Set the use count to 0 to disable the item.")
			       .push("Heal Count");

			SALVE_HEAL_COUNT = builder
					.comment("Use count for the salve item")
					.defineInRange("salve_heal_count", 4, 0, Short.MAX_VALUE);
			PLASTER_HEAL_COUNT = builder
					.comment("Use count for the plaster item")
					.defineInRange("plaster_heal_count", 4, 0, Short.MAX_VALUE);
			BANDAGE_HEAL_COUNT = builder
					.comment("Use count for the bandage item")
					.defineInRange("bandage_heal_count", 6, 0, Short.MAX_VALUE);
			MEDKIT_HEAL_COUNT = builder
					.comment("Use count for the medkit item")
					.defineInRange("medkit_heal_count", 16, 0, Short.MAX_VALUE);
			MEDKIT_ENCHANTED_HEAL_COUNT = builder
					.comment("Use count for the enchanted medkit item")
					.defineInRange("medkit_enchanted_heal_count", 16, 0, Short.MAX_VALUE);

	        builder.pop();
		}
	}

	public static class HealAmount {
		public final DoubleValue SALVE_HEAL_AMOUNT;
		public final DoubleValue PLASTER_HEAL_AMOUNT;
		public final DoubleValue BANDAGE_HEAL_AMOUNT;
		public final DoubleValue MEDKIT_HEAL_AMOUNT;
		public final DoubleValue MEDKIT_ENCHANTED_HEAL_AMOUNT;

		public HealAmount(ForgeConfigSpec.Builder builder) {
			builder.comment("Defines how much health the item restores on rightclick.")
			       .push("Heal Amount");

			SALVE_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("salve_heal_count", 1F, 1F, Short.MAX_VALUE);
			PLASTER_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("plaster_heal_count", 1F, 1F, Short.MAX_VALUE);
			BANDAGE_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("bandage_heal_count", 1F, 1F, Short.MAX_VALUE);
			MEDKIT_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("medkit_heal_count", 2F, 1F, Short.MAX_VALUE);
			MEDKIT_ENCHANTED_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("medkit_enchanted_heal_count", 3F, 1F, Short.MAX_VALUE);

	        builder.pop();
		}
	}

	public static class HealTime {
		public final IntValue SALVE_USE_TIME;
		public final IntValue PLASTER_USE_TIME;
		public final IntValue BANDAGE_USE_TIME;
		public final IntValue MEDKIT_USE_TIME;
		public final IntValue MEDKIT_ENCHANTED_USE_TIME;

		public HealTime(ForgeConfigSpec.Builder builder) {
			builder.comment("Defines how long it takes for a healing item to get used.")
			       .push("Items");

			SALVE_USE_TIME = builder
					.comment("Use time for the salve item")
					.defineInRange("salve_use_time", 5, 0, 72000);
			PLASTER_USE_TIME = builder
					.comment("Use count for the plaster item")
					.defineInRange("plaster_use_time", 10, 0, 72000);
			BANDAGE_USE_TIME = builder
					.comment("Use count for the bandage item")
					.defineInRange(".bandage_use_time", 20, 0, 72000);
			MEDKIT_USE_TIME = builder
					.comment("Use count for the medkit item")
					.defineInRange("medkit_use_time", 40, 0, 72000);
			MEDKIT_ENCHANTED_USE_TIME = builder
					.comment("Use count for the enchanted medkit item")
					.defineInRange("medkit_enchanted_use_time", 40, 0, 72000);

	        builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final General GENERAL;
	public static final HealCount HEAL_COUNT;
	public static final HealAmount HEAL_AMOUNT;
	public static final HealTime HEAL_TIME;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		GENERAL = new General(builder);
		HEAL_COUNT = new HealCount(builder);
		HEAL_AMOUNT = new HealAmount(builder);
		HEAL_TIME = new HealTime(builder);

		COMMON_SPEC = builder.build();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {

	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading event) {

	}
}