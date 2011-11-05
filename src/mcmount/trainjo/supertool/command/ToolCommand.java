package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.SuperTool;
import mcmount.trainjo.supertool.WorldLink;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;

//a class inherited by all tool handling classes
public abstract class ToolCommand {
	//performed when the class is called sending the additional arguments, the player who performed and a link to the main class
	public abstract boolean call(String[] string, Player player, SuperTool link);
	//The permission needed to perform this command
	public abstract String permission();
	//Checks whether 'player' has the permission returned by permission()
	public boolean hasPermission(Player player){
		PermissionManager manager = WorldLink.getPermissionManager();
		if(manager == null) return false;
		return manager.has(player, permission());
	}
}
