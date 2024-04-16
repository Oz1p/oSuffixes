package me.oz1p.osuffixes;
import org.bukkit.ChatColor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tools {
    public static String processHexColors(String message) {
        Pattern pattern = Pattern.compile("(#[a-fA-F0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }
            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message).replace('&', '§');
    }
    public static void LoadYAMLs(OSuffixes plugin){
        plugin.saveDefaultConfig();
        File langFile = new File(plugin.getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            try (InputStream inputStream = plugin.getResource("lang.yml")) {
                Files.copy(inputStream, langFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, e.toString());
            }
        }
        Config.ReloadData();
        Manager.ReloadLang();
        plugin.reloadConfig();
    }
    public static void Register(OSuffixes plugin){
        new Expansion(plugin).register();
        plugin.getCommand("oSuffixes").setExecutor(new CommandExecutor(plugin));
        plugin.getCommand("oSuffixes").setTabCompleter(new TabComplete());
    }
}
