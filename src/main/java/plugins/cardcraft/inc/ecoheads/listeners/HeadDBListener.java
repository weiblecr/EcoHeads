package plugins.cardcraft.inc.ecoheads.listeners;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.EconomyResponse;
import plugins.cardcraft.inc.ecoheads.EcoHeadsPlugin;
import plugins.cardcraft.inc.ecoheads.util.Logger;
import tsp.headdb.inventory.PagedPane;
import tsp.headdb.util.Utils;


public class HeadDBListener implements Listener{

    public HeadDBListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
    	
    	if(event == null)
    	{
    		return;
    	}
    	
        InventoryHolder holder = event.getInventory().getHolder();
        HumanEntity player = event.getWhoClicked();
        
        //eventInventory.
         
        if (holder instanceof PagedPane) {
        	Logger.info("### Entering PagedPane Check ###");
        	Logger.info("PLAYER: "+player.getName());
        	if(event.getCurrentItem() == null)
        	{
        		return;
        	}
        	
        	ItemStack item = event.getCurrentItem();
        	ItemMeta itemMetaData = item.getItemMeta();
        	String itemMetaString = itemMetaData.toString();
        	
        	Logger.info("EVENT NAME:" + event.getEventName());
        	Logger.info("EVENT ACTION:" + event.getAction());
        	Logger.info("EVENT ITEM META: " + itemMetaString);
        	if(! itemMetaString.contains("\"text\":\"Page \""))
        	{
        		Logger.info("DETECTED HEAD SALE!!!!");
        		double playerBalance = EcoHeadsPlugin.ECONOMY.getBalance(player.getName());
        		Logger.info("Current Player Balance: " + playerBalance);
        		Player playerInstance = Bukkit.getPlayer(player.getName());
        		
        		if(playerBalance >= 500.0)
        		{
            		EconomyResponse response = EcoHeadsPlugin.ECONOMY.withdrawPlayer(player.getName(), 500.0);
                    if(response.transactionSuccess()) 
                    {
                    	Logger.info("Transaction Success, New Balance: "+ EcoHeadsPlugin.ECONOMY.getBalance(player.getName()));                    	
                    	playerInstance.playSound(playerInstance.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 100);
                    } 
                    else 
                    {
                    	Logger.info("Transaction Failed: "+response.errorMessage);
                    	Utils.sendMessage(playerInstance, "Transaction Failed: " + response.errorMessage);
                    	playerInstance.playSound(playerInstance.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 100);
                    	playerInstance.getInventory().remove(item);
                    	return;
                    }
        		}
        		else
        		{       
        			Logger.info("Transaction Failed: Insufficient Account Balance, Heads cost 500 each");
        			Utils.sendMessage(playerInstance, "Transaction Failed: Insufficient Account Balance");
        			playerInstance.playSound(playerInstance.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 100);
        			playerInstance.getInventory().remove(item);
        		}


        	}

        	Logger.info("### Exiting PagedPane Check ###");
        }
    }
}
