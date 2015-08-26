package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemMetBlue extends ItemFood {
    public ItemMetBlue() {
        super(0, 0F, false);
        this.setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        this.setUnlocalizedName("MetBlue");
        this.setTextureName("HxCBlocks:MetBlue");
        this.setMaxStackSize(32);
        this.setAlwaysEdible();
    }

    @Override
    public EnumAction getItemUseAction(ItemStack f){
        return EnumAction.drink;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        List<PotionEffect> activePotions = new ArrayList<>((Collection<PotionEffect>) player.getActivePotionEffects());
        for(PotionEffect potionEffect : activePotions) player.removePotionEffect(potionEffect.getPotionID());
        player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 600, 1));
        player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 1200, 2));
        if(!player.capabilities.isCreativeMode) player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));

        super.onFoodEaten(stack, world, player);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tt, boolean boo) {
        super.addInformation(stack, player, tt, boo);
        try {
            tt.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("metblue.tooltip"));
        } catch (Exception ignored){}
    }

}

