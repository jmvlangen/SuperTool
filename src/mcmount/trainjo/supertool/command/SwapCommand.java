package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.Selection;
import mcmount.trainjo.supertool.Shape;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;

import org.bukkit.Material;
import org.bukkit.entity.Player;

//a class which responds to the command /swap
public class SwapCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] string, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		PlayerConfig config = link.configmanager.getconfig(player);
		Selection selection = config.getSelection();
		Material material;
		if(string.length == 0) material = config.getMaterial();
		else material = WorldLink.getMaterial(string[0]);
		if(!selection.isValid()){
			WorldLink.sendMessage(player, "You don't have a valid selection");
			return true;
		}
		if(material == null){
			material = Material.AIR;
		}
		WorldLink.ChangeArea(Shape.CUBE, selection, material.getId(), player);
		return true;
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.swap";
	}

}
