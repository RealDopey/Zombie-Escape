package com.mc_ze.ZombieEscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MapVoting {
	
	private HashMap<Integer, String> maps = new HashMap<Integer, String>();
	private HashMap<String, Integer> votes = new HashMap<String, Integer>();
	private HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
	
	public MapVoting(List<String> maps) {
		int i = 0;
		for(String map : maps) {
			this.maps.put(i, map);
		}
	}
	
	public void addVote(String name, int map) {
		votes.put(name, map);
		if(!totals.containsKey(map)) totals.put(map, 0);
		totals.put(map, totals.get(map) + 1);
	}
	
	public void removeVote(String name) {
		int map = votes.get(name);
		votes.remove(name);
		totals.put(map, totals.get(map) - 1);
	}
	
	public List<String> getWinningMap() {
		List<String> winner = new ArrayList<String>();
		int highest = 0;
		for(int i : totals.values()) {
			if(i > highest) {
				highest = i;
			}
		}
		for(int i : totals.keySet()) {
			if(totals.get(i) == highest) {
				String map = maps.get(i);
				winner.add(map);
			}
		}
		return winner;
	}
	
	public void broadcastVoteMessage() {
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Vote on which map to play! Type /map [id] to vote for a map.");
		for(int i : maps.keySet()) {
			Bukkit.getServer().broadcastMessage("" + ChatColor.RED + i + ". " + ChatColor.GREEN + maps.get(i));
		}
	}
}
