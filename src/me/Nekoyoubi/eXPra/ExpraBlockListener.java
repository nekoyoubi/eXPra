package me.Nekoyoubi.Expra;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ExpraBlockListener extends BlockListener {

	@Override
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		Block block = event.getBlock();
		Integer amount = 1;
		Integer ratio = 20;
		switch (block.getType()) {
			case SEEDS :
			case CROPS :
			case MELON_SEEDS :
			case SAPLING :
				ratio = 3;
				break;
			case CAKE :
			case CAKE_BLOCK :
				ratio = 1;
				amount = 10;
				break;
			case SUGAR_CANE_BLOCK :
			case CACTUS :
				ratio = 6;
				break;
			default: break;
		}
		if (Expra.rando.nextInt(ratio)==0) {
			ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(amount);
		}
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		Block block = event.getBlock();
		Integer amount = 1;
		Integer ratio = 20;
		switch (block.getType()) {
			case DIAMOND_ORE :
			case LAPIS_ORE :
			case OBSIDIAN :
				ratio = 1;
				amount = 3;
				break;
			case GOLD_ORE :
			case IRON_ORE :
				ratio = 2;
				amount = 2;
				break;
			case COAL_ORE :
			case MELON :
				ratio = 3;
				break;
			case SUGAR_CANE_BLOCK :
				ratio = 40;
				break;
			case MOB_SPAWNER :
				ratio = 1;
				amount = 10;
				break;
			default: break;
		}
		if (Expra.rando.nextInt(ratio)==0) {
			ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(amount);
		}
	}
}
