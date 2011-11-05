package mcmount.trainjo.supertool.action;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

//the action performed by the select tool
public class BlockSelectAction extends ToolAction {
	//the action performed
	public void run(PlayerInteractEvent event, SuperTool link) {
		if(!hasPermission(event.getPlayer())){
			WorldLink.sendMessage(event.getPlayer(), "You are not allowed to use this tool");
			return;
		}
		Block block = event.getClickedBlock();
		if(block == null) block = event.getPlayer().getTargetBlock(null, 200);
		if(block == null){
			WorldLink.sendMessage(event.getPlayer(), "You are too far away from a block!");
			return;
		}
		WorldLink.sendMessage(event.getPlayer(), link.configmanager.getconfig(event.getPlayer()).setSelection(block.getLocation()));
	}

	//returns the tool affiliated with this action
	public Tool tool() {
		return ToolLink.getTool("select");
	}

	//returns the permission needed to use this tool
	public String permission() {
		return "supertool.tool.select";
	}

}
