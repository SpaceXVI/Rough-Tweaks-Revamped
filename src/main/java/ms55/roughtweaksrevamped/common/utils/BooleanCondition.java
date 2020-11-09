package ms55.roughtweaksrevamped.common.utils;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public final class BooleanCondition implements ICondition {
	private static final ResourceLocation NAME = new ResourceLocation(RoughTweaksRevamped.MODID, "isconfigenabled");

    private BooleanSupplier bool;
    private String type;

    public BooleanCondition(BooleanSupplier bool, String type) {
    	this.bool = bool;
    	this.type = type;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        return bool.getAsBoolean() == true;
    }

    @Override
    public String toString() {
        return ((Boolean) bool.getAsBoolean()).toString();
    }

    public static enum Type {
    	SALVE("enable_salve"),
    	PLASTER("enable_plaster"),
    	BANDAGE("enable_bandage"),
    	MEDKIT("enable_medkit"),
    	MEDKIT_ENCHANTED("enable_medkit_enchanted");

    	String name;

    	Type(String name) {
    		this.name = name;
    	}

    	public String get() {
    		return this.name;
    	}
    }

    public static class Serializer implements IConditionSerializer<BooleanCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, BooleanCondition value) {
            json.addProperty("type", value.bool.getAsBoolean());
            json.addProperty("config_setting", value.type);
        }

        @Override
        public BooleanCondition read(JsonObject json)  {
        	String configSetting = JSONUtils.getString(json, "config_setting");

            switch (configSetting) {
                case "enable_salve":
                    return new BooleanCondition(() -> RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT.get() > 0, configSetting);
                case "enable_plaster":
                    return new BooleanCondition(() -> RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT.get() > 0, configSetting);
                case "enable_bandage":
                    return new BooleanCondition(() -> RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT.get() > 0, configSetting);
                case "enable_medkit":
                    return new BooleanCondition(() -> RoughConfig.HEAL_COUNT.MEDKIT_HEAL_COUNT.get() > 0, configSetting);
                case "enable_medkit_enchanted":
                    return new BooleanCondition(() -> RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT.get() > 0, configSetting);
                default:
                    throw new RuntimeException("Invalid config setting: " + configSetting);
            }
        }

        @Override
        public ResourceLocation getID() {
            return BooleanCondition.NAME;
        }
    }
}