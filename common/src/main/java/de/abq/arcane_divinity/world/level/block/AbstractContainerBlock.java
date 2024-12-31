package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.world.level.block.entity.SimpleInventoryBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EntityBlock;

public abstract class AbstractContainerBlock extends AbstractBlock implements EntityBlock {
    public AbstractContainerBlock(Properties $$0) {
        super($$0);
    }

    /**
     * *WARNING*: This function looks like it could handle multiple stacks, but it can't, just easy that way
     * @param stacks should only be set to one, it would override other stacks
     */
    protected <T extends SimpleInventoryBlockEntity> void handleInteraction(ServerPlayer player, T entity, ItemStack... stacks){
        Container container = entity.getItemHandler();
        ItemStack returned = container.getItem(0);
        for (ItemStack stack : stacks){
            container.setItem(0,new ItemStack(stack.getItem(), 1));
            stack.setCount(stack.getCount()-1);
        }

        Inventory pInv = player.getInventory();

        if (pInv.getFreeSlot() <= 0){
            player.level().addFreshEntity(new ItemEntity(entity.getLevel(), entity.getBlockPos().getX()+0.5, entity.getBlockPos().getY()+1, entity.getBlockPos().getZ()+0.5, returned));
        } else {
            player.getInventory().add(returned);
        }
        entity.setChanged();
    }
}
