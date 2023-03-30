package gregicality.science.common.metatileentities.multiblock;

import gregicality.science.api.recipes.GCYSRecipeMaps;
import gregicality.science.common.block.GCYSMetaBlocks;
import gregicality.science.common.block.blocks.BlockGCYSMultiblockCasing;
import gregicality.science.common.block.blocks.BlockGCYSMultiblockLightSensors;
import gregtech.api.capability.IWorkable;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregicality.science.common.block.blocks.BlockGCYSMultiblockLightSensors.SensorType.*;

public class MetaTileEntityGreenhouse extends RecipeMapMultiblockController implements ITieredMetaTileEntity {
    private final int tier;
    private ResourceLocation metaTileEntityId;
    private BlockGCYSMultiblockLightSensors.SensorType type;
    public MetaTileEntityGreenhouse(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, GCYSRecipeMaps.GREENHOUSE_RECIPES);
        this.tier = tier;
        this.metaTileEntityId = metaTileEntityId;
    }

    @Override
    protected void updateFormedValid() {

    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCC ", " GGG ", " GGG ", " GGG ", " GGG ", " GGG ", " CCC ")
                .aisle("CCCCC", "GDDDG", "G###G", "G###G", "G###G", "G###G", "CFFFC")
                .aisle("CCCCC", "GDDDG", "G###G", "G###G", "G###G", "G###G", "CFFFC")
                .aisle("CCCCC", "GDDDG", "G###G", "G###G", "G###G", "G###G", "CFFFC")
                .aisle(" CSC ", " GGG ", " GGG ", " GGG ", " GGG ", " GGG ", " CCC ")
                .where('C', states(this.getCasingState())
                        .or(autoAbilities(true, false))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS).setMinGlobalLimited(1).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setMinGlobalLimited(1).setMaxGlobalLimited(5))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(2).setMaxGlobalLimited(2))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMinGlobalLimited(2).setMaxGlobalLimited(2)))
                .where('G', blocks(Blocks.GLASS))
                .where('D', blocks(Blocks.GRASS)
                        .or(blocks(Blocks.DIRT)))
                .where('#', air())
                .where('F', new TraceabilityPredicate(blockWorldState -> {

                }))
                .where('S', this.selfPredicate())
                .build();
    }

    @Nonnull
    private IBlockState getCasingState() {
        return GCYSMetaBlocks.MULTIBLOCK_CASING.getState(BlockGCYSMultiblockCasing.CasingType.GREENHOUSE_CASING);
    }

    private TraceabilityPredicate lightSensorPredicate() {
        return new TraceabilityPredicate(blockWorldState -> {
            if (blockWorldState.getBlockState() instanceof GCYSMetaBlocks.LIGHT_SENSORS.getState()) {

            }
        });
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return null;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityGreenhouse(metaTileEntityId, tier);
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public String getTierlessTooltipKey() {
        return ITieredMetaTileEntity.super.getTierlessTooltipKey();
    }
}
