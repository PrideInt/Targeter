package me.prride.targeter;

import me.prride.commands.TargetAddCommand;
import me.prride.commands.TargetRemoveCommand;
import me.prride.storage.TargetCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Targeter extends JavaPlugin {

	public static Targeter instance;
	private Listener listener;

	@Override
	public void onEnable() {
		instance = this;
		listener = new TargeterListener();

		TargetCache.init();

		getLogger().info("Targeter has been enabled!");
		getServer().getPluginManager().registerEvents(listener, this);
		getCommand("targetadd").setExecutor(new TargetAddCommand());
		getCommand("targetremove").setExecutor(new TargetRemoveCommand());
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(listener);
		getLogger().info("Targeter has been disabled!");
	}

	public static List<String> onTabComplete(String[] strings) {
		if (strings.length == 1) {
			return Bukkit.getOnlinePlayers().stream().map(player -> player.getName()).collect(Collectors.toList());
		} else if (strings.length == 2) {
			return Stream.concat(Stream.of("hostile"), Arrays.stream(EntityType.values()).filter(entityType -> entityType.isAlive() && !entityType.name().equals("ARMOR_STAND")).map(entityType -> entityType.name().toLowerCase())).collect(Collectors.toList());
		}
		return null;
	}

	public static List<String> getHostileEntities() {
		return List.of("creeper", "skeleton", "spider", "giant", "zombie", "slime", "ghast", "pig_zombie", "enderman", "cave_spider", "silverfish", "blaze", "magma_cube", "ender_dragon",
				"wither", "witch", "endermite", "guardian", "shulker", "vindicator", "phantom", "husk", "stray", "illusioner", "vex", "evoker", "drowned", "pillager", "ravager", "hoglin", "zoglin",
				"piglin", "piglin_brute", "warden", "elder_guardian", "wither_skeleton");
	}
}
