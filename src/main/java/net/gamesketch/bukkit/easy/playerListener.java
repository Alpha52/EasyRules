package net.gamesketch.bukkit.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class playerListener extends PlayerListener {

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!core.enableRules) { return; }
		File file = new File("plugins/EasyRules/rules.txt");
		
		//check the file
		if (!Rules.checkFile()) { player.sendMessage(ChatColor.RED + "ERROR: Unable to load rules"); return; }
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String str;
			String result = null;
			while ((str = in.readLine()) != null) {
				if (str.startsWith("joinmessage=")) { 
					result = str.replace((CharSequence)"joinmessage=", (CharSequence)"").
	            	replaceAll("@red@", ChatColor.RED + "").
	            	replaceAll("@yellow@", ChatColor.YELLOW + "").
	            	replaceAll("@gold@", ChatColor.GOLD + "").
	            	replaceAll("@green@", ChatColor.GREEN + "").
	            	replaceAll("@darkgreen@", ChatColor.DARK_GREEN + "").
	            	replaceAll("@blue@", ChatColor.BLUE + "").
	            	replaceAll("@darkblue@", ChatColor.DARK_BLUE + "").
	            	replaceAll("@@", ChatColor.WHITE + "").
	            	replaceAll("@gray@", ChatColor.GRAY + "").
	            	replaceAll("@darkgray@", ChatColor.DARK_GRAY + "").
	            	replaceAll("@aqua@", ChatColor.AQUA + "").
	            	replaceAll("@darkaqua@", ChatColor.DARK_AQUA + "").
	            	replaceAll("@black@", ChatColor.BLACK + "").
	            	replaceAll("@darkpurple@", ChatColor.DARK_PURPLE + "").
	            	replaceAll("@darkred@", ChatColor.DARK_RED + "").
	            	replaceAll("@pink@", ChatColor.LIGHT_PURPLE + "");
				}
			}
			if (!result.isEmpty()) {
				player.sendMessage(result);
			}
		} catch (IOException e) { e.printStackTrace(); return; }
	}
	
}
