package mcmount.trainjo.supertool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

//This class takes care of the configs.
public class ConfigManager {
	//constructor registering the main class.
	public ConfigManager(SuperTool link){
		host = link;
	}
	
	//Initializing the config. Done when one of the methods of this class is called and the class isn't initialized.
	public void initConfig(){
		hasInit = true;
		if(!host.getDataFolder().exists()) host.getDataFolder().mkdirs();
		bukkitconfig = new Configuration(new File(host.getDataFolder(), "config.yml"));
		bukkitconfig.load();
		initPlayers();
		initGlobal();
	}
	
	//Setting the global variables to the ones in the config or the default ones.
	private void initGlobal() {
		globalconfig = new GlobalConfig(host, this);
		globalconfig.setMaxSize(bukkitconfig.getInt("global.maxsize", 10));
		globalconfig.setMaxVolume(bukkitconfig.getInt("global.maxvolume", 20000));
		globalconfig.setMaxLength(bukkitconfig.getInt("global.maxlength", 30));
		globalconfig.setSuperTool(bukkitconfig.getInt("global.supertool.id", 294));
		bukkitconfig.save();
	}
	
	//returns a reference to the globalconfig
	public GlobalConfig getGlobalConfig(){
		return globalconfig;
	}
	
	//loads all playerconfigs for the players online when this function is called.
	public void initPlayers(){
		playerconfig.clear();
		Player player[] = host.getServer().getOnlinePlayers();
		for (int i = 0;i < player.length;i++) loadPlayerConfig(player[i]);
	}
	
	//loads the config for a player returning the config loaded.
	public PlayerConfig loadPlayerConfig(Player player) {
		if(!hasInit) initConfig();
		int a,b;
		a = bukkitconfig.getInt((new StringBuilder().append(player.getName()).append(".lefthand").toString()), 0);
		b = bukkitconfig.getInt((new StringBuilder().append(player.getName()).append(".righthand").toString()), 0);
		Material material = Material.getMaterial(bukkitconfig.getInt((new StringBuilder().append(player.getName()).append(".material").toString()), 0));
		PlayerConfig pc = new PlayerConfig(player, a, b, material, this);
		playerconfig.add(pc);
		return pc;
	}
	
	//returns the config of a player.
	public PlayerConfig getconfig(Player player){
		if(!hasInit) initConfig();
		if(!hasInit) initConfig();
		for(int i = 0;i < playerconfig.size();i++){
			if(playerconfig.get(i).getPlayer() == player) return playerconfig.get(i);
		}
		return null;
	}
	
	//unloads a player config. Use this function only if the player isn't online anymore!!!
	public void unloadPlayerConfig(Player player) {
		if(!hasInit) initConfig();
		savePlayerConfig(player);
		for(int i = 0;i < playerconfig.size();i++){
			if(playerconfig.get(i).getPlayer() == player){
				playerconfig.remove(i);
				break;
			}
		}
	}

	//saves the playerconfig of a specific player.
	public void savePlayerConfig(Player player) {
		if(!hasInit) initConfig();
		for(int i = 0;i < playerconfig.size();i++){
			if(playerconfig.get(i).getPlayer() == player){
				savePlayerConfig(player,playerconfig.get(i));
				break;
			}
		}
	}

	//saves the playerconfig of a player using the player and the playerconfig as parameters. Returns true if saved, false if not
	public boolean savePlayerConfig(Player player, PlayerConfig pc) {
		if(!hasInit) initConfig();
		if(pc.getPlayer()!=player) return false;
		String leftstring = (new StringBuilder()).append(player.getName()).append(".lefthand").toString();
		String rightstring = (new StringBuilder()).append(player.getName()).append(".righthand").toString();
		String materialstring = (new StringBuilder()).append(player.getName()).append(".material").toString();
		bukkitconfig.setProperty(leftstring, pc.getLefthand());
		bukkitconfig.setProperty(rightstring, pc.getRighthand());
		bukkitconfig.setProperty(materialstring, pc.getMaterial().getId());
		bukkitconfig.save();
		return true;
	}
	
	//reloads all configs
	public void reload(){
		if(!hasInit) initConfig();
		bukkitconfig.load();
		initPlayers();
		initGlobal();
	}

	//All variables
	private boolean hasInit = false;											//whether the config has been initialized
	private Configuration bukkitconfig;											//a link to the bukkitconfig file
	private SuperTool host;														//a link to the main class
	private List<PlayerConfig> playerconfig = new ArrayList<PlayerConfig>();	//a list of all playerconfigs
	private GlobalConfig globalconfig;											//a link to the globalconfig
}
