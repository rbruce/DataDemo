package edu.unca.rbruce.DataDemo;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * This is the main class of the sample plug-in
 */
public class DataDemo extends JavaPlugin {
	/*
	 * This is called when your plug-in is enabled
	 */
	DataDemoLogger logger;

	@Override
	public void onEnable() {
		// save the configuration file
		saveDefaultConfig();

		// Create logger
		logger = new DataDemoLogger(this);

		// Create the SampleListener
		new DataDemoListener(this);

		// set the command executor for sample
		this.getCommand("sample")
				.setExecutor(new DataDemoCommandExecutor(this));
	}

	/*
	 * This is called when your plug-in shuts down
	 */
	@Override
	public void onDisable() {

	}

}
