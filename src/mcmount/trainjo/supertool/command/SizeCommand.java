package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.ConfigLink;
import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;

import org.bukkit.entity.Player;

//a class which responds to the command /size
public class SizeCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] string, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		if(string.length == 0){
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.getSizeName());
			return true;
		}
		try{
			int i = Integer.parseInt(string[0]);
			if (i > ConfigLink.getMaxSize()){
				WorldLink.sendMessage(player, "The size you put in is too large. Setting to the maximum size:");
				i = ConfigLink.getMaxSize();
			}
			if (i < 1){
				WorldLink.sendMessage(player, "The size you put in is too small. Setting to 1:");
				i = 1;
			}
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.setSize(i));
			return true;
		}
		catch (NumberFormatException exception){
			return false;
		}
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.size";
	}

}
