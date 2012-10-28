package me.Nekoyoubi.Expra;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;

public class ExpraEntityListener implements Listener {
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player player = (Player)entity;
			float xp = Nekoyoubi.levelExpCombo(player);
			if (Nekoyoubi.hasPermission(player, "expra.death.noloss")) {
				Expra.playerExperience.put(player.getName(), xp);
				event.setDroppedExp(0);
			} else if (Nekoyoubi.hasPermission(player, "expra.death.loss")) {
				float exp = (float)((double)xp * ((float)Expra.deathPercentLoss * .01));
				Expra.playerExperience.put(player.getName(), exp);
				event.setDroppedExp(Nekoyoubi.getExactXP((int)(xp-exp), (xp-exp)%1));
			}
		}
	}
	
	@EventHandler
	public void onEntityTame(EntityTameEvent event) {
		Player player = (Player)event.getOwner();
		if (!Nekoyoubi.hasPermission(player, "expra.award.tame")) return;
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		if (Expra.rando.nextInt(Expra.defaultTamingRatio)==0) {
			ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(Expra.defaultTamingRatio);
		}
	}	
}
