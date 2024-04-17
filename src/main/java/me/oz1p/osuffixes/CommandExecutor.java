package me.oz1p.osuffixes;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    private final Manager mgr;
    public CommandExecutor(JavaPlugin plugin) {
        this.mgr = new Manager(plugin);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            commandSender.sendMessage(mgr.getText("messages.help.text"));
            commandSender.sendMessage(mgr.getText("messages.help.Line1"));
            commandSender.sendMessage(mgr.getText("messages.help.Line2"));
            commandSender.sendMessage(mgr.getText("messages.help.Line3"));
            commandSender.sendMessage(mgr.getText("messages.help.Line4"));
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            mgr.ReloadLang();
            mgr.plugin.reloadConfig();
            commandSender.sendMessage(mgr.getText("messages.reloaded"));
            return true;
        }
        if (args.length >= 3 && args[0].equalsIgnoreCase("set")) {
            if(!commandSender.hasPermission("osuffixes.set")){
                commandSender.sendMessage(mgr.getText("messages.no-perms"));
                return true;
            }
            Player player = Bukkit.getPlayer(args[1]);
            if(player != null && player.isOnline()){
                StringBuilder suffixBuilder = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    suffixBuilder.append(args[i]).append(" ");
                }
                String suffix = suffixBuilder.toString().trim();
                commandSender.sendMessage(Tools.processHexColors(PlaceholderAPI.setPlaceholders(player, mgr.getText("messages.suffixes.set").replace("%player%", args[1]).replace("%suffix%", suffix).replace("&", "§"))));
                Config.Insert(player, suffix.replace("&", "§"));
                return true;
            }
            else{
                commandSender.sendMessage(mgr.getText("messages.player-offline").replace("%player%", args[1]));
                return true;
            }
        }
        if (args.length >= 2 && args[0].equalsIgnoreCase("remove")) {
            if(!commandSender.hasPermission("osuffixes.reset")){
                commandSender.sendMessage(mgr.getText("messages.no-perms"));
                return true;
            }
            Player player = Bukkit.getPlayer(args[1]);
            if(player != null && player.isOnline()){
                commandSender.sendMessage(PlaceholderAPI.setPlaceholders(player, mgr.getText("messages.suffixes.reset").replace("%player%", args[1])));
                Config.Delete(player);
                return true;
            }
            else{
                commandSender.sendMessage(mgr.getText("messages.player-offline").replace("%player%", args[1]));
                return true;
            }
        }
        if (args.length >= 1) {
            commandSender.sendMessage(mgr.getText("messages.help.text"));
            commandSender.sendMessage(mgr.getText("messages.help.Line1"));
            commandSender.sendMessage(mgr.getText("messages.help.Line2"));
            commandSender.sendMessage(mgr.getText("messages.help.Line3"));
            commandSender.sendMessage(mgr.getText("messages.help.Line4"));
            return true;
        }
        commandSender.sendMessage("§aThis Server is using §eoSuffixes §6"+ OSuffixes.version +"§a by §3Oz1p");
        commandSender.sendMessage("§aUse §6/os help§a for Help!");
        commandSender.sendMessage("§aIf you want to get the latest §eUpdates §acheck out our §6Spigot");
        Component message = Component.text(ChatColor.GREEN + "§9§nhttps://www.spigotmc.org/resources/osuffixes%E2%AD%90-lightweight-suffixes-manager.116232/")
                .clickEvent(ClickEvent.openUrl("https://www.spigotmc.org/resources/osuffixes%E2%AD%90-lightweight-suffixes-manager.116232/"))
                .hoverEvent(HoverEvent.showText(Component.text("§aClick Me!")));
        String legacyMessage = LegacyComponentSerializer.legacySection().serialize(message);
        commandSender.sendMessage(legacyMessage);
        return true;
    }
}
