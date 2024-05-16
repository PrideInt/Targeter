package me.prride.commands;

import me.prride.storage.TargetCache;
import me.prride.targeter.Targeter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TargetRemoveCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
		if (strings.length == 2) {
			if (!TargetCache.targets().containsKey(strings[0])) {
				commandSender.sendMessage("§cPlayer not found.");
				return true;
			} else if (TargetCache.targets().containsKey(strings[0]) && !TargetCache.targets().get(strings[0]).contains(strings[1])) {
				commandSender.sendMessage("§cNot in list.");
				return true;
			} else {
				TargetCache.removeTargetsFromCache(strings[0], strings[1]);
				commandSender.sendMessage("§aSuccessfully removed " + strings[1] + " from " + strings[0] + "'s target list.");
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
