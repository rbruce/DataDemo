package edu.unca.rbruce.DataDemo;

import java.text.MessageFormat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/*
 * This is a sample event listener
 */
public class DataDemoListener implements Listener {
	private final DataDemo plugin;

	/*
	 * This listener needs to know about the plugin which it came from
	 */
	public DataDemoListener(DataDemo plugin) {
		// Register the listener
		plugin.getServer().getPluginManager().registerEvents(this, plugin);

		this.plugin = plugin;
	}

	/*
	 * Send the sample message to all players that join
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		plugin.setMetadata(event.getPlayer(), "god", false, plugin);
		event.getPlayer().sendMessage(
				this.plugin.getConfig().getString("sample.message"));

	}

	/*
	 * Another example of a event handler. This one will give you the name of
	 * the entity you interact with, if it is a Creature it will give you the
	 * creature Id.
	 */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent event) {
		final EntityType entityType = event.getRightClicked().getType();
		event.getPlayer().sendMessage(
				MessageFormat.format(
						"You interacted with a {0} it has an id of {1}",
						entityType.getName(), entityType.getTypeId()));
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void demoEvent(PlayerInteractEvent event) {
		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if ((Boolean) plugin.getMetadata(event.getPlayer(), "god", plugin)) {
				Block b = event.getClickedBlock();
				if (b != null) {
					Location loc = b.getLocation();
					loc.getWorld().strikeLightning(loc);
					b.setType(Material.FIRE);
					event.getPlayer().sendMessage("you are dangerous");
					plugin.logger.info(event.getPlayer()
							+ " is blowing things up");
				}
			}
		}
	}
}
