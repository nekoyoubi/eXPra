package me.Nekoyoubi.Expra;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Expra extends JavaPlugin {

	private ExpraBlockListener blockListener = new ExpraBlockListener();
	private ExpraPlayerListener playerListener = new ExpraPlayerListener();

	public static Random rando;
	
	@Override
	public void onDisable() {
		System.out.println(this + " is now disabled.");
	}

	@Override
	public void onEnable() {
		//loadConfiguration();
		rando = new Random();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.BLOCK_PLACE, blockListener, Priority.Low, this);
        pm.registerEvent(Type.BLOCK_BREAK, blockListener, Priority.Low, this);
        pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Low, this);
        pm.registerEvent(Type.PLAYER_FISH, playerListener, Priority.Low, this);
		System.out.println(this + " is now enabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	Player player = (sender instanceof Player) ? (Player)sender : null;
    	if(command.getName().equalsIgnoreCase("expra") && player != null) {
    		if (args.length == 0) {
				Integer level = player.getLevel();
				Integer xp = player.getExperience();
				Integer levelxp = level*10;
	    		Nekoyoubi.sendMessage(player, "Level: &b"+level);
	    		Nekoyoubi.sendMessage(player, "Progress: &a"+xp+"&f of &c"+levelxp+"&f (&6"+Math.round(((double)xp/(double)levelxp)*100)+"%&f)", true);
	    		return true;
    		}
    	} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
    	return false;
	}
}