package me.Nekoyoubi.Expra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Nekoyoubi {
    private static String chatStart = ChatColor.GOLD + "eXPra" + ChatColor.WHITE + ": ";
    
    public static void sendMessage(Player player, String message) {
        Nekoyoubi.sendMessage(player, message, false);
    }
	public static void sendMessage(Player player, String message, boolean nextline) {
		if (nextline) {
			if (message.startsWith("<"))
				message = message.substring(1);
			else {
				message = "        "+message;
			}
		}
		else {
			message = chatStart+message;
		}
		player.sendMessage(message
        	.replaceAll("(&([a-f0-9]))", "\u00A7$2")
        	.replaceAll("%WORLD%", titleCase(player.getWorld().getName())));
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

}
