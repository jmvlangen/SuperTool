package mcmount.trainjo.supertool.action;

import mcmount.trainjo.supertool.Selection;
import mcmount.trainjo.supertool.Shape;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

//the action performed by the swap tool
public class BlockSwapAction extends ToolAction {
	//the action performed
	public void run(PlayerInteractEvent event, SuperTool link) {
		if(!hasPermission(event.getPlayer())){
			WorldLink.sendMessage(event.getPlayer(), "You are not allowed to use this tool");
			return;
		}
		Selection selection = link.configmanager.getconfig(event.getPlayer()).getSelection();
		if(!selection.isValid()){
			WorldLink.sendMessage(event.getPlayer(), "You don't have a valid selection");
			return;
		}
		if(!selection.isAllowed()){
			WorldLink.sendMessage(event.getPlayer(), "Your selection is too big");
			return;
		}
		Block block = event.getClickedBlock();
		if(block == null) block = event.getPlayer().getTargetBlock(null, 200);
		if(block == null) WorldLink.sendMessage(event.getPlayer(), "You are too far away from a block!");
		Material material = WorldLink.getPlaceableBlock(block.getType());
		if(material == null){
			material = Material.AIR;
		}
		WorldLink.ChangeArea(Shape.CUBE, selection, material.getId(), event.getPlayer());
	}

	//returns the tool affiliated with this action
	public Tool tool() {
		return ToolLink.getTool("swap");
	}

	//returns the permission needed to use this tool
	public String permission() {
		return "supertool.tool.swap";
	}

}
