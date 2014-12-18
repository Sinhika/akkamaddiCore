package akkamaddi.api.core;

import java.util.Random;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HandlerJoinWorld
{
    public static double rand;
    public static int range;
    public static Random random = new Random();

    public static final String HANDLED_KEY = "simpleores.spawn.handled";
    public static final String AKKA_HANDLED_KEY = "akkamaddicore.spawn.handled";
    
    /**
     * This class allows zombies and skeletons to spawn holding/wearing Addition items.
     */
    @SubscribeEvent
    public void EntityJoinWorldEvent(EntityJoinWorldEvent event)
    {
    	int n = APIcore.instance.joinWorldModRegistry.size();
    	
    	// skip mob if client-side, nor not zombie/skeleton, or already tagged by either Simple Ores
    	// or an akkamaddi Addition, or no relevant Additions exist.
        if ((n == 0) || event.world.isRemote
                || !(event.entity instanceof EntitySkeleton || event.entity instanceof EntityZombie)
                || event.entity.getEntityData().getBoolean(HANDLED_KEY)
                || event.entity.getEntityData().getBoolean(AKKA_HANDLED_KEY))
        {
              return;
        }
        
        // tag as handled for both Simple Ores and akkamaddi's Additions.
        event.entity.getEntityData().setBoolean(HANDLED_KEY, true);
        event.entity.getEntityData().setBoolean(AKKA_HANDLED_KEY, true);
           
        rand = Math.random();
        
        if (rand <= 0.03D)
        {
        	// pick an active Addition...
        	int i = random.nextInt(n);
        	AkkaJoinWorldHelper jwh = APIcore.instance.joinWorldModRegistry.toArray( new AkkaJoinWorldHelper[0])[i];
        	jwh.EquipMobs(event);
        }
    } // end EntityJoinWorldEvent()
} // end class HandlerJoinWorld
