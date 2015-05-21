package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Configs.Config;
import HxCKDMS.HxCCore.Utils.WorldHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class Boom {
    private World worldObj;
    public int explosionX;
    public int explosionY;
    public int explosionZ;
    public int explosionSize;

    public Boom(World world, int x, int y, int z, int size) {
        worldObj = world;
        explosionX = x;
        explosionY = y;
        explosionZ = z;
        explosionSize = size;
        doExplosion();
    }

    @SuppressWarnings("unchecked")
    public void doExplosion() {
        System.out.println("The Radius of LeBomb at x:" + explosionX + " y:" + explosionY + " z:" + explosionZ + " is : " + explosionSize);

        long beginTime = System.nanoTime();
        WorldHelper.drawSphere(worldObj, explosionX, explosionY, explosionZ, Blocks.air, explosionSize, false, Config.LeBombAccuracy);
        System.out.println("LeBomb Took " + ((System.nanoTime()-beginTime) / 1000000000) + " seconds to go off!");

        List<EntityLiving> list = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(explosionX, explosionY, explosionZ, 16));
        for (EntityLiving ent : list) {
            double distance = ent.getDistance(explosionX, explosionY, explosionZ) / explosionSize;

            if (distance <= 1.0D) {
                double dx = ent.posX - explosionX;
                double dy = ent.posY + (ent.getEyeHeight() - explosionY);
                double dz = ent.posZ - explosionZ;
                double dsq = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
                if (dsq != 0.0D) {
                    double reduction = EnchantmentProtection.func_92092_a(ent, 2);
                    ent.motionX += (dx / dsq * reduction);
                    ent.motionY += (dy / dsq * reduction);
                    ent.motionZ += (dz / dsq * reduction);
                }
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(int x, int y, int z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - mod, y - mod, z - mod,
        /** Indented because CDO :P **/     x + mod, y + mod, z + mod);
    }
}
