package user.liltinyten.pluginrequest.worldwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;


public class worldListener implements Listener {

	@EventHandler
	public void onWorld(PlayerChangedWorldEvent e) {

		if (Main.getConfigFile().contains(e.getPlayer().getWorld().getName() + ".enabled")) {
			if (Main.getConfigFile().getBoolean(e.getPlayer().getWorld().getName() + ".enabled") == true) {
				String worldname = e.getPlayer().getWorld().getName();


				if ((Main.getConfigFile().contains(e.getPlayer().getWorld().getName() + ".players"))) {
					if (!(Main.getConfigFile().getStringList(e.getPlayer().getWorld().getName() + ".players").contains(e.getPlayer().getUniqueId().toString()) 
							|| e.getPlayer().hasPermission("wwhitelist.bypass") 
							|| e.getPlayer().isOp()
							|| e.getPlayer().hasPermission("wwhitelist." + worldname)
							)) {

						e.getPlayer().teleport(e.getFrom().getSpawnLocation());
						e.getPlayer().sendMessage(ChatColor.RED + "You are not whitelisted on this world!");
					}

				} else {
					e.getPlayer().teleport(e.getFrom().getSpawnLocation());
					e.getPlayer().sendMessage(ChatColor.RED + "You are not whitelisted on this world!");
				}
			}
		}




	}
}
