package gregtechfoodoption.fluid;

import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.GTUtility;
import gregtech.common.MetaFluids;
import gregtechfoodoption.material.GTFOFluidMaterial;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class GTFOMetaFluids {

    public static void init() {
        for (GTFOFluidMaterial fluidMat : GTFOFluidMaterial.GTFO_FLUIDS.values()) {
            Fluid fluid = new Fluid(fluidMat.name, MetaFluids.FLUID_NONE_TEXTURE, MetaFluids.FLUID_NONE_TEXTURE, GTUtility.convertRGBtoOpaqueRGBA_MC(fluidMat.rgb));
            fluid.setTemperature(fluidMat.temperature);
            if (!FluidRegistry.isFluidRegistered(fluid.getName())) {
                FluidRegistry.registerFluid(fluid);

                // Fluid Tooltips
                List<String> tooltip = new ArrayList<>();
                tooltip.add(fluidMat.getFormula());
                tooltip.add(String.valueOf(fluidMat.temperature));
                tooltip.add(String.valueOf(fluid.isGaseous()));
                FluidTooltipUtil.registerTooltip(fluid, tooltip);
                FluidTooltipUtil.registerTooltip(fluid, fluidMat.getFormula());
                FluidRegistry.addBucketForFluid(fluid);
                fluidMat.fluid = fluid;
            } else {
                if (!FluidRegistry.hasBucket(FluidRegistry.getFluid(fluid.getName()))) {
                    FluidRegistry.addBucketForFluid(fluid);
                }
                fluidMat.fluid = FluidRegistry.getFluid(fluid.getName());
            }
        }
    }
}