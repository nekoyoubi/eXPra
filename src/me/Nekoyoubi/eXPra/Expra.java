package me.Nekoyoubi.Expra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	protected static HashMap<String, String> overrideBreak = new HashMap<String, String>();
	protected static HashMap<String, String> overridePlace = new HashMap<String, String>();
	
	protected static List<String> disabledWorlds = new ArrayList<String>();
	protected static List<String> disabledPlayers = new ArrayList<String>();
	
	protected static Integer defaultBlockPlaceAmount = 1;
	protected static Integer defaultBlockPlaceRatio = 20;
	protected static Integer defaultBlockBreakAmount = 1;
	protected static Integer defaultBlockBreakRatio = 20;
	protected static Integer defaultFishingAmount = 1;
	protected static Integer defaultFishingRatio = 1;
	
	public static Random rando;
	
	@Override
	public void onDisable() {
		System.out.println(this + " is now disabled.");
	}

	@Override
	public void onEnable() {
		loadConfiguration();
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
	
    public void loadConfiguration() {
     	//reloadConfig();
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    	List<String> overbreaks = null;
    	List<String> overplaces = null;
    	if (getConfig().contains("disabled-worlds")) {
    		disabledWorlds = Nekoyoubi.castList(String.class, getConfig().getList("disabled-worlds"));
    	}
    	if (getConfig().contains("disabled-players")) {
    		disabledPlayers = Nekoyoubi.castList(String.class, getConfig().getList("disabled-players"));
    	}
    	String defaultPlace = getConfig().getString("defaults.block-place", "1@20");
    	String defaultBreak = getConfig().getString("defaults.block-break", "1@20");
    	String defaultFishing = getConfig().getString("defaults.fishing", "1@1");
    	defaultBlockPlaceAmount = Integer.parseInt(defaultPlace.split("@")[0]);
    	defaultBlockPlaceRatio = Integer.parseInt(defaultPlace.split("@")[1]);
     	defaultBlockBreakAmount = Integer.parseInt(defaultBreak.split("@")[0]);
    	defaultBlockBreakRatio = Integer.parseInt(defaultBreak.split("@")[1]);
     	defaultFishingAmount = Integer.parseInt(defaultFishing.split("@")[0]);
    	defaultFishingRatio = Integer.parseInt(defaultFishing.split("@")[1]);
   	
    	if (getConfig().contains("overrides.block-break")) {
    		overbreaks = Nekoyoubi.castList(String.class, getConfig().getList("overrides.block-break"));
	    	for (String overbreak : overbreaks) {
				String block = overbreak.split("=")[0];
				String reward = overbreak.split("=")[1];
	    		overrideBreak.put(block, reward);
			}
    	}
    	if (getConfig().contains("overrides.block-place")) {
    		overplaces = Nekoyoubi.castList(String.class, getConfig().getList("overrides.block-place"));
	    	for (String overplace : overplaces) {
				String block = overplace.split("=")[0];
				String reward = overplace.split("=")[1];
	    		overridePlace.put(block, reward);
			}
    	}
    }

}