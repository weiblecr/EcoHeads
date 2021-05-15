package plugins.cardcraft.inc.spigotplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {
        // Don't log enabling, Spigot does that for you automatically!

        // Commands enabled with following method must have entries in plugin.yml
    	getLogger().info("Enabling The SpigotPlugin");
        getCommand("example").setExecutor(new ExampleCommand(this));
    }
}