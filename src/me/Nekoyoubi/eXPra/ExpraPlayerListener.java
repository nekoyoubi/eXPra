package me.Nekoyoubi.Expra;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.entity.CraftChicken;
import org.bukkit.craftbukkit.entity.CraftCow;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftPig;
import org.bukkit.craftbukkit.entity.CraftSheep;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

@SuppressWarnings("unused")
public class ExpraPlayerListener extends PlayerListener {

	@Override
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
	
	@Override
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		if (Expra.playerExperience.containsKey(player.getName())) {
			final float exp = Expra.playerExperience.get(player.getName());
			player.getServer().getScheduler().scheduleSyncDelayedTask(Expra.plugin, new Runnable() {
			    public void run() {
			    	//Nekoyoubi.testMessage(player, "(respawn) "+exp);
			    	player.setLevel((int)Math.floor(exp));
			    	player.setExp(exp%1);
					Nekoyoubi.updateXPDisplay(player);
			    }
			}, 10L);
		}
	}
	
	@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (true) return; //Looking to add breeding, but it's not happening this go around.
		Nekoyoubi.testMessage(null, event.getRightClicked().toString());
		Player player = event.getPlayer();
		if (!Nekoyoubi.hasPermission(player, "expra.award.breed")) return;
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		CraftEntity entity = (CraftEntity)event.getRightClicked();
		if (player.getItemInHand().getType() == Material.WHEAT &&
				entity instanceof CraftChicken ||
				entity instanceof CraftCow ||
				entity instanceof CraftPig ||
				entity instanceof CraftSheep) {
			//((CraftCow)entity).
		}
	}
}
