package akkamaddi.akkamaddiCore.api;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        // This is for rendering entities and so forth later on
    }

    public int addArmor(String armor)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }
} // end class ClientProxy
