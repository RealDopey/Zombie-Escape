package com.mc_ze.ZombieEscape.Listeners;

import java.util.Stack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.mc_ze.ZombieEscape.Main;

public class PlayerListener implements Listener {
	Stack<String> players = new Stack<String>();
	Main plugin = Main.getInstance();
	Server server = Bukkit.getServer();

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		if(event.getPlayer().hasPermission("zombies.reserved")) {
			if(server.getOnlinePlayers().length >= server.getMaxPlayers()) {
				server.getPlayer(players.pop()).kickPlayer(plugin.messages.reservedKick);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player p = event.getPlayer();
		if(plugin.getStatus().equalsIgnoreCase("lobby")) {
			final Location lobby = plugin.config.getLobby();
			server.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					p.teleport(lobby);
				}
			}, 1);
			// Set to human
		}
		
		else if(plugin.getStatus().equalsIgnoreCase("active")) {
			final Location spawn = plugin.config.getSpawn(plugin.getMap());
			server.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					p.teleport(spawn);
				}
			}, 1);
			// Set to zombie
		}
		
		else {
			p.kickPlayer(plugin.messages.restartingKick);
		}
		
	}
}
