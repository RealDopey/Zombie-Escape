package com.mc_ze.ZombieEscape;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.ChatColor;

public class Messages {
	Main plugin = Main.getInstance();
	FileConfiguration config = plugin.getConfig();
	ConfigurationSection messages = config.getConfigurationSection("Messages");
	
	public String reservedKick = color(messages.getString("ReservedKick"));
	public String restartingKick = color(messages.getString("RestartingKick"));
	public String countdown = color(messages.getString("Countdown"));
	public String extension = color(messages.getString("MoreTime"));
	public String map = color(messages.getString("Map"));
			
	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
