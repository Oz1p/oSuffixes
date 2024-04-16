package me.oz1p.osuffixes;
import org.bukkit.entity.Player;
public class Config {
    public static void ReloadData(){
        Expansion.plugin.reloadConfig();
    }
    public static void Insert(Player player, String suffix){
        Expansion.plugin.getConfig().set("data." + player.getName(), suffix);
        Expansion.plugin.saveConfig();
        ReloadData();
    }
    public static void Delete(Player player){
        Expansion.plugin.getConfig().set("data." + player.getName(), "");
        Expansion.plugin.saveConfig();
        ReloadData();
    }
    public static boolean UserExist(Player player){
        if(Expansion.plugin.getConfig().contains("data." + player.getName())){
            return true;
        }
        return false;
    }
    public static boolean Updates() {
        if(Expansion.plugin.getConfig().getString("update-notify") == "true"){
            return true;
        }
        return false;
    }
    public static String getSuffix(String player) {
        return Tools.processHexColors(Expansion.plugin.getConfig().getString("data." + player).replace("&", "§"));
    }

}
