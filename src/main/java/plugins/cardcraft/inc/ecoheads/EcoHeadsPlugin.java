package plugins.cardcraft.inc.ecoheads;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import plugins.cardcraft.inc.ecoheads.listeners.HeadDBListener;
import plugins.cardcraft.inc.ecoheads.util.Logger;

public class EcoHeadsPlugin extends JavaPlugin {
	
	public static Economy ECONOMY = null;
	
    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {

        if (!setupEconomy() ) 
        {
        	Logger.error(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
    	Logger.debug("Registering listeners...");
    	new HeadDBListener(this);

    	Logger.debug("Registering commands...");
        getCommand("ecoheads").setExecutor(new EcoHeadsCommand(this));
    }
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        ECONOMY = rsp.getProvider();
        return ECONOMY != null;
    }
}
