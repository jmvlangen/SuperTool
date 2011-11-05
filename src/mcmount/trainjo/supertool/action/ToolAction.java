package mcmount.trainjo.supertool.action;

import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;
import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.tehkode.permissions.PermissionManager;

//This class needs to be inherited by the action class for a tool
public abstract class ToolAction {
	//the method called when the tool is used.
	public abstract void run(PlayerInteractEvent event, SuperTool link);
	//needs to return the tool affiliated with this action
	public abstract Tool tool();
	//needs to return the permission (as a string) that is needed to use the tool
	public abstract String permission();
	//returns whether or not the player has the permission returned by the permission() method. (This plugin used permissionex for permissions!)
	public boolean hasPermission(Player player){
		PermissionManager manager = WorldLink.getPermissionManager();
		if(manager == null) return false;
		return manager.has(player, permission());
	}
}
