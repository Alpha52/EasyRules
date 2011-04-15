package net.gamesketch.bukkit.easy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class core extends JavaPlugin
{
	public static boolean enableRules = true;
	public static String rulesPrefix = ChatColor.GREEN + "RULES";
	final PlayerListener playerListener = new playerListener();
	
	public void onEnable() {
	    PluginDescriptionFile pdfFile = getDescription();
	    if (!Rules.checkFile()) { System.out.println("Unable to load rules file."); enableRules = false; }
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
	    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	public void onDisable() {

	}
	
	  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	  {
	    String commandName = command.getName().toLowerCase();
	    if (commandName.equals("rules")) {
	    	if (!enableRules) { return true; }
	    	if (!Rules.send((Player)sender, args)) { return false; }
	    	return true;
	    }
	    
	    
	    return true;
	  }
}
