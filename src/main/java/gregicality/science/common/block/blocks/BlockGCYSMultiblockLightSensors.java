package gregicality.science.common.block.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockGCYSMultiblockLightSensors extends VariantBlock<BlockGCYSMultiblockLightSensors.SensorType> {
    public BlockGCYSMultiblockLightSensors() {
        super(Material.IRON);
        setTranslationKey("light_sensor");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 1);
        setDefaultState(getState(SensorType.LIGHT_SENSOR));
    }

    public enum SensorType implements IStringSerializable {
        LIGHT_SENSOR("ulv", 0),
        LV_LIGHT_SENSOR("lv", 1),
        MV_LIGHT_SENSOR("mv", 2),
        HV_LIGHT_SENSOR("hv", 3),
        EV_LIGHT_SENSOR("ev", 4),
        IV_LIGHT_SENSOR("iv", 5),
        LuV_LIGHT_SENSOR("luv", 6),
        ZPM_LIGHT_SENSOR("zpm", 7),
        UV_LIGHT_SENSOR("uv", 8),
        UHV_LIGHT_SENSOR("uhv", 9),
        UEV_LIGHT_SENSOR("uev", 10),
        UIV_LIGHT_SENSOR("uiv", 11),
        UXV_LIGHT_SENSOR("uxv", 12),
        OpV_LIGHT_SENSOR("opv", 13),
        MAX_LIGHT_SENSOR("max", 14);

        public String name;
        public final float tier;

        SensorType(String name, float tier) {
            this.name = name + "_light_sensor";
            this.tier = tier;
        }

        @Override
        public String getName() {
            return name;
        }

        public float getTier() {
            return tier;
        }

        public float getPerTierSpeedAdd() {
            return tier * 2.5f;
        }

        public float getPerTierEnergyAdd() {
            return tier * 2;
        }

        public float getPerTierWaterAdd() {
            return 500 + 500 * tier;
        }
    }
}
