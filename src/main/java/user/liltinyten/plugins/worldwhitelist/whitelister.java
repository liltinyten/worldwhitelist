package user.liltinyten.plugins.worldwhitelist;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class whitelister implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            // for on and off
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("on")) {
                    // toggle on code here
                    if (sender.hasPermission("wwhitelist.on")) {
                        // checks if it's /wwhitelist on
                        if (Main.getConfigFile().contains(args[1] + ".enabled")) {
                            if (Main.getConfigFile().getBoolean(args[1] + ".enabled") == false) {
                                sender.sendMessage(ChatColor.GREEN +"Enabled WorldWhitelist for " + args[1]);
                                Main.getConfigFile().set((args[1] + ".enabled"), true);
                                Main.mainclass.saveConfig();
                            } else {
                                sender.sendMessage(ChatColor.RED +"WorldWhitelist is already enabled for this world!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.GREEN +"Enabled WorldWhitelist for " + args[1]);
                            Main.getConfigFile().set((args[1] + ".enabled"), true);
                            Main.mainclass.saveConfig();
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "No permission!");
                    }
                }
            }


            if (args[0].equalsIgnoreCase("off")) {
                // toggle off code here
                if (sender.hasPermission("wwhitelist.off")) {
                    // checks if it's /wwhitelist off
                    if (Main.getConfigFile().contains(args[1] + ".enabled")) {
                        if (Main.getConfigFile().getBoolean(args[1] + ".enabled") == true) {
                            sender.sendMessage(ChatColor.GREEN +"Disabled WorldWhitelist for " + args[1]);
                            Main.getConfigFile().set((args[1] + ".enabled"), false);
                            Main.mainclass.saveConfig();
                        } else {
                            sender.sendMessage(ChatColor.RED +"WorldWhitelist is already disabled for this world!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN +"Disabled WorldWhitelist for " + args[1]);
                        Main.getConfigFile().set((args[1] + ".enabled"), false);
                        Main.mainclass.saveConfig();
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "No permission!");
                }



            }

            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.YELLOW+"/wwhitelist add/remove player world - Allows you to add and remove players from a world whitelist" + "\n" + "/wwhitelist on/off world - Toggles WorldWhitelist for a world");
            }

            // for add and remove
            if (args.length == 3) {
                String worldname = args[2];
                String playername = args[1];

                if (args[0].equalsIgnoreCase("add")) {
                    // add code here
                    if (sender.hasPermission("wwhitelist.add")) {
                        if (Main.getConfigFile().contains(worldname + ".players")) {
                            List<String> players = Main.getConfigFile().getStringList(worldname + ".players");
                            OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
                            String PlayerID = player.getUniqueId().toString();
                            if (players.contains(PlayerID)) {
                                sender.sendMessage(ChatColor.RED +playername + " is already on the whitelist!");
                            } else {
                                players.add(PlayerID);
                                Main.getConfigFile().set(worldname + ".players", players);
                                sender.sendMessage(ChatColor.GREEN +"Added " + playername + " to the whitelist!");
                                Main.mainclass.saveConfig();
                            }

                        } else {
                            List<String> oneplayer = new ArrayList<String>();
                            OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
                            String PlayerID = player.getUniqueId().toString();
                            oneplayer.add(PlayerID);
                            Main.getConfigFile().set(worldname + ".players", oneplayer);
                            sender.sendMessage(ChatColor.GREEN +"Added " + playername + " to the whitelist!");
                            Main.mainclass.saveConfig();
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED+"No permission!");
                    }

                }
                if (args[0].equalsIgnoreCase("remove")) {
                    // remove code here
                    if (sender.hasPermission("wwhitelist.remove")) {
                        if (Main.getConfigFile().contains(worldname + ".players")) {
                            List<String> players = Main.getConfigFile().getStringList(worldname + ".players");
                            OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
                            String PlayerID = player.getUniqueId().toString();
                            if (players.contains(PlayerID)) {
                                players.remove(PlayerID);
                                Main.getConfigFile().set(worldname + ".players", players);
                                sender.sendMessage(ChatColor.GREEN +"Removed " + playername + " from the whitelist!");
                                Main.mainclass.saveConfig();
                            } else {
                                sender.sendMessage(ChatColor.RED + playername + " is not in the whitelist!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED +playername + " is not in the whitelist!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "No permission!");
                    }
                }

            }
        }


        return false;
    }

}