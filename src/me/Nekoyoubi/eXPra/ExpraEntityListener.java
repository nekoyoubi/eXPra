package me.Nekoyoubi.Expra;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class ExpraEntityListener extends EntityListener {
	
	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player player = (Player)entity;
			Expra.playerLevels.put(player.getName(), (double)player.getLevel()+player.getExp());
			Nekoyoubi.testMessage(player, ""+Expra.playerXPDrop);
			if (!Expra.playerXPDrop) event.setDroppedExp(0);
		}
	}
	
}
