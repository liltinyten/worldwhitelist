package user.liltinyten.pluginrequest.worldwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;


public class worldListener implements Listener {

	@EventHandler
	public void onWorld(PlayerChangedWorldEvent e) {
		
		// Checks if world whitelist is enabled for that world.
		if (Main.getConfigFile().contains(e.getPlayer().getWorld().getName() + ".enabled")) {
			if (Main.getConfigFile().getBoolean(e.getPlayer().getWorld().getName() + ".enabled") == true) {
				String worldname = e.getPlayer().getWorld().getName();
				
				// Checks the player's permission for the world and checks if the world's configuration has a player list.
				if ((Main.getConfigFile().contains(e.getPlayer().getWorld().getName() + ".players") || e.getPlayer().hasPermission("wwhitelist.bypass") || e.getPlayer().hasPermission("wwhitelist." + worldname) )) {
					// Checks if the player is allowed.
					if (!(Main.getConfigFile().getStringList(e.getPlayer().getWorld().getName() + ".players").contains(e.getPlayer().getUniqueId().toString()) || e.getPlayer().hasPermission("wwhitelist.bypass") || e.getPlayer().isOp())) {
						// Returns the player to their original world.
						e.getPlayer().teleport(e.getFrom().getSpawnLocation());
						e.getPlayer().sendMessage(ChatColor.RED + "You are not whitelisted on this world!");
					}
				
				   } else {
					   // Returns the player to their original world.
					   e.getPlayer().teleport(e.getFrom().getSpawnLocation());
					   e.getPlayer().sendMessage(ChatColor.RED + "You are not whitelisted on this world!");
				   }
			}
		}
			
		
		
		
	}
}
