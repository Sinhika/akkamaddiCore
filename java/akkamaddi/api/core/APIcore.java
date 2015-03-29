package akkamaddi.api.core;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, 
	 dependencies = "required-after:simplecore; after:MoCreatures")

public class APIcore 
{

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and 
     * register them with the GameRegistry. Register recipes.
     */
    @EventHandler 
    public void preInit(FMLPreInitializationEvent event)
    {} // end preInit()
    
    /**
     * Do your mod setup. Build whatever data structures you care about. 
     */
    @EventHandler
    public void Init(FMLInitializationEvent event)
    { } // end Init()
    
    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @EventHandler 
    public void postInit(FMLPostInitializationEvent event)
    {
        try
        {
            MinecraftForge.EVENT_BUS.register(new WerewolfHandler());
        }
        catch (ClassNotFoundException ignored) {}
    } // end postInit()
    
} // end class APIcore
