package me.Nekoyoubi.Expra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Expra extends JavaPlugin {

	protected static boolean testing = false;
	protected static Expra plugin;
	
	private ExpraBlockListener blockListener = new ExpraBlockListener();
	private ExpraPlayerListener playerListener = new ExpraPlayerListener();
	private ExpraEntityListener entityListener = new ExpraEntityListener();
	
	protected static HashMap<String, String> overrideBreak = new HashMap<String, String>();
	protected static HashMap<String, String> overridePlace = new HashMap<String, String>();
	
	protected static HashMap<String, Location> playerLit = new HashMap<String, Location>();
	protected static HashMap<String, Float> playerExperience = new HashMap<String, Float>();
	
	protected static List<String> disabledWorlds = new ArrayList<String>();
	protected static List<String> disabledPlayers = new ArrayList<String>();
	
	protected static Integer deathPercentLoss = 0;
	protected static boolean playerXPDrop = true;
	protected static boolean usePermissions = false;;
	
	protected static Integer defaultBlockPlaceAmount = 1;
	protected static Integer defaultBlockPlaceRatio = 20;
	protected static Integer defaultBlockBreakAmount = 1;
	protected static Integer defaultBlockBreakRatio = 20;
	protected static Integer defaultFishingAmount = 1;
	protected static Integer defaultFishingRatio = 1;
	protected static Integer defaultLightingAmount = 1;
	protected static Integer defaultLightingRatio = 10;
	protected static Integer defaultTamingAmount = 5;
	protected static Integer defaultTamingRatio = 1;
	protected static Integer defaultBreedingAmount = 3;
	protected static Integer defaultBreedingRatio = 2;
	
	public static Random rando;
	
	@Override
	public void onDisable() {
		System.out.println(this + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		loadConfiguration();
		plugin = this;
		rando = new Random();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.playerListener, this);
        pm.registerEvents(this.blockListener, this);
        pm.registerEvents(this.entityListener, this);
		System.out.println(this + " is now enabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	Player player = (sender instanceof Player) ? (Player)sender : null;
    	if(command.getName().equalsIgnoreCase("expra") && player != null) {
    		if (args.length == 0) {
	    		Nekoyoubi.sendMessage(player, "Level: &b"+player.getLevel()+" &f(&6"+Math.round(player.getExp()*100f)+"%&f)");
	    		return true;
    		} else if (args[0].equalsIgnoreCase("sell")) {
    			if (!Nekoyoubi.hasPermission(player, "expra.sell", false)) {
    				Nekoyoubi.sendMessage(player, "You do not have access to that command.");
    				return true;
    			} else {
    				ItemStack item = player.getItemInHand();
    				if (item == null) {
    					Nekoyoubi.sendMessage(player, "You do not currently have an item selected.");
    					return true;
    				}
    				PlayerInventory inv = player.getInventory();
    				int amount = (args[1].equalsIgnoreCase("all") ? -1 : Nekoyoubi.getInt(args[1],1));
    				ItemStack toRemove = new ItemStack(item.getType(), amount, item.getData().getData());
    				if (amount == -1) {
    					inv.removeItem(toRemove);
    				}
    				
    			}
    		} else if (
    				args[0].equalsIgnoreCase("adjust")||
    				args[0].equalsIgnoreCase("adj")||
    				args[0].equalsIgnoreCase("a")||
    				args[0].equalsIgnoreCase("add")||
    				args[0].equalsIgnoreCase("set")||
    				args[0].equalsIgnoreCase("s")) {
    			if (!Nekoyoubi.hasPermission(player, "expra.adjust", true)) {
    				Nekoyoubi.sendMessage(player, "You do not have access to that command.");
    				return true;
    			}
    			int levels = 0;
    			Player target = player;
    			if (args.length == 2) {
	    			if (Pattern.matches("^-?\\d+$", args[1])) {
	    				levels = Integer.parseInt(args[1]);
	    			} else if (getServer().getPlayer(args[1]) != null) {
	    				target = getServer().getPlayer(args[1]);
	    			}
    			} else if (args.length == 3) {
	    			if (Pattern.matches("^-?\\d+$", args[1])) {
	    				levels = Integer.parseInt(args[1]);
	    				if (getServer().getPlayer(args[2]) != null) target = getServer().getPlayer(args[2]);
	    			} else if (getServer().getPlayer(args[1]) != null) {
	    				target = getServer().getPlayer(args[1]);
	    				if (Pattern.matches("^-?\\d+$", args[2])) levels = Integer.parseInt(args[2]);
	    			}
    			}
    			if (args[0].equalsIgnoreCase("set")||args[0].equalsIgnoreCase("s")) {
    				target.setLevel(levels);
    			} else {
    				target.setLevel(levels+target.getLevel() < 0 ? 0 : levels+target.getLevel());
    			}
    			target.setExp(0);
    			target.getWorld().spawn(target.getLocation(), ExperienceOrb.class).setExperience(1);
    			target.getWorld().spawn(target.getLocation(), ExperienceOrb.class).setExperience(-1);
    			if (target == player) {
		    		Nekoyoubi.sendMessage(player, "You set your level to &b"+target.getLevel()+"&f.");	
    			} else {
		    		Nekoyoubi.sendMessage(player, "You set the level of &6"+target.getName()+" to &b"+target.getLevel()+"&f.");
    			}
    			return true;
    		}
    	}
    	return false;
	}
	
    public void loadConfiguration() {
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
	   	deathPercentLoss = getConfig().getInt("death-percent-loss", 0);
	   	playerXPDrop = getConfig().getBoolean("player-xp-drop", true);
	   	usePermissions = getConfig().getBoolean("use-permissions", false);
    	String defaultPlace = getConfig().getString("defaults.block-place", "1@20");
    	String defaultBreak = getConfig().getString("defaults.block-break", "1@20");
    	String defaultFishing = getConfig().getString("defaults.fishing", "1@1");
    	String defaultLighting = getConfig().getString("defaults.lighting", "1@20");
    	String defaultTaming = getConfig().getString("defaults.taming", "5@1");
    	String defaultBreeding = getConfig().getString("defaults.breeding", "5@1");
     	defaultBlockPlaceAmount = Integer.parseInt(defaultPlace.split("@")[0]);
    	defaultBlockPlaceRatio = Integer.parseInt(defaultPlace.split("@")[1]);
     	defaultBlockBreakAmount = Integer.parseInt(defaultBreak.split("@")[0]);
    	defaultBlockBreakRatio = Integer.parseInt(defaultBreak.split("@")[1]);
     	defaultFishingAmount = Integer.parseInt(defaultFishing.split("@")[0]);
    	defaultFishingRatio = Integer.parseInt(defaultFishing.split("@")[1]);
     	defaultLightingAmount = Integer.parseInt(defaultLighting.split("@")[0]);
    	defaultLightingRatio = Integer.parseInt(defaultLighting.split("@")[1]);
     	defaultTamingAmount = Integer.parseInt(defaultTaming.split("@")[0]);
    	defaultTamingRatio = Integer.parseInt(defaultTaming.split("@")[1]);
     	defaultBreedingAmount = Integer.parseInt(defaultBreeding.split("@")[0]);
    	defaultBreedingRatio = Integer.parseInt(defaultBreeding.split("@")[1]);
   	
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
    	
    	Nekoyoubi.testMessage(null, "config loaded");
    }
}