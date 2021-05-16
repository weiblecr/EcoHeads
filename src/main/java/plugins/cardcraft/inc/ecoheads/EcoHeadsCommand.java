package plugins.cardcraft.inc.ecoheads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tsp.headdb.HeadDB;
import tsp.headdb.api.HeadAPI;
import tsp.headdb.inventory.InventoryUtils;
import tsp.headdb.util.Utils;

public class EcoHeadsCommand implements CommandExecutor {
    EcoHeadsPlugin plugin;

    public EcoHeadsCommand(EcoHeadsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("ecoheaddb.open")) {
                Utils.sendMessage(sender, "&cNo permission!");
                return true;
            }
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cOnly players may open the database.");
                return true;
            }
            Player player = (Player) sender;

            Utils.sendMessage(player, "Opening &cHead Database");
            InventoryUtils.openDatabase(player);
            return true;
        }
        String sub = args[0];
        
        if (sub != null) 
        {
            if (!sender.hasPermission("ecoheaddb.search")) 
            {
                Utils.sendMessage(sender, "&cNo permission!");
                return true;
            }
            if (!(sender instanceof Player)) 
            {
                Utils.sendMessage(sender, "&cOnly players may open the database.");
                return true;
            }
            Player player = (Player) sender;

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; i++) 
            {
                builder.append(args[i]);
                if (i != args.length - 1) 
                {
                    builder.append(" ");
                }
            }
            String name = builder.toString();
            Utils.sendMessage(sender, "Searching for &e" + name);
            InventoryUtils.openSearchDatabase(player, name);
            return true;
        }
     
        Utils.sendMessage(sender, " ");
        Utils.sendMessage(sender, "&c&lEcoHeadsDB &c- &5Commands");
        Utils.sendMessage(sender, "&7&oParameters:&c command &9(aliases)&c arguments... &7- Description");
        Utils.sendMessage(sender, " > &c/ehdb &7- Opens the database");
        Utils.sendMessage(sender, " > &c/ehdb search &9(s) &c<name> &7- Search for heads matching a name");
        Utils.sendMessage(sender, " ");
        return true;        
    }
}
