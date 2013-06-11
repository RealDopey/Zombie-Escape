package com.mc_ze.ZombieEscape;

import java.util.List;
import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private String status = "lobby";
	private String map = "lobby";
	private static Main instance;
	public Messages messages;
	public ConfigHandler config;
	public MapVoting voting;

	public Main() {
		messages = new Messages();
		config = new ConfigHandler();
		voting = new MapVoting(config.getMapsList());
		runCountdown();
	}

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		runCountdown();
	}

	public String getStatus() {
		return status;
	}

	public static Main getInstance() {
		return instance;
	}

	public String getMap() {
		return map;
	}

	public void runCountdown() {
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			int i = config.getCountdown();
			int min = config.getMinPlayers();
			public void run() {
				if(i % 60 == 0 || i == 30 || i == 10 || i < 5) {
					String time = Util.convertSec((double) i);
					if(i != 0) {
						getServer().broadcastMessage(messages.countdown.replace("{time}", time));
						if(i > 30) voting.broadcastVoteMessage();
						else {
							if(getServer().getOnlinePlayers().length < min) {
								getServer().broadcastMessage(messages.extension);
								i = 60;
							}
							else {
								status = "playing";
								List<String> winners = voting.getWinningMap();
								if(winners.size() == 1) {
									map = winners.get(0);
								}
								else {
									Random r = new Random();
									int pick = r.nextInt(winners.size());
									map = winners.get(pick);
								}
								getServer().broadcastMessage(messages.map.replace("{map}", map));
								// Start game
							}
						}
					}
					i = i - 1;
				}
			}
		}, 0, 20);
	}
}