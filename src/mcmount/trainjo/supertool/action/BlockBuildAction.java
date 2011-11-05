package mcmount.trainjo.supertool.action;

import mcmount.trainjo.supertool.ConfigLink;
import mcmount.trainjo.supertool.PlayerConfig;
import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.ToolLink;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

//the action performed by the build tool
public class BlockBuildAction extends ToolAction {
	//the action performed
	public void run(PlayerInteractEvent event, SuperTool link) {
		if(!hasPermission(event.getPlayer())){
			WorldLink.sendMessage(event.getPlayer(), "You are not allowed to use this tool");
			return;
		}
		Block block = event.getClickedBlock();
		if(block == null) block = event.getPlayer().getTargetBlock(null, 200);
		if(block == null) WorldLink.sendMessage(event.getPlayer(), "You are too far away from a block!");
		PlayerConfig config = link.configmanager.getconfig(event.getPlayer());
		if(config.getSize() > ConfigLink.getMaxSize()){
			WorldLink.sendMessage(config.getPlayer(), "Your set size is too big. Resetting your size.");
			WorldLink.sendMessage(config.getPlayer(), config.setSize(ConfigLink.getMaxSize()));
			return;
		}
		WorldLink.ChangeArea(config.getShape(), config.getSize(), config.getSize(), config.getSize(), block.getLocation(), config.getMaterial().getId(), event.getPlayer());
	}

	//returns the tool affiliated with this action
	public Tool tool() {
		return ToolLink.getTool("build");
	}

	//returns the permission needed to use this tool
	public String permission() {
		return "supertool.tool.build";
	}

}
