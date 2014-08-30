package akkamaddi.akkamaddiCore.api;

import java.util.SortedSet;
import java.util.TreeSet;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "akkamaddicore", name = "akkamaddi's Core API", version = "1.7.10-1.0.0", 
	 dependencies = "required-after:simpleores; required-after:fusionplugin")

public class APIcore 
{
	@Instance("akkamaddicore")
	public static APIcore instance;
	
	public SortedSet<AkkaJoinWorldHelper> joinWorldModRegistry; // add mods that have equipment for mobs.
	
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "akkamaddi.akkamaddiCore.api.ClientProxy", 
    			serverSide = "akkamaddi.akkamaddiCore.api.CommonProxy")
    CommonProxy proxy;
    
    /**
     * Run before anything else. Read your config, create blocks, items, etc, and 
     * register them with the GameRegistry. Register recipes.
     */
    @EventHandler 
    public void preInit(FMLPreInitializationEvent event)
    {
    	joinWorldModRegistry = new TreeSet<AkkaJoinWorldHelper>();
    } // end preInit()
    
    /**
     * Do your mod setup. Build whatever data structures you care about. 
     */
    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new HandlerJoinWorld());
    } // end Init()
    
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
