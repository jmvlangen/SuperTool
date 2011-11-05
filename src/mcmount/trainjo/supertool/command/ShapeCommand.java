package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.Shape;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;

import org.bukkit.entity.Player;

//a class which responds to the command /shape
public class ShapeCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] string, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		if(string.length == 0){
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.getShapeName());
			return true;
		}
		PlayerConfig config = link.configmanager.getconfig(player);
		if(string[0].equalsIgnoreCase("cube")){
			WorldLink.sendMessage(player, config.setShape(Shape.CUBE));
			return true;
		}
		if(string[0].equalsIgnoreCase("circle")){
			WorldLink.sendMessage(player, config.setShape(Shape.CIRCLE));
			return true;
		}
		if(string[0].equalsIgnoreCase("cilinder")){
			WorldLink.sendMessage(player, config.setShape(Shape.CILINDER));
			return true;
		}
		return false;
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.shape";
	}

}
