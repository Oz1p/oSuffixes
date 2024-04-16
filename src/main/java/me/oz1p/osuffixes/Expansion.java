package me.oz1p.osuffixes;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
public class Expansion extends PlaceholderExpansion {
    public static OSuffixes plugin;
    public Expansion(OSuffixes plugin) {
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "osuffixes";
    }
    @Override
    public @NotNull String getAuthor() {
        return "Oz1p";
    }
    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("suffix")) {
            if(Config.getSuffix(player.getName()) == null){
                return "";
            }
            return Tools.processHexColors(Config.getSuffix(player.getName()));
        }
        return "";
    }

}
