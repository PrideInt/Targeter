package me.prride.commands;

import me.prride.storage.TargetCache;
import me.prride.targeter.Targeter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TargetAddCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
		if (strings.length == 2) {
			if (!TargetCache.targets().containsKey(strings[0])) {
				TargetCache.addTargetsToCache(strings[0], strings[1]);
				TargetCache.update();
				commandSender.sendMessage("§aSuccessfully added " + strings[1] + " to " + strings[0] + "'s target list.");
				return true;
			} else if (TargetCache.targets().containsKey(strings[0]) && TargetCache.targets().get(strings[0]).contains(strings[1])) {
				commandSender.sendMessage("§cAlready in list.");
				return true;
			} else {
				TargetCache.addTargetsToCache(strings[0], strings[1]);
				TargetCache.update();
				commandSender.sendMessage("§aSuccessfully added " + strings[1] + " to " + strings[0] + "'s target list.");
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] strings) {
		return Targeter.onTabComplete(strings);
	}
}
