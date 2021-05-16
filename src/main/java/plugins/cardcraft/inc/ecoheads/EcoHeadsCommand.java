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
            if (!sender.hasPermission("headdb.open")) {
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

        if (sub.equalsIgnoreCase("info") || sub.equalsIgnoreCase("i")) {
            Utils.sendMessage(sender, "Running &cHeadDB v" + HeadDB.getInstance().getDescription().getVersion());
            Utils.sendMessage(sender, "Created by &c" + HeadDB.getInstance().getDescription().getAuthors());
            Utils.sendMessage(sender, "There are currently &c" + HeadAPI.getHeads().size() + " &7heads in the database.");
            return true;
        }
        
        Utils.sendMessage(sender, " ");
        Utils.sendMessage(sender, "&c&lHeadDB &c- &5Commands");
        Utils.sendMessage(sender, "&7&oParameters:&c command &9(aliases)&c arguments... &7- Description");
        Utils.sendMessage(sender, " > &c/hdb &7- Opens the database");
        Utils.sendMessage(sender, " > &c/hdb info &9(i) &7- Plugin Information");
        Utils.sendMessage(sender, " > &c/hdb search &9(s) &c<name> &7- Search for heads matching a name");
        Utils.sendMessage(sender, " > &c/hdb tagsearch &9(ts) &c<tag> &7- Search for heads matching a tag");
        Utils.sendMessage(sender, " > &c/hdb give &9(g) &c<id> <player> &6[amount] &7- Give player a head");
        Utils.sendMessage(sender, " ");
        return true;        
    }
}
