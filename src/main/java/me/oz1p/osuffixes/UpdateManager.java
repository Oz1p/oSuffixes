package me.oz1p.osuffixes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class UpdateManager implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        if(Config.Updates()){
            if(event.getPlayer().isOp()){
                String url = "https://pastebin.com/raw/nQxiQjvv";
                try {
                    String text = getTextFromURL(url);
                    if(!text.equalsIgnoreCase(OSuffixes.version)){
                        event.getPlayer().sendMessage("§aYou are using §eoSuffixes §6v" + OSuffixes.version);
                        event.getPlayer().sendMessage("§aOn our §6Spigot §ais a new version §7(§6" + text + "§7)");
                        Component message = Component.text(ChatColor.GREEN + "§9§nhttps://www.spigotmc.org/resources/osuffixes%E2%AD%90-lightweight-suffixes-manager.116232/")
                                .clickEvent(ClickEvent.openUrl("https://www.spigotmc.org/resources/osuffixes%E2%AD%90-lightweight-suffixes-manager.116232/"))
                                .hoverEvent(HoverEvent.showText(Component.text("§aClick Me!")));
                        String legacyMessage = LegacyComponentSerializer.legacySection().serialize(message);
                        event.getPlayer().sendMessage(legacyMessage);
                    }
                } catch (IOException e) {
                }
            }
        }
    }
    public static String getTextFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
}
