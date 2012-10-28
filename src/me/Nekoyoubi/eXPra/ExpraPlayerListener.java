package me.Nekoyoubi.Expra;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ExpraPlayerListener implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (!Nekoyoubi.hasPermission(player, "expra.award.fish")) return;
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		Entity caught = event.getCaught();
		if (caught != null) {
			if (Expra.rando.nextInt(Expra.defaultFishingRatio)==0) {
				ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
				xp.setExperience(Expra.defaultFishingRatio);
			}
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		if (Expra.playerExperience.containsKey(player.getName())) {
			final float exp = Expra.playerExperience.get(player.getName());
			player.getServer().getScheduler().scheduleSyncDelayedTask(Expra.plugin, new Runnable() {
			    public void run() {
			    	player.setLevel((int)Math.floor(exp));
			    	player.setExp(exp%1);
					Nekoyoubi.updateXPDisplay(player);
			    }
			}, 10L);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (true) return; //Looking to add breeding, but it's not happening this go around.
		Nekoyoubi.testMessage(null, event.getRightClicked().toString());
		Player player = event.getPlayer();
		if (!Nekoyoubi.hasPermission(player, "expra.award.breed")) return;
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		Entity entity = event.getRightClicked();
		if (player.getItemInHand().getType() == Material.WHEAT &&
                entity instanceof Chicken ||
   				entity instanceof Cow ||
				entity instanceof Pig ||
				entity instanceof Sheep) {
		}
	}
}
