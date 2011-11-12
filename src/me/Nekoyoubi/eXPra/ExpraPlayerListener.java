package me.Nekoyoubi.Expra;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerListener;

public class ExpraPlayerListener extends PlayerListener {
	@Override
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
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
}
