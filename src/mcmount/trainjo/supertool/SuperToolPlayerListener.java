package mcmount.trainjo.supertool;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

//playerlistener to keep track of certain events
public class SuperToolPlayerListener extends PlayerListener {
	
	//constructor registering the main class
	SuperToolPlayerListener(SuperTool link){
		host = link;
	}
	
	//passing the onPlayerIteractEvent to the respective tool (calls the actionhandler to do this)
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getItem() == null) return;
		if(event.getItem().getTypeId() == ConfigLink.getSuperTool()) host.handler.Action(event);
	}
	
	//loads a playerconfig when a player joins the server
	public void onPlayerJoin(PlayerJoinEvent event){
		host.configmanager.loadPlayerConfig(event.getPlayer());
	}
	
	//unloads a playerconfig when a player quits the server
	public void onPlayerQuit(PlayerQuitEvent event){
		host.configmanager.unloadPlayerConfig(event.getPlayer());
	}
	
	//all variables
	private SuperTool host;	//a link to the main class
}
