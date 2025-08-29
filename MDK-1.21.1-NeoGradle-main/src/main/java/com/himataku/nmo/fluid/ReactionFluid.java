package com.himataku.nmo.fluid;

import com.himataku.nmo.block.AllBlock;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

import java.util.ArrayList;
import java.util.List;

public class ReactionFluid {

    private static class FluidReaction {
        String liquid1;
        String liquid2;
        String generatedBlock;

        FluidReaction(String liquid1, String liquid2, String generatedBlock) {
            this.liquid1 = liquid1;
            this.liquid2 = liquid2;
            this.generatedBlock = generatedBlock;
        }
    }

    private static final List<FluidReaction> REACTIONS = new ArrayList<>();

    static {
        // 反応パターンをここに追加していく
        REACTIONS.add(new FluidReaction("copper", "tin", "bronze_slag"));
        REACTIONS.add(new FluidReaction("tin", "copper", "bronze_slag"));
        REACTIONS.add(new FluidReaction("copper", "zinc", "brass_slag"));
        REACTIONS.add(new FluidReaction("zinc", "copper", "brass_slag"));
        REACTIONS.add(new FluidReaction("copper", "beryllium", "beryllium_copper_slag"));
        REACTIONS.add(new FluidReaction("beryllium", "copper", "beryllium_copper_slag"));
        // 反応は双方向登録するなら両方書く
        // 必要に応じて追加
    }

    public static void registerFluidInteractions() {
        for (FluidReaction reaction : REACTIONS) {
            FluidInteractionRegistry.addInteraction(
                    AllFluid.FLUID_MAP.get(reaction.liquid1).type.get(),
                    new FluidInteractionRegistry.InteractionInformation(
                            AllFluid.FLUID_MAP.get(reaction.liquid2).type.get(),
                            fluidState -> AllBlock.REGISTERED_BLOCKS.get(reaction.generatedBlock).get().defaultBlockState()
                    )
            );
        }
    }
}
