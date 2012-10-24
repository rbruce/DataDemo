package edu.unca.rbruce.DataDemo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class DataDemoCommandExecutor implements CommandExecutor {
	private final DataDemo plugin;

	/*
	 * This command executor needs to know about its plugin from which it came
	 * from
	 */
	public DataDemoCommandExecutor(DataDemo plugin) {
		this.plugin = plugin;
	}

	/*
	 * On command set the sample message
	 */
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + command.getUsage());
			return false;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "you must be logged on to use these commands");
			return false;
		} else if (args[0].equalsIgnoreCase("god")
				&& sender.hasPermission("demo.god")) {
			Player fred = (Player) sender;
			plugin.godMode.put(fred, true);
			sender.sendMessage(ChatColor.RED + fred.getName()
					+ " you are a god now");
			plugin.logger.info(fred.getName() + " has been made a god");
			return true;
		} else if (args[0].equalsIgnoreCase("human")
				&& sender.hasPermission("demo.god")) {
			Player fred = (Player) sender;
			plugin.godMode.put(fred, false);
			sender.sendMessage(ChatColor.RED + fred.getName()
					+ " you are human now");
			plugin.logger.info(fred.getName() + " is no longer a god");
			return true;
		} else if (args[0].equalsIgnoreCase("message")
				&& sender.hasPermission("demo.message")) {
			this.plugin.getConfig().set("sample.message",
					Joiner.on(' ').join(args));
			return true;

		} else {
			return false;
		}
	}

}
