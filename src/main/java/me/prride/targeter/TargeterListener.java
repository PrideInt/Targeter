package me.prride.targeter;

import me.prride.storage.TargetCache;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.Set;

public class TargeterListener implements Listener {

	@EventHandler
	public void onTarget(EntityTargetLivingEntityEvent event) {
		if (event.getTarget() == null) {
			return;
		} else if (!(event.getTarget() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getTarget();
		Entity entity = event.getEntity();

		if (TargetCache.targets().containsKey(player.getName())) {
			Set<String> targets = TargetCache.targets().get(player.getName());

			if (targets.isEmpty()) {
				return;
			} else {
				if (targets.contains("hostile")) {
					for (String hostile : Targeter.getHostileEntities()) {
						if (entity.getType().name().toLowerCase().equals(hostile)) {
							event.setCancelled(true);
						}
					}
				} else {
					if (targets.contains(entity.getType().name().toLowerCase())) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
