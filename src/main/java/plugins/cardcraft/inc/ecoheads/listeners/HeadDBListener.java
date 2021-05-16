package plugins.cardcraft.inc.ecoheads.listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import plugins.cardcraft.inc.ecoheads.util.Logger;
import tsp.headdb.inventory.PagedPane;


public class HeadDBListener implements Listener{

    public HeadDBListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        Inventory eventInventory = event.getInventory();
        //eventInventory.
        
        Logger.info("Logging Inventory Event");
        
        if (holder instanceof PagedPane) {
        	Logger.info("### Entering PagedPane Check ###");
        	ItemStack item = event.getCurrentItem();
        	ItemMeta itemMetaData = item.getItemMeta();
        	
        	Logger.info("EVENT NAME:" + event.getEventName());
        	Logger.info("EVENT ACTION:" + event.getAction());
        	Logger.info("EVENT ITEM META: " + itemMetaData.toString());
        	//event.g
        	Logger.info("### Exiting PagedPane Check ###");
        }
    }
}
