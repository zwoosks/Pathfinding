package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pathfinding.AStar;

public class CommandManager implements CommandExecutor {
	
	private Pathfinding plugin;
	
	public CommandManager(Pathfinding plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("set1")) {
				Pathfinding.lc1 = player.getLocation();
				player.sendMessage(Utils.chat("Location 1 has been set"));
			} else if (label.equalsIgnoreCase("set2")) {
				Pathfinding.lc2 = player.getLocation();
				player.sendMessage(Utils.chat("Location 2 has been set"));
			} else if (label.equalsIgnoreCase("pathfinding")) {
				if (Pathfinding.lc1 != null) {
					if (Pathfinding.lc2 != null) {
						// Fer
						player.sendMessage(Utils.chat("&b&lFENT..."));
						calculate();
						player.sendMessage(Utils.chat("&a&lACABAT!!!"));
					} else {
						player.sendMessage(Utils.chat("&cLocation 2 isn't set"));
					}
				} else {
					player.sendMessage(Utils.chat("&cAt least Location 1 isn't set"));
				}
			}
		} else {
			sender.sendMessage(Utils.chat("&cYou're not a player lol"));
		}
		return true;
	}
	
	Location[] path;
	Material[] mat;
	Byte[] data;
	@SuppressWarnings("deprecation")
	private void calculate() {
		Location start = Pathfinding.lc1;
		Location end = Pathfinding.lc2;
		
		AStar a = new AStar(start, end, 10000, true, 5);
		path = a.getPath();
		
		mat = new Material[path.length];
		data = new Byte[path.length];
		
		for(int i = 0; i < path.length; i++)
		{
			mat[i] = path[i].getBlock().getType();
			data[i] = path[i].getBlock().getData();
			
			path[i].getBlock().setType(Material.GLASS);
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run()
			{
				resetTest();
			}
		}, 80L);
	}
	
	@SuppressWarnings("deprecation")
	public void resetTest() {
		for(int i = 0; i < path.length; i++)
		{
			path[i].getBlock().setType(mat[i]);
			path[i].getBlock().setData(data[i]);
		}
	}

}
