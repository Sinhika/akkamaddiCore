package akkamaddi.api.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class WerewolfHandler
{
    private final Class<?> werewolf;
    private final Method isHumanForm;
    private boolean werewolfExists;
    
    public static Map<Item, Float> Damage2Wolf = new HashMap<Item, Float>();
    
    public WerewolfHandler() throws ClassNotFoundException
    {
    	try {
    		werewolf = Class.forName("drzhark.mocreatures.entity.monster.MoCEntityWerewolf");
    	}
    	catch (ClassNotFoundException e)
    	{
    		werewolfExists = false;
    		throw e;
    	}
    	
        try
        {
            isHumanForm = werewolf.getMethod("getIsHumanForm");
        }
        catch (NoSuchMethodException e)
        {
        	werewolfExists = false;
            throw new RuntimeException(e);
        }
        catch (SecurityException e)
        {
        	werewolfExists = false;
            throw new RuntimeException(e);
        }
        werewolfExists = true;
    } // end ctor()

    private boolean isWerewolfInWolfForm(EntityLivingBase entityLiving)
    {
        try
        {
            return werewolf.isInstance(entityLiving) && !(Boolean) isHumanForm.invoke(entityLiving);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    } // end isWerewolfInWolfForm()

    @SubscribeEvent public void onLivingHurt(LivingHurtEvent event)
    {
        if (!werewolfExists || !isWerewolfInWolfForm(event.entityLiving))
        {
            return;
        }

        Entity entity;
        ItemStack stack;

        if (!(event.source instanceof EntityDamageSource && event.source != null
                && (entity = ((EntityDamageSource) event.source).getEntity()) instanceof EntityLivingBase
                && (stack = ((EntityLivingBase) entity).getHeldItem()) != null))
        {
            return;
        }

        Item item = stack.getItem();
        if (Damage2Wolf.containsKey(item)) {
        	event.ammount = Damage2Wolf.get(item);
        }
    } // end onLivingHurt()

	/**
	 * @return the werewolfExists
	 */
	public boolean isWerewolfExists() {
		return werewolfExists;
	}
} // end class WerewolfHandler

