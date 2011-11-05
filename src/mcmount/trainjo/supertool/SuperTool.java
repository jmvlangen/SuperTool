package mcmount.trainjo.supertool;

import java.util.logging.Logger;

import mcmount.trainjo.supertool.command.SuperToolCommands;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

//main class of the plugin
public class SuperTool extends JavaPlugin {

	//actions to be performed when the plugin is disabled
	public void onDisable() {
		Logger log = Logger.getLogger("Minecraft");
		log.info("Supertool has been disabled");
		toollink.save();
	}

	//actions to be performed when the plugin is enabled
	public void onEnable() {
		Logger log = Logger.getLogger("Minecraft");
		log.info("Supertool has been activated");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		toollink = new ToolLink(this);
	}
	
	//hook to the bukkit command system
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		return commands.onCommand(sender, command, label, args);
	}

	//all variables
	private final SuperToolPlayerListener playerListener = new SuperToolPlayerListener(this);	//playerlistener
	public final ActionHandler handler = new ActionHandler(this);								//actionhandler
	public final ConfigManager configmanager = new ConfigManager(this);							//configmanager
	public final SuperToolCommands commands = new SuperToolCommands(this);						//commandhandler
	public final ConfigLink configlink = new ConfigLink(this);									//configlink class (has to be initialized)
	public ToolLink toollink;																	//toollink class (has to be initialized)
}
