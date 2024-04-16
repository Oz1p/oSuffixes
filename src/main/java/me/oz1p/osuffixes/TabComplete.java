package me.oz1p.osuffixes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1 && "help".startsWith(args[0])) {
            completions.add("help");
        }
        if (args.length == 1 && "set".startsWith(args[0])) {
            completions.add("set");
        }
        if (args.length == 2 && "set".equals(args[0])) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }
        if (args.length == 2 && "remove".equals(args[0])) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }
        if (args.length == 1 && "remove".startsWith(args[0])) {
            completions.add("remove");
        }
        if (args.length == 1 && "reload".startsWith(args[0])) {
            completions.add("reload");
        }
        return completions;
    }}