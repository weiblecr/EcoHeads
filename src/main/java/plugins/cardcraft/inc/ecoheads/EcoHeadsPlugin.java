package plugins.cardcraft.inc.ecoheads;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.cardcraft.inc.ecoheads.listeners.HeadDBListener;
import plugins.cardcraft.inc.ecoheads.util.Logger;

public class EcoHeadsPlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {


    	Logger.debug("Registering listeners...");
    	new HeadDBListener(this);

        getCommand("ecoheads").setExecutor(new EcoHeadsCommand(this));
    }
}
