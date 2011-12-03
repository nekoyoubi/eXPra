package me.Nekoyoubi.Expra;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

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
	
	@Override
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		if (Expra.playerLevels.containsKey(player.getName())) {
			Nekoyoubi.testMessage(player, Expra.playerLevels.get(player.getName()).toString());
			double percent = (double)Expra.deathPercentLoss * .01;
			double starting = Expra.playerLevels.get(player.getName());
			final int level = (int)(starting-(starting*percent));
			final float exp = ((float)starting-((float)starting*(float)percent)) % 1;
			player.getServer().getScheduler().scheduleSyncDelayedTask(Expra.plugin, new Runnable() {
			    public void run() {
					player.setLevel(level);
					player.setExp(exp);
					player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(1);
					player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(-1);
			    }
			}, Nekoyoubi.ticksFromSeconds(1));
/*			player.setLevel(level);
			player.setExp(exp);
			player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(1);
			player.getWorld().spawn(player.getLocation(), ExperienceOrb.class).setExperience(-1);
*/		}
	}
}
