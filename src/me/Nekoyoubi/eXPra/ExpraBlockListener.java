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
		if (			block.getType() == Material.SEEDS ||
						block.getType() == Material.CROPS ||
						block.getType() == Material.MELON_SEEDS ||
						block.getType() == Material.SAPLING) {
			ratio = 3;
		} else if (	block.getType() == Material.CAKE ||
						block.getType() == Material.CAKE_BLOCK) {
			ratio = 1; amount = 10;
		} else if (	block.getType() == Material.SUGAR_CANE ||
						block.getType() == Material.SUGAR_CANE_BLOCK ||
						block.getType() == Material.CACTUS) {
			ratio = 6;
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
		if (			block.getType() == Material.DIAMOND_ORE ||
						block.getType() == Material.LAPIS_ORE ||
						block.getType() == Material.OBSIDIAN) {
			ratio = 1;
			amount = 3;
		} else if (	block.getType() == Material.GOLD_ORE ||
						block.getType() == Material.IRON_ORE) {
			ratio = 2;
			amount = 2;
		} else if (	block.getType() == Material.COAL_ORE ||
						block.getType() == Material.MELON) {
			ratio = 3;
			amount = 1;
		} else if (	block.getType() == Material.SUGAR_CANE ||
						block.getType() == Material.SUGAR_CANE_BLOCK) {
			ratio = 40;
		} else if (	block.getType() == Material.MOB_SPAWNER) {
			ratio = 1;
			amount = 10;
		}
		if (Expra.rando.nextInt(ratio)==0) {
			ExperienceOrb xp = world.spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(amount);
		}
	}
}
