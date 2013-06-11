package com.mc_ze.ZombieEscape;

import org.bukkit.Location;
import org.bukkit.World;

public class Util {

	public static String formatLocation(Location loc) {
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		float pitch = loc.getPitch();
		float yaw = loc.getYaw();
		String world = loc.getWorld().getName();
		return x + ", " + y + ", " + z + ", " + pitch + ", " + yaw + ", " + world;
	}

	public static Location parseLocation(String s) {
		String[] parts = s.split(",");
		double x = Double.parseDouble(parts[0]);
		double y = Double.parseDouble(parts[1]);
		double z = Double.parseDouble(parts[2]);
		float pitch = Float.parseFloat(parts[3]);
		float yaw = Float.parseFloat(parts[4]);
		World world = Main.getInstance().getServer().getWorld(parts[5]);
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String convertSec(double sec) {
		double m = Math.floor(sec / 60);
		double s = sec % 60;
		String total;
		if(m == 0) {
			total = s + " seconds";
		}
		else {
			total = m + " minutes and " + s + " seconds";
		}
		return total;
	}
}
