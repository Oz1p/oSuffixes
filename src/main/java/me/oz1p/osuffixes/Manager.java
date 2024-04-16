package me.oz1p.osuffixes;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
public class Manager {
    public static JavaPlugin plugin;
    private static File langFile;
    private static YamlConfiguration langConfig;
    public Manager(JavaPlugin plugin) {
        this.plugin = plugin;
        langFile = new File(plugin.getDataFolder(), "lang.yml");
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }
    public static void ReloadLang(){
        langFile = new File(plugin.getDataFolder(), "lang.yml");
        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }
    public String getText(String key) {
        String text = Tools.processHexColors(langConfig.getString(key));
        if (text != null) {
            text = text.replace("&", "§");
            if (text.contains("%prefix%")){
                return text.replace("%prefix%", getText("prefix"));
            }
        }
        return text;
    }
}
