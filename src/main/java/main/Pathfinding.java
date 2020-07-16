package main;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Pathfinding extends JavaPlugin implements Listener {
	
	public static Location lc1;
	public static Location lc2;
	
	@Override
	public void onEnable() {
		System.out.println("[" + this.getDescription().getName() + "] v" + this.getDescription().getVersion() + " enabled!");
		lc1 = null;
		lc2 = null;
		registerCommands();
	}
	
	@Override
	public void onDisable() {
		System.out.println("[" + this.getDescription().getName() + "] v" + this.getDescription().getVersion() + " disabled!");
	}
	
	private void registerCommands() {
		CommandExecutor ce = new CommandManager(this);
		getCommand("set1").setExecutor(ce);
		getCommand("set2").setExecutor(ce);
		getCommand("pathfinding").setExecutor(ce);
	}
	
}
