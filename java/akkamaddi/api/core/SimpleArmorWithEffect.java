package akkamaddi.api.core;

import alexndr.api.content.items.SimpleArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

abstract public class SimpleArmorWithEffect extends SimpleArmor 
{
	public static enum ARMOR_TYPE { HELM, CHEST, LEGS, BOOTS };
	
	// { helm, chest, legs, boots }
	protected ItemStack[] armor = { null, null, null, null };

	public SimpleArmorWithEffect(ArmorMaterial armormaterial, int slotnumber)
	{
		super(armormaterial, slotnumber);	
	}
	
	@Deprecated
	public SimpleArmorWithEffect(ArmorMaterial armormaterial, int renderer,
			int slotnumber) 
	{
		super(armormaterial, slotnumber);
	}

	/**
	 * Called to tick armor in the armor slot. Must be overridden.
	 * 
	 * @param world
	 * @param player
	 * @param itemStack
	 */
	@Override
	abstract public void onArmorTick(World world, EntityPlayer player,
			ItemStack itemStack);

	/**
	 * Helper method to sift through player's armor and figure out which piece
	 * is helm, chest, legs, boots, etc.
	 * 
	 * @param player
	 */
	public static void getArmorPieces(EntityPlayer player, ItemStack[] ar) 
	{
		if (ar == null) {
			ar = new ItemStack[4];
		}
		// search armor list for the different pieces.
		for (int i = 0; i < 4; i++) {
			if (player.getCurrentArmor(i) == null) {
				continue;
			}
			ItemArmor armorPiece = (ItemArmor) player.getCurrentArmor(i).getItem();
			// armorType: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
			ar[armorPiece.armorType] = player.getCurrentArmor(i);
		} // end-for
		
	} // end static getArmorPieces
	
	public void getArmorPieces(EntityPlayer player) 
	{
		getArmorPieces(player, this.armor);
	} // end getArmorPieces()

} // end class SimpleArmorWithEffect
