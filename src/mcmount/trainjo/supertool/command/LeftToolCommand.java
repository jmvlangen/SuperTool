package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;
import org.bukkit.entity.Player;

//a class which responds to the command /lefttool
public class LeftToolCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] string, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		if(string.length == 0){
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.getLeftHandName());
			return true;
		}
		Tool tool = ToolLink.getTool(string[0]);
		if(tool == ToolLink.getTool(0)){
			WorldLink.sendMessage(player, "No such tool exists!");
			return true;
		}
		if(!tool.action().hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use that tool");
			return true;
		}
		PlayerConfig config = link.configmanager.getconfig(player);
		WorldLink.sendMessage(player, config.setLeftTool(tool));	
		return true;
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.lefttool";
	}

}
