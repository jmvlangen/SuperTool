package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.toolset.ToolSet;

import org.bukkit.entity.Player;

//a class which responds to the command /toolset
public class ToolSetCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] args, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		if(args.length == 0){
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.getLeftHandName());
			WorldLink.sendMessage(player, config.getRightHandName());
			return true;
		}
		ToolSet toolset = ToolLink.getToolSet(args[0]);
		PlayerConfig config = link.configmanager.getconfig(player);
		if(toolset.lefthand()!=ToolLink.getTool(0)){
			if(!toolset.lefthand().action().hasPermission(player)) WorldLink.sendMessage(player, "You are not allowed to use the left tool");
			else WorldLink.sendMessage(player, config.setLeftTool(toolset.lefthand()));
		}
		else WorldLink.sendMessage(player, "The left tool doesn't exist!");
		if(toolset.righthand()!=ToolLink.getTool(0)){
			if(!toolset.righthand().action().hasPermission(player)) WorldLink.sendMessage(player, "You are not allowed to use the right tool");
			else WorldLink.sendMessage(player, config.setRightTool(toolset.righthand()));
		}
		else WorldLink.sendMessage(player, "The right tool doesn't exist!");
		return true;
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.toolset";
	}
}
