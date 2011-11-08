package me.Nekoyoubi.Expra;

import org.bukkit.Material;
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
			case Material.SEEDS :
			case Material.CROPS :
			case Material.MELON_SEEDS :
			case Material.SAPLING :
				ratio = 3;
				break;
			case Material.CAKE :
			case Material.CAKE_BLOCK :
				ratio = 1;
				amount = 10;
				break;
			case Material.SUGAR_CANE_BLOCK :
			case Material.CACTUS :
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
			case Material.DIAMOND_ORE :
			case Material.LAPIS_ORE :
			case Material.OBSIDIAN :
				ratio = 1;
				amount = 3;
				break;
			case Material.GOLD_ORE :
			case Material.IRON_ORE :
				ratio = 2;
				amount = 2;
				break;
			case Material.COAL_ORE :
			case Material.MELON :
				ratio = 3;
				break;
			case Material.SUGAR_CANE_BLOCK :
				ratio = 40;
				break;
			case Material.MOB_SPAWNER :
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
