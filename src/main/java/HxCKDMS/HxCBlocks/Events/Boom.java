package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Configs.Config;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.EntityLiving;
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
        removeBlocks(worldObj, explosionX, explosionY, explosionZ, explosionSize);
        List<EntityLiving> list = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(explosionX, explosionY, explosionZ, explosionSize));
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
    private static void removeBlocks(World world, int x, int y, int z, int radius){
        System.out.println("The Radius of LeBomb at x:" + x + " y:" + y + " z:" + z + " is : " + radius);
        long beginTime = System.nanoTime();
        long endTime;
            for(float xr = -radius; xr <= radius; xr += Config.LeBombAccuracy){
                float zrSquared = (float) Math.pow(radius, 2) - (float) Math.pow(xr, 2);
                if (zrSquared < 0) continue;
                int zl = Math.round((float) Math.sqrt(zrSquared));

                world.setBlockToAir(x + Math.round(xr), y, z + zl);
                world.setBlockToAir(x + Math.round(xr), y, z - zl);

                    for(int zf = y - zl; zf <= y + zl; zf++)
                        world.setBlockToAir(x + Math.round(xr), y, zf);

                for(float zr = -radius; zr <= radius; zr += Config.LeBombAccuracy){
                    float yrSquared = (float) Math.pow(radius, 2) - ((float) Math.pow(xr, 2) + (float) Math.pow(zr, 2));
                    if (yrSquared < 0) continue;
                    int yl = Math.round((float) Math.sqrt(yrSquared));

                    world.setBlockToAir(x + Math.round(xr), y + yl, z + Math.round(zr));
                    world.setBlockToAir(x + Math.round(xr), y - yl, z + Math.round(zr));

                        for(int yf = y - yl; yf <= y + yl; yf++)
                            world.setBlockToAir(x + Math.round(xr), yf, z + Math.round(zr));
                }
            }
        endTime = System.nanoTime();
        System.out.println("LeBomb Took " + ((endTime-beginTime) / 1000000000) + " seconds to go off!");
    }

    protected AxisAlignedBB getAreaBoundingBox(int x, int y, int z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - mod, y - mod, z - mod,
        /** Indented because CDO :P **/     x + mod, y + mod, z + mod);
    }
}