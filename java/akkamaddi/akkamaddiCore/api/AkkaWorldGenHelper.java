/**
 * @file AkkaWorldGenHelp.java
 * Implementation of AkkaWorldGenHelper class.
 * @author Sinhika
 */
package akkamaddi.akkamaddiCore.api;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Copied from SimpleOres.WorldGenHelper, but modified to use isOreGenReplaceable() function.
 *
 */
public class AkkaWorldGenHelper extends WorldGenerator 
{

	/** The block ID of the ore to be placed using this generator. */
    protected Block minableBlock;
    protected int minableBlockMeta = 0;

    /** The number of blocks to generate. */
    protected int numberOfBlocks;
    
    /** Block that the new block will spawn inside of. For most ores, this is Stone for example. 
     * Defaults to stone, can be overridden by the constructor call.*/
    protected Block replacedBlock = Blocks.stone;
    
    public AkkaWorldGenHelper(Block blockToMine, int number, Block blockToReplace)
    {
        this.minableBlock = blockToMine;
        this.numberOfBlocks = number;
        this.replacedBlock = blockToReplace;
    }

    public AkkaWorldGenHelper(Block blockToMine, int meta, int number, Block blockToReplace)
    {
        this(blockToMine, number, blockToReplace);
        this.minableBlockMeta = meta;
    }

	/* (non-Javadoc)
	 * @see net.minecraft.world.gen.feature.WorldGenerator#generate(net.minecraft.world.World, java.util.Random, int, int, int)
	 */
	@Override
	public boolean generate(World world, Random r, int x, int y, int z) 
	{
        float var6 = r.nextFloat() * (float)Math.PI;
        double var7 = (double)((float)(x + 8) + MathHelper.sin(var6) * (float)this.numberOfBlocks / 8.0F);
        double var9 = (double)((float)(x + 8) - MathHelper.sin(var6) * (float)this.numberOfBlocks / 8.0F);
        double var11 = (double)((float)(z + 8) + MathHelper.cos(var6) * (float)this.numberOfBlocks / 8.0F);
        double var13 = (double)((float)(z + 8) - MathHelper.cos(var6) * (float)this.numberOfBlocks / 8.0F);
        double var15 = (double)(y + r.nextInt(3) - 2);
        double var17 = (double)(y + r.nextInt(3) - 2);

        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19)
        {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.numberOfBlocks;
            double var26 = r.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double var28 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / 
            							(float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (double)(MathHelper.sin((float)var19 * (float)Math.PI / 
            							(float)this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                double var39 = ((double)var38 + 0.5D - var20) / (var28 / 2.0D);

                if (var39 * var39 < 1.0D)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        double var42 = ((double)var41 + 0.5D - var22) / (var30 / 2.0D);

                        if (var39 * var39 + var42 * var42 < 1.0D)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                double var45 = ((double)var44 + 0.5D - var24) / (var28 / 2.0D);

                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D 
                                	&& world.getBlock(var38, var41, var44).isReplaceableOreGen(world, x, y, z, this.replacedBlock))
                                {
                                    world.setBlock(var38, var41, var44, this.minableBlock, minableBlockMeta, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
	} // end generate()

} // end class WorldGenHelper
