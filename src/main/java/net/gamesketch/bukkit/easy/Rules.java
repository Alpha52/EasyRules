package net.gamesketch.bukkit.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Rules {
	
	public static boolean checkFile() {
		if (!core.enableRules) { return true; }
		File file = new File("plugins/EasyRules/rules.txt");
		File folder = new File("plugins/EasyRules/");
		if (!file.exists()) { System.out.println("[EasyRules] No rules file found, generating file.");
			try {
				folder.mkdirs();
				file.createNewFile();
			} catch (IOException e) { System.out.println("[EasyRules] Unable to create new rules file."); return false; }
			System.out.println("[EasyRules] rules file made.");
			return true;
		}
		return true;
	}
	
	public static boolean send(Player player, String[] args) {
		if (!core.enableRules) { return true; }
		File file = new File("plugins/EasyRules/rules.txt");
		
		//check the file
		if (!checkFile()) { player.sendMessage(ChatColor.RED + "ERROR: Unable to load rules"); return false; }
		
		BufferedReader in;
		List<String> result;
		
		//get search string
		String search;
		if (args.length < 1) { search = "[main]"; }
		else { search = "[" + args[0].toLowerCase() + "]"; }
		
		//read the file
		try {
			in = new BufferedReader(new FileReader(file));
			String str;
			result = new LinkedList<String>();
			core.rulesPrefix = ChatColor.GREEN + "RULES";
			while ((str = in.readLine()) != null) {
				if (str.startsWith(search)) { 
					result.add(str.replace((CharSequence)search, (CharSequence)"").
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
			            	replaceAll("@pink@", ChatColor.LIGHT_PURPLE + "")
					);
				}
				if (str.startsWith("prefix=")) {
					core.rulesPrefix = str.replace((CharSequence)"prefix=", (CharSequence)"").
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
		} catch (IOException e) { player.sendMessage(ChatColor.RED + "ERROR: Unable to read rules"); return false; }
		//check result empty
		if (result.isEmpty()) { player.sendMessage(ChatColor.RED + "ERROR: No rules found for that category"); return false; }
		
		//send player the messages
		for (String line : result) {
			player.sendMessage("[" + core.rulesPrefix + ChatColor.WHITE + "] " + line);
		}
				
		return true;
		
	}
}
