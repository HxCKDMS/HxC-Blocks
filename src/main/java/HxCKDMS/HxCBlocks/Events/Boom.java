package HxCKDMS.HxCBlocks.Events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.*;

@SuppressWarnings("unchecked")
public class Boom {

    private Random explosionRNG = new Random();
    private World worldObj;
    public int explosionX;
    public int explosionY;
    public int explosionZ;
    public Entity exploder;
    public int explosionSize;
    /**
     * A list of ChunkPositions of blocks affected by this explosion
     */
    public List affectedBlockPositions = new ArrayList();

    public Boom(World world, int x, int y, int z, int size) {
        worldObj = world;
        explosionX = x;
        explosionY = y;
        explosionZ = z;
        explosionSize = size;
        doExplosion();
    }

    public void doExplosion() {
        int f = this.explosionSize;
        HashSet hashset = new HashSet();
        int i, j, k;
        double d5, d6, d7;
        affectedBlockPositions.addAll(hashset);
        this.explosionSize *= 2.0F;
        i = MathHelper.floor_double(this.explosionX - (double) this.explosionSize - 1.0D);
        j = MathHelper.floor_double(this.explosionX + (double) this.explosionSize + 1.0D);
        k = MathHelper.floor_double(this.explosionY - (double) this.explosionSize - 1.0D);
        int i2 = MathHelper.floor_double(this.explosionY + (double) this.explosionSize + 1.0D);
        int l = MathHelper.floor_double(this.explosionZ - (double) this.explosionSize - 1.0D);
        int j2 = MathHelper.floor_double(this.explosionZ + (double) this.explosionSize + 1.0D);
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox((double) i, (double) k, (double) l, (double) j, (double) i2, (double) j2));
        Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

        for(int[] coordinate : getSphereCoordinates(explosionX, explosionY, explosionZ, 10))
            worldObj.setBlock(coordinate[0], coordinate[1], coordinate[2], Blocks.bedrock);




        for (Object aList : list) {
            Entity entity = (Entity) aList;
            double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) this.explosionSize;

            if (d4 <= 1.0D) {
                d5 = entity.posX - this.explosionX;
                d6 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
                d7 = entity.posZ - this.explosionZ;
                double d9 = (double) MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);

                if (d9 != 0.0D) {
                    d5 /= d9;
                    d6 /= d9;
                    d7 /= d9;
                    double d10 = (double) this.worldObj.getBlockDensity(vec3, entity.boundingBox);
                    double d11 = (1.0D - d4) * d10;
                    entity.attackEntityFrom(new DamageSource("BOOM"), (float) ((int) ((d11 * d11 + d11) / 2.0D * 8.0D * (double) this.explosionSize + 1.0D)));
                    double d8 = EnchantmentProtection.func_92092_a(entity, d11);
                    entity.motionX += d5 * d8;
                    entity.motionY += d6 * d8;
                    entity.motionZ += d7 * d8;
                }
            }
        }

        this.explosionSize = f;

        worldObj.playSound(explosionX, explosionY, explosionZ, "mob.wither.spawn", 1, 0.5f, true);
        Iterator iterator;
        ChunkPosition chunkposition;
        int ii;
        int jj;
        int kk;
        Block block;
        iterator = this.affectedBlockPositions.iterator();

        while (iterator.hasNext()) {
            chunkposition = (ChunkPosition) iterator.next();
            ii = chunkposition.chunkPosX;
            jj = chunkposition.chunkPosY;
            kk = chunkposition.chunkPosZ;
            block = this.worldObj.getBlock(ii, jj, kk);
            Block block1 = this.worldObj.getBlock(ii, jj - 1, kk);

            if (block.getMaterial() != Material.air) {
                worldObj.setBlockToAir(ii, jj, kk);
            }

            if (block.getMaterial() == Material.air && block1.func_149730_j() && this.explosionRNG.nextInt(3) == 0) {
                this.worldObj.setBlock(ii, jj, kk, Blocks.fire);
            }
        }
    }
    public int makeSphere(Vec3 pos, double radiusX, double radiusY, double radiusZ) {
        int affected = 0;

        radiusX += 0.5;
        radiusY += 0.5;
        radiusZ += 0.5;

        final double invRadiusX = 1 / radiusX;
        final double invRadiusY = 1 / radiusY;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusY = (int) Math.ceil(radiusY);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;

                    double distanceSq = lengthSq(xn, yn, zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) {
                                break forX;
                            }
                            break forY;
                        }
                        break forZ;
                    }
                    if (worldObj.setBlockToAir(x, y, z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(-x, y, z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(x, -y, z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(x, y, -z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(-x, -y, z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(x, -y, -z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(-x, y, -z)) {
                        ++affected;
                    }
                    if (worldObj.setBlockToAir(-x, -y, -z)) {
                        ++affected;
                    }
                }
            }
        }
        return affected;
    }

    private static double lengthSq(double x, double y, double z) {
        return (x * x) + (y * y) + (z * z);
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
}
