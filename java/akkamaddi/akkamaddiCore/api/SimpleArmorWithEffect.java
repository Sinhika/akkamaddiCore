package akkamaddi.akkamaddiCore.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alexndr.SimpleOres.api.content.SimpleArmor;

abstract public class SimpleArmorWithEffect extends SimpleArmor 
{
	public enum ARMOR_TYPE { HELM, CHEST, LEGS, BOOTS };
	
	// { helm, chest, legs, boots }
	protected ItemStack[] armor = { null, null, null, null };

	public SimpleArmorWithEffect(ArmorMaterial armormaterial, int renderer,
			int slotnumber) {
		super(armormaterial, renderer, slotnumber);
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
	public void getArmorPieces(EntityPlayer player) {
		// search armor list for the different pieces.
		for (int i = 0; i < 4; i++) {
			if (player.getCurrentArmor(i) == null) {
				continue;
			}
			ItemArmor armorPiece = (ItemArmor) player.getCurrentArmor(i).getItem();
			armor[armorPiece.armorType] = player.getCurrentArmor(i);
		} // end-for
	} // end getArmorPieces()

} // end class SimpleArmorWithEffect
