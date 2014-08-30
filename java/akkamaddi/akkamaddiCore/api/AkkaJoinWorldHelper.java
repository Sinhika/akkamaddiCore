package akkamaddi.akkamaddiCore.api;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;

abstract public class AkkaJoinWorldHelper 
{
    public static RandomRange random = new RandomRange();

	abstract public void EquipMobs(EntityJoinWorldEvent event);
} // end class AkkaJoinWorldHelper
