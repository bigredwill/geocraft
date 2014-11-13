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
				player.sendMessage("You are at x: " + x+" y: " + y + " z: " + z);
				
				GeoCoordinate gc = locFind.mcToGC(new MinecraftCoordinate(x, y, z));
				player.sendMessage("And you're real world coordinates are: " + gc.toString());
				return true;
			}
			
		} else if (cmd.getName().equalsIgnoreCase("setkeypoint")) {
			if(args.length < 5) {
				sender.sendMessage("/setKeyPoint <x> <y> <z> <lat> <lng>");	
			}
			else {
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				double lat = Double.parseDouble(args[3]);
				double lng = Double.parseDouble(args[4]);
				MinecraftCoordinate mc = new MinecraftCoordinate(x, y, z);
				GeoCoordinate gc = new GeoCoordinate(lat, lng);
				locFind.setKeyPoint(new KeyPoint(mc, gc));
				sender.sendMessage("KeyPointSet at " + mc.toString() + " " + gc.toString());
			}
		} else if (cmd.getName().equalsIgnoreCase("setscale")) {
			if(args.length != 1) {
				sender.sendMessage("/setscale <scale>");	
			}
			else {
				float s = Float.parseFloat(args[0]);
				locFind.setScale(s);
				sender.sendMessage("Scale set to: " + s);
			}
		}
		return false;
		
	}

	
}
