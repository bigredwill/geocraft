package com.WeerdSalmon.LatLngSpwn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
	GeoSpawnPlugin plug;
	public LoginListener(GeoSpawnPlugin plugin) {
		this.plug = plugin;
    }
	@EventHandler
    public void normalLogin(PlayerLoginEvent event) {
        plug.getLogger().info(event.getAddress().getHostAddress() + " "+ event.getPlayer().getDisplayName());
        plug.locFind.ipToGC(event.getAddress().getHostAddress());
//        event.getPlayer().setBedSpawnLocation(arg0, arg1);
        
        
    }
 

}






