package mcmount.trainjo.supertool.action;

import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

//the action performed by the get tool
public class BlockGetAction extends ToolAction{
	//the action performed
	public void run(PlayerInteractEvent event, SuperTool link) {
		if(!hasPermission(event.getPlayer())){
			WorldLink.sendMessage(event.getPlayer(), "You are not allowed to use this tool");
			return;
		}
		Block block = event.getClickedBlock();
		if(block == null) block = event.getPlayer().getTargetBlock(null, 200);
		if(block == null) WorldLink.sendMessage(event.getPlayer(), "You are too far away from a block!");
		Material material = WorldLink.getInventoryItem(block);
		if (material != null) WorldLink.givePlayer(event.getPlayer(),material,1);
		else WorldLink.sendMessage(event.getPlayer(), "You selected an invalid block");
	}
	
	//returns the tool affiliated with this action
	public Tool tool(){
		return ToolLink.getTool("get");
	}

	//returns the permission needed to use this tool
	public String permission() {
		return "supertool.tool.get";
	}

}
