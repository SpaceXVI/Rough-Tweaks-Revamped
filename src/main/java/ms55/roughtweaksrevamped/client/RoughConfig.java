package ms55.roughtweaksrevamped.client;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

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
					.defineInRange("dropChance", 30, 0, Short.MAX_VALUE);

			SLEEP_HEAL_AMOUNT = builder
					.comment("Amount of half hearts sleeping heals. Set to 0 to disable this feature")
					.defineInRange("sleepHealAmount", 4F, 0F, Short.MAX_VALUE);

			HEALTH_REGEN = builder
					.comment("Set to true if you want to regenerate health without the use of healing items")
					.define("healthRegen", false);

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
			       .push("HealCount");

			SALVE_HEAL_COUNT = builder
					.comment("Use count for the salve item")
					.defineInRange("salveHealCount", 4, 0, Integer.MAX_VALUE);

			PLASTER_HEAL_COUNT = builder
					.comment("Use count for the plaster item")
					.defineInRange("plasterHealCount", 4, 0, Integer.MAX_VALUE);

			BANDAGE_HEAL_COUNT = builder
					.comment("Use count for the bandage item")
					.defineInRange("bandageHealCount", 6, 0, Integer.MAX_VALUE);

			MEDKIT_HEAL_COUNT = builder
					.comment("Use count for the medkit item")
					.defineInRange("medkitHealCount", 16, 0, Integer.MAX_VALUE);
	
			MEDKIT_ENCHANTED_HEAL_COUNT = builder
					.comment("Use count for the enchanted medkit item")
					.defineInRange("medkitEnchantedHealCount", 16, 0, Integer.MAX_VALUE);

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
			       .push("HealAmount");

			SALVE_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("salveHealAmount", 1F, 1F, Double.MAX_VALUE);

			PLASTER_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("plasterHealAmount", 1F, 1F, Double.MAX_VALUE);

			BANDAGE_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("bandageHealAmount", 1F, 1F, Double.MAX_VALUE);

			MEDKIT_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("medkitHealAmount", 2F, 1F, Double.MAX_VALUE);

			MEDKIT_ENCHANTED_HEAL_AMOUNT = builder
					.comment("Salve heal amount")
					.defineInRange("medkitEnchantedHealAmount", 3F, 1F, Double.MAX_VALUE);

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
			       .push("HealTime");

			SALVE_USE_TIME = builder
					.comment("Use time for the salve item")
					.defineInRange("salveUseTime", 5, 0, 72000);

			PLASTER_USE_TIME = builder
					.comment("Use count for the plaster item")
					.defineInRange("plasterUseTime", 10, 0, 72000);

			BANDAGE_USE_TIME = builder
					.comment("Use count for the bandage item")
					.defineInRange("bandageUseTime", 20, 0, 72000);

			MEDKIT_USE_TIME = builder
					.comment("Use count for the medkit item")
					.defineInRange("medkitUseTime", 40, 0, 72000);

			MEDKIT_ENCHANTED_USE_TIME = builder
					.comment("Use count for the enchanted medkit item")
					.defineInRange("medkitEnchantedUseTime", 40, 0, 72000);

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
}