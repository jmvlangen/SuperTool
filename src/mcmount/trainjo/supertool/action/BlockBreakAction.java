package mcmount.trainjo.supertool.action;

import mcmount.trainjo.supertool.Selection;
import mcmount.trainjo.supertool.Shape;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

//the action performed by the break tool
public class BlockBreakAction extends ToolAction {
	//the action performed
	public void run(PlayerInteractEvent event, SuperTool link) {
		if(!hasPermission(event.getPlayer())){
			WorldLink.sendMessage(event.getPlayer(), "You are not allowed to use this tool");
			return;
		}
		Block block = event.getClickedBlock();
		if(block == null) block = event.getPlayer().getTargetBlock(null, 200);
		if(block == null) WorldLink.sendMessage(event.getPlayer(), "You are too far away from a block!");
		WorldLink.ChangeArea(Shape.CUBE, new Selection(block.getLocation(), block.getLocation()), 0, event.getPlayer());
	}

	//returns the tool affiliated with this action
	public Tool tool() {
		return ToolLink.getTool("break");
	}
	
	//returns the permission needed to use this tool
	public String permission() {
		return "supertool.tool.break";
	}

}
