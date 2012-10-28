package me.Nekoyoubi.Expra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Nekoyoubi {
	
	public static boolean hasPermission(Player player, String permission) { return hasPermission(player, permission, false); }
	public static boolean hasPermission(Player player, String permission, boolean opOnly) { return hasPermission(player, permission, opOnly, player.getWorld().getName()); }
	public static boolean hasPermission(Player player, String permission, boolean opOnly, String world) {
		if (Expra.usePermissions) {
			if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
				boolean inpex = PermissionsEx.getPermissionManager().has(player, permission, world);
				return inpex;
			} else {
				boolean insuper = player.hasPermission(permission);
				return insuper;
			}
		} else if (opOnly) {
			if (player.hasPermission("op")) return true;
			else return false;
		} else {
			return true;
		}
	}

    private static String chatStart = ChatColor.BLUE + "eXPra" + ChatColor.WHITE + ": ";
    
	public static void testMessage(Player player, String message) {
		if (Expra.testing) sendMessage(player, message);
	}
	
    public static void sendMessage(Player player, String message) {
        Nekoyoubi.sendMessage(player, message, false);
    }
	public static void sendMessage(Player player, String message, boolean nextline) {
		if (nextline) {
			if (message.startsWith("<"))
				message = message.substring(1);
			else {
				message = "         "+message;
			}
		}
		else {
			message = chatStart+message;
		}
		if (player == null) {
			Bukkit.broadcastMessage(message.replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		} else {
			player.sendMessage(message
	        	.replaceAll("(&([a-f0-9]))", "\u00A7$2")
	        	.replaceAll("%WORLD%", titleCase(player.getWorld().getName())));
		}
	}
	
	public static Player randomPlayerInWorld(World world) {
		if (world.getPlayers().size() > 0) {
			return world.getPlayers().get(new Random().nextInt(world.getPlayers().size()));
		} else {
			return null;		
		}
	}

	public static String titleCase(String text){
        String result = "";
        for (int i = 0; i < text.length(); i++){
            String next = text.substring(i, i + 1);
            result += (i == 0) ? next.toUpperCase() : next.toLowerCase();
        }
        return result;
    }
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	
	public static long ticksFromSeconds(int seconds) { return seconds*20L; }
	public static long ticksFromMinutes(int minutes) { return minutes*60*20L; }
	public static long ticksFromHours(int hours) { return hours*60*60*20L; }
	public static long ticksFromdays(int days) { return days*24*60*60*20L; }

	public static int getExpToXP(int level, float exp) {
		return (int)(7+Math.floor((double)level*3.5));
	}
	
	public static int getExactXP(int level, float exp) {
		int xp = 0;
		for (int i = 0; i < level; i++) {
			xp += (int)(7+Math.floor((double)i*3.5));
		}
		int xpToLevel = xp;//(int)((float)(3.5 * level) * (float)(level + 1));
		int xpAtLevel = (int)(7+Math.floor((double)level*3.5));
		int xpInExp = (int)((float)xpAtLevel * exp);
		return xpToLevel+xpInExp;
	}
	public static Float levelExpCombo(Player player) {
		return (float)player.getLevel()+player.getExp();
	}
	public static int getExactXP(Player player) {
		return getExactXP(player.getLevel(), player.getExp());
	}
	public static void updateXPDisplay(Player player) {
    	player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(1);						
		player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(-1);
	}
	
	public static int getInt(String number, int def) { try { return Integer.parseInt(number); } catch (NumberFormatException e) { return def; } }
	public static float getFloat(String number, float def) { try { return Float.parseFloat(number); } catch (NumberFormatException e) { return def; } }
	
}
