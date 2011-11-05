package mcmount.trainjo.supertool;

import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.event.player.PlayerInteractEvent;

//This class calls the different ToolAction classes run function
public class ActionHandler {
	//Constructor that registers the reference to the main class
	public ActionHandler(SuperTool link){
		host = link;
	}
	
	//This method is called when an action has to be performed. The class will pass it on to the right ToolAction
	public void Action(PlayerInteractEvent event){
		PlayerConfig config = host.configmanager.getconfig(event.getPlayer());
		if(config == null) config = host.configmanager.loadPlayerConfig(event.getPlayer());
		event.setCancelled(true);
		switch(event.getAction()){
		case LEFT_CLICK_BLOCK:
		case LEFT_CLICK_AIR:
			callAction(event, config.getLeftTool());
			break;
		case RIGHT_CLICK_BLOCK:
		case RIGHT_CLICK_AIR:
			callAction(event, config.getRightTool());
			break;
		default:
			break;
		}
	}

	//calls the run method of a ToolAction
	private void callAction(PlayerInteractEvent event, Tool tool) {
		tool.action().run(event, host);
	}
	
	//All variables
	private SuperTool host; //Reference to the main class
}
