package net.gamesketch.bukkit.easy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class core extends JavaPlugin
{
	public static boolean enableRules = true;
	
	public void onEnable() {
	    PluginDescriptionFile pdfFile = getDescription();
	    if (!Rules.checkFile()) { System.out.println("Unable to load rules file."); enableRules = false; }
	    
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
