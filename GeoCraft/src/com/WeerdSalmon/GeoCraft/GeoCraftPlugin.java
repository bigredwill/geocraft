package com.WeerdSalmon.GeoCraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GeoCraftPlugin extends JavaPlugin implements Listener {
	
	public LocationFinder locFind;
	
	public GeoCraftPlugin() {
	}
	
	public void onEnable() {
        locFind = new LocationFinder(this);
        //load user scale
		try {
			FileConfiguration config = this.getConfig();
			locFind.setScale(config.getDouble("scale"));
			this.getLogger().info("scale found");
			
		} catch (Exception e) {
			this.getLogger().info("No scale has been set");
		}
		//load user keypoint
		try {
			FileConfiguration config = this.getConfig();
			int x = config.getInt("keypointx");
			int y = config.getInt("keypointy");
			int z = config.getInt("keypointz");
			MinecraftCoordinate mc = new MinecraftCoordinate(x, y, z);
			
			double lat = config.getDouble("keypointlat");
			double lng = config.getDouble("keypointlng");
			GeoCoordinate gc = new GeoCoordinate(lat, lng);	
			locFind.setKeyPoint(new KeyPoint(mc, gc));
			
			this.getLogger().info("Keypoint found");
			
		} catch (Exception e) {
			this.getLogger().info("No keypoint has been set");
		}
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
						
				String msg = "";
				GeoCoordinate gc = locFind.mcToGC(new MinecraftCoordinate(x, y, z));
				getLogger().info(gc.toString());
				msg = String.format("You are at:\n%s\nlatitude: %f longitude: %f\n", locFind.geocode(gc), gc.latitude, gc.longitude);
				sender.sendMessage(msg);				
				return true;
			}
			
		} else if (cmd.getName().equalsIgnoreCase("geotp")) {
			if(args.length != 2) {
				sender.sendMessage("/geotp <lat> <lng>");	
			}
			else {
				double lat = Double.parseDouble(args[0]);
				double lng = Double.parseDouble(args[1]);
				
				MinecraftCoordinate mc = locFind.gcToMC(new GeoCoordinate(lat, lng));
				
				
				getLogger().info(mc.toString());
				
				if(sender instanceof Player) {
					Player player = (Player) sender;
					World world = Bukkit.getServer().getWorld("world");
					Block y = world.getHighestBlockAt(mc.x, mc.z);
					player.teleport(new Location(world, mc.x, y.getY() + 1, mc.z));
					sender.sendMessage("You are now at: " + mc.toString());
				}
				

			}
		} else if (cmd.getName().equalsIgnoreCase("whereis")) {
			if(args.length != 3) {
				sender.sendMessage("/whereis <x> <y> <z>");	
			}
			else {
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				
				GeoCoordinate gc = locFind.mcToGC(new MinecraftCoordinate(x,y,z));
				
				sender.sendMessage(gc.toString());
				getLogger().info(gc.toString());
				

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
				
				//add to config
				FileConfiguration config = this.getConfig();
				config.set("keypointx", x);
				config.set("keypointy", y);
				config.set("keypointz", z);
				config.set("keypointlat", lat);
				config.set("keypointlng", lng);
				sender.sendMessage("KeyPointSet at " + mc.toString() + " " + gc.toString());
				this.saveConfig();
			}
		} else if (cmd.getName().equalsIgnoreCase("setscale")) {
			if(args.length != 1) {
				sender.sendMessage("/setscale <scale>");	
			}
			else {
				float s = Float.parseFloat(args[0]);
				locFind.setScale(s);
				FileConfiguration config = this.getConfig();
				config.set("scale", s);
				this.saveConfig();
				sender.sendMessage("Scale set to: " + s);
			}
		} else if (cmd.getName().equalsIgnoreCase("getdefaults")) {
				FileConfiguration config = this.getConfig();	
				sender.sendMessage("\n"+config.saveToString());
		}
		return false;
		
	}

	
}
