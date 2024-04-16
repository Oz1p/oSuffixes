package me.oz1p.osuffixes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(Config.UserExist(event.getPlayer()) == false){
            Config.Insert(event.getPlayer(), "");
        }
    }
}
