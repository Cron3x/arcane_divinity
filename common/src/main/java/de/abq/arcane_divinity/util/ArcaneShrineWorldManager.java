package de.abq.arcane_divinity.util;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static de.abq.arcane_divinity.util.SimpleVec.vec3i;

public class ArcaneShrineWorldManager {
    private BlockPos center;
    private Level level;

    public ArcaneShrineWorldManager(Level level, BlockPos center) {
        ArcaneDivinity.LOG.debug("{}",level);
        this.center = center;
        this.level = level;
    }

    public boolean detect(){
        return  has(vec3i(0,0,0), ZBlocks.ARCANE_SHRINE_BLOCK.get()) &&
                has(vec3i(3,0,0), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(-3,0,0), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(0,0,3), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(0,0,-3), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(2,0,2), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(2,0,-2), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(-2,0,-2), ZBlocks.PEDESTAL_BLOCK.get()) &&
                has(vec3i(-2,0,2), ZBlocks.PEDESTAL_BLOCK.get()) &&

                has(vec3i(4,0,4), Blocks.CHISELED_POLISHED_BLACKSTONE) &&
                has(vec3i(-4,0,-4), Blocks.CHISELED_POLISHED_BLACKSTONE) &&
                has(vec3i(-4,0,4), Blocks.CHISELED_POLISHED_BLACKSTONE) &&
                has(vec3i(4,0,-4), Blocks.CHISELED_POLISHED_BLACKSTONE) &&

                has(vec3i(4,1,4), ZBlocks.ARCANE_OBELISK_BLOCK.get()) &&
                has(vec3i(-4,1,-4), ZBlocks.ARCANE_OBELISK_BLOCK.get()) &&
                has(vec3i(-4,1,4), ZBlocks.ARCANE_OBELISK_BLOCK.get()) &&
                has(vec3i(4,1,-4), ZBlocks.ARCANE_OBELISK_BLOCK.get());
    }

    private boolean has(Vec3i pos, Block should){
        ArcaneDivinity.LOG.debug("{}: {}", center.offset(pos), this.level.getBlockState(center.offset(pos)).getBlock());
        return this.level.getBlockState(center.offset(pos)).getBlock().equals(should);
    }
}