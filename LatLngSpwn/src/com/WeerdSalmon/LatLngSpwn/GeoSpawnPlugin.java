package com.WeerdSalmon.LatLngSpwn;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GeoSpawnPlugin extends JavaPlugin implements Listener {
	public LocationFinder locFind;
	public LoginListener logList;
	public GeoSpawnPlugin() {
		locFind = new LocationFinder(this);
		logList = new LoginListener(this);
		
	}
	
	public void onEnable() {
        getServer().getPluginManager().registerEvents(new LoginListener(this), this);
        
        getLogger().info("");

    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(cmd.getName().equalsIgnoreCase("whereami")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
				player.getAddress();
				Location e = player.getLocation();
				int x = e.getBlockX();
				int y = e.getBlockY();
				int z = e.getBlockZ();
				getLogger().info("You are at\tx: " + x+" y: " + y);
				player.sendMessage("You are at x: " + x+" y: " + y);
				
				locFind.mcToGC(new MinecraftCoordinate(x, y, z));
				return true;
			}
			
		} else if (cmd.getName().equalsIgnoreCase("setKeyPoint")) {
			
		}
		return false;
		
	}

	
}
