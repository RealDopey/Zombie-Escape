package com.mc_ze.ZombieEscape;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
	Main plugin = Main.getInstance();
	FileConfiguration config = plugin.getConfig();
	
	public Location getLobby() {
		return Util.parseLocation(config.getString("Locations.Lobby"));
	}
	
	public void setLobby(Location loc) {
		config.set("Locations.Lobby", Util.formatLocation(loc));
		plugin.saveConfig();
	}
	
	public Location getSpawn(String world) {
		return Util.parseLocation(config.getString("Locations." + world + ".Spawn"));
	}
	
	public void setSpawn(String world, Location loca) {
		String loc = Util.formatLocation(loca);
		config.set("Locations." + world + ".Spawn", loc);
		plugin.saveConfig();
	}
	
	public List<String> getMapsList() {
		return config.getStringList("Maps");
	}
	
	public int getCountdown() {
		return config.getInt("Countdown");
	}
	
	public int getMinPlayers() {
		return config.getInt("MinPlayers");
	}
}
