package user.liltinyten.plugins.worldwhitelist;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
    private static FileConfiguration config = null;
    public static Main mainclass;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"WorldWhitelist has been enabled!");
        this.getCommand("wwhitelist").setExecutor(new whitelister());
        getServer().getPluginManager().registerEvents(new worldListener(), this);
        config = this.getConfig();
        this.saveDefaultConfig();
        mainclass = this;
    }

    public static FileConfiguration getConfigFile(){
        return config;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED+"WorldWhitelist has been disbaled!");
    }

}