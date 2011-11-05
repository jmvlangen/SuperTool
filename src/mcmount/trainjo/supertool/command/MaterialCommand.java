package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;

import org.bukkit.Material;
import org.bukkit.entity.Player;

//a class which responds to the command /material
public class MaterialCommand extends ToolCommand {
	//the action performed when called
	public boolean call(String[] string, Player player, SuperTool link) {
		if(!hasPermission(player)){
			WorldLink.sendMessage(player, "You are not allowed to use this command");
			return true;
		}
		if(string.length == 0){
			PlayerConfig config = link.configmanager.getconfig(player);
			WorldLink.sendMessage(player, config.getMaterialName());
			return true;
		}
		Material material = WorldLink.getMaterial(string[0]);
		if(material == null) return false;
		PlayerConfig config = link.configmanager.getconfig(player);
		WorldLink.sendMessage(player , config.setMaterial(WorldLink.getPlaceableBlock(material)));
		return true;
	}

	//the permission needed to perform this command
	public String permission() {
		return "supertool.command.material";
	}

}
