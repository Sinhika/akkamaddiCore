package akkamaddi.api.core;

import java.io.File;

import alexndr.api.core.LogHelper;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Not quite an interface or an abstract class, because it uses static classes.
 * @author higginbothamc
 *
 */
public class ASettings 
{

	public static Configuration config;

	/**
	 * Get configuration instance.
	 * @param cfgdir author-specific configuration sub-directory.
	 * @param configname mod-specific configuration file name.
	 * @return the configuration instance.
	 */
	public static Configuration GetConfig(FMLPreInitializationEvent event, String cfgdir, String configname )
	{
	    File installDir = event.getModConfigurationDirectory();
	    File configDir = new File(installDir, cfgdir);
	    File configFile = new File(configDir, configname);
	    return new Configuration(configFile);
	}
	
	/** 
	 * umbrella config loading routine. must be instantiated by child classes.
	 * @param event
	 */
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {}

	/**
	 * loads generic settings
	 */
	public static void genericSettings(String mod_id, String contents_blurb)
	{
		LogHelper.verboseInfo(mod_id, "Loading Generic Settings...");
		
		// toggles
        enableRecycling = config.getBoolean("Enable " + contents_blurb + " recycling recipes?", 
        		Configuration.CATEGORY_GENERAL, false,
        		"Enables fusion furnace recycling recipes for " + contents_blurb);
		enableArmorStatModification = config.getBoolean("(Advanced) Enable Armor Stat Modification?", 
				"Setting Toggles", false, 
				"Enables configuration of Armor stats. Relaunch game to generate the new settings.");
		enableBlockStatModification = config.getBoolean("(Advanced) Enable Block Stat Modification?", 
				"Setting Toggles", false, 
				"Enables configuration of Block stats. Relaunch game to generate the new settings.");
		enableCustomGeneration = config.getBoolean("(Advanced) Enable Custom Generation?", 
				"Setting Toggles", false, 
				"Enables Custom World Generation. Allows custom rules to be set. Relaunch the game to generate the new settings.");
		enableToolStatModification = config.getBoolean("(Advanced) Enable Tool Stat Modification?", 
				"Setting Toggles", false, 
				"Enables configuration of Tool stats. Relaunch game to generate the new settings.");
//			enableUpdateChecker = config.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the Update Checker for SimpleOres. Disabling will not check for updates.");
	} // end genericSettings()
	
	/** 
	 * loads/sets the ore generation variables.
	 */
	public static void adjustOreSpawnRates() {}

	/**
	 * Sets the default armor stats.
	 */
	public static void armorStatDefaults() {}
	
	/**
	 * sets customized armor stats.
	 */
	public static void customizeArmorStats() {}
	
	/**
	 * Sets the default block stats.
	 */
	public static void blockStatDefaults() {}

	/**
	 * sets customized block stats.
	 */
	public static void customizeBlockStats() {}
	
	/**
	 * Sets the default tool stats.
	 */
	public static void toolStatDefaults() {}

	/**
	 * set customized tool stats.
	 */
	public static void customizeToolStats() {}
	
	public static boolean enableRecycling;
	public static boolean enableHigherDimensionGen;
	public static boolean enableUpdateChecker;
	public static boolean enableCustomGeneration;
	public static boolean enableArmorStatModification;
	public static boolean enableBlockStatModification;
	public static boolean enableToolStatModification;

} // end class ASettings
