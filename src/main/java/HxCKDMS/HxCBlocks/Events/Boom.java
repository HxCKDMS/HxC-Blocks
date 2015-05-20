package HxCKDMS.HxCBlocks.Events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class Boom {

    private Random explosionRNG = new Random();
    private World worldObj;
    public int explosionX;
    public int explosionY;
    public int explosionZ;
    public int explosionSize;

    public List<ChunkPosition> affectedBlockPositions;

    public Boom(World world, int x, int y, int z, int size) {
        worldObj = world;
        explosionX = x;
        explosionY = y;
        explosionZ = z;
        explosionSize = size;
        doExplosion();
    }

    public void doExplosion() {
        List<EntityLiving> list = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(explosionX, explosionY, explosionZ, explosionSize));
        for(int[] coordinate : getSphereCoordinates(explosionX, explosionY, explosionZ, 10))
            worldObj.setBlock(coordinate[0], coordinate[1], coordinate[2], Blocks.bedrock);

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

        for (ChunkPosition chunkposition : affectedBlockPositions) {
            int pX = chunkposition.chunkPosX;
            int pY = chunkposition.chunkPosY;
            int pZ = chunkposition.chunkPosZ;

            Block block = this.worldObj.getBlock(pX, pY, pZ);
            if (block.getMaterial() != Material.air) worldObj.setBlockToAir(pX, pY, pZ);
            if (block.getMaterial() == Material.air && this.explosionRNG.nextInt(3) == 0) worldObj.setBlock(pX, pY, pZ, Blocks.fire);
        }
    }

    private static ArrayList<int[]> getCircleCoordinates(int x, int y, int z, int radius){
        ArrayList<int[]> coordinates = new ArrayList<>();

        for(float xr = -radius; xr <= radius; xr += 0.01){
            float zrSquared =  (float)Math.pow(radius, 2) - (float)Math.pow(xr, 2);
            if(zrSquared < 0) continue;
            int zl = Math.round((float) Math.sqrt(zrSquared));

            coordinates.add(new int[]{x + Math.round(xr), y, z + zl});
            coordinates.add(new int[]{x + Math.round(xr), y, z - zl});
        }
        return coordinates;
    }

    private static ArrayList<int[]> getSphereCoordinates(int x, int y, int z, int radius){
        ArrayList<int[]> coordinates = new ArrayList<>();

            for(float xr = -radius; xr <= radius; xr += 0.01){
                float zrSquared = (float) Math.pow(radius, 2) - (float) Math.pow(xr, 2);
                if (zrSquared < 0) continue;
                int zl = Math.round((float) Math.sqrt(zrSquared));

                coordinates.add(new int[]{x + Math.round(xr), y, z + zl});
                coordinates.add(new int[]{x + Math.round(xr), y, z + zl});

                for(float zr = -radius; zr <= radius; zr += 0.01){
                    float yrSquared = (float) Math.pow(radius, 2) - ((float) Math.pow(xr, 2) + (float) Math.pow(zr, 2));
                    if (yrSquared < 0) continue;
                    int yl = Math.round((float) Math.sqrt(yrSquared));

                    coordinates.add(new int[]{x + Math.round(xr), y + yl, z + Math.round(zr)});
                    coordinates.add(new int[]{x + Math.round(xr), y - yl, z + Math.round(zr)});
                }
            }
        return coordinates;
    }

    protected AxisAlignedBB getAreaBoundingBox(int x, int y, int z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - mod, y - mod, z - mod,
        /** Indented because CDO :P **/     x + mod, y + mod, z + mod);
    }
}
