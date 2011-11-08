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
		Entity caught = event.getCaught();
		if (caught != null) {
			Player player = event.getPlayer();
			World world = player.getWorld();
			if (Expra.rando.nextInt(5)==0) {
				ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
				xp.setExperience(1);
			}
		}
	}
}
