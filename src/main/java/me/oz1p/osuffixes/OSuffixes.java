package me.oz1p.osuffixes;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;
public final class OSuffixes extends JavaPlugin {
    public static String version = "1.1";
    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().log(Level.SEVERE, "PlaceholderAPI is not installed! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
        }else {
            PluginManager pluginManager = getServer().getPluginManager();
            Plugin placeholderAPI = pluginManager.getPlugin("PlaceholderAPI");
            getLogger().log(Level.INFO, "Hooked into PlaceholderAPI v" + placeholderAPI.getDescription().getVersion());
            Tools.Register(this);
            Tools.LoadYAMLs(this);
            getServer().getPluginManager().registerEvents(new UpdateManager(), this);
            getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
