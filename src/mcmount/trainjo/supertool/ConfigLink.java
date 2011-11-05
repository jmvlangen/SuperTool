package mcmount.trainjo.supertool;

import mcmount.trainjo.supertool.tool.Tool;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

//This class allows easy config reference!!! ;)
public class ConfigLink {
	//Constructor which registers the main class
	public ConfigLink(SuperTool link){
		host = link;
	}
	
	//Returns the maximum size the player size value may be set to
	public static int getMaxSize(){
		return host.configmanager.getGlobalConfig().getMaxSize();
	}
	
	//Returns the maximum volume a selection may be. 
	//Do not use this method, use the isAllowed() method in the selection class instead!
	public static int getMaxVolume(){
		return host.configmanager.getGlobalConfig().getMaxVolume();
	}
	
	//Returns the maximum length the side of a selection may be.
	//Do not use this method, use the isAllowed() method in the selection class instead!
	public static int getMaxLength(){
		return host.configmanager.getGlobalConfig().getMaxLength();
	}
	
	//Returns the id of the object which is the supertool.
	public static int getSuperTool(){
		return host.configmanager.getGlobalConfig().getSuperTool();
	}

	//Used to set the maximum size the player size may bet to value
	public static void setMaxSize(int value){
		host.configmanager.getGlobalConfig().setMaxSize(value);
	}
	
	//Used to set the maximum volume to value (measured in blocks)
	public static void setMaxVolume(int value){
		host.configmanager.getGlobalConfig().setMaxVolume(value);
	}
	
	//Used to set the maximum length a selection may have to value.
	public static void setMaxLength(int value){
		host.configmanager.getGlobalConfig().setMaxLength(value);
	}
	
	//Used to set the id of the supertool to value.
	//Do not use unless there is an important reason.
	public static void setSuperTool(int value){
		host.configmanager.getGlobalConfig().setSuperTool(value);
	}
	
	//Returns the id of the tool in the left hand of a player
	//The parameter player represents the player
	public static int getLefthand(Player player){
		return host.configmanager.getconfig(player).getLefthand();
	}
	
	//Returns the id of the tool in the right hand of a player
	//The parameter player represents the player
	public static int getRighthand(Player player){
		return host.configmanager.getconfig(player).getRighthand();
	}
	
	//Returns a reference to the tool in the left hand of a player
	//The parameter player represents the player
	public static Tool getLeftTool(Player player){
		return host.configmanager.getconfig(player).getLeftTool();
	}
	
	//Returns a reference to the tool in the right hand of a player
	//The parameter player represents the player
	public static Tool getRightTool(Player player){
		return host.configmanager.getconfig(player).getRightTool();
	}
	
	//Sets one corner of a selection to a certain block
	//The parameter player represents the player which selection has to be set
	//The parameter world represents the world the block is in
	//The parameter x is the x-coordinate of the block
	//The parameter y is the y-coordinate of the block
	//The parameter z is the z-coordinate of the block
	//The return value is a string containing a message which can be sent to the player.
	public static String setSelection(Player player, World world, int x, int y, int z){
		return host.configmanager.getconfig(player).setSelection(world, x, y, z);
	}
	
	//Sets one corner of a selection to a certain block
	//The parameter player represents the player which selection has to be set
	//The parameter location is a Location class referring to the the blocks location.
	//The return value is a string containing a message which can be sent to the player.
	public static String setSelection(Player player, Location location){
		return host.configmanager.getconfig(player).setSelection(location);
	}
	
	//Returns a reference to the selection of a player
	//The parameter player is the player.
	public static Selection getSelection(Player player) {
		return host.configmanager.getconfig(player).getSelection();
	}
	
	//Returns a reference to the set material of a player
	//The parameter player is the player.
	public static Material getMaterial(Player player) {
		return host.configmanager.getconfig(player).getMaterial();
	}
	
	//Sets the left tool of a player to a certain tool
	//The parameter player represents the player
	//The parameter tool represents the tool which has to be set.
	//The return value is a string containing a message which can be sent to the player.
	public static String setLeftTool(Player player, Tool tool){
		return host.configmanager.getconfig(player).setLeftTool(tool);
	}
	
	//Sets the right tool of a player to a certain tool
	//The parameter player represents the player
	//The parameter tool represents the tool which has to be set.
	//The return value is a string containing a message which can be sent to the player.
	public static String setRightTool(Player player, Tool tool){
		return host.configmanager.getconfig(player).setRightTool(tool);
	}
	
	//Sets the material of a player to a certain material
	//The parameter player represents the player
	//The parameter material represents the material which has to be set.
	//The return value is a string containing a message which can be sent to the player.
	public static String setMaterial(Player player, Material setmaterial){
		return host.configmanager.getconfig(player).setMaterial(setmaterial);
	}
	
	//Returns the configmanager which keeps track of all the playerconfigs
	public static ConfigManager getConfigManager(){
		return host.configmanager;
	}
	
	//Returns the name of the material of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getMaterialName(Player player){
		return host.configmanager.getconfig(player).getMaterialName();
	}
	
	//Returns the name of the left tool of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getLeftHandName(Player player){
		return host.configmanager.getconfig(player).getLeftHandName();
	}
	
	//Returns the name of the right tool of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getRightHandName(Player player){
		return host.configmanager.getconfig(player).getRightHandName();
	}
	
	//Returns the name of the shape of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getShapeName(Player player){
		return host.configmanager.getconfig(player).getShapeName();
	}
	
	//Returns the name of the material of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getSizeName(Player player){
		return host.configmanager.getconfig(player).getSizeName();
	}
	
	//Returns the name of the toolset of a player
	//The parameter player is the player.
	//The return value is actually a string which can be send to the player
	public static String getToolSetName(Player player){
		return host.configmanager.getconfig(player).getToolSetName();
	}
	
	//Sets the material of a player to a certain material
	//The parameter player represents the player.
	//The parameter setshape represents the shape which has to be set.
	//The return value is a string containing a message which can be sent to the player.
	public static String setShape(Player player, Shape setshape){
		return host.configmanager.getconfig(player).setShape(setshape);
	}
	
	//Returns the shape of a player.
	//The parameter player is the player.
	public static Shape getShape(Player player){
		return host.configmanager.getconfig(player).getShape();
	}
	
	//Sets the size of a player to a certain value
	//The parameter player represents the player.
	//The parameter setsize represents the size which has to be set.
	//The return value is a string containing a message which can be sent to the player.
	public static String setSize(Player player, int setsize){
		return host.configmanager.getconfig(player).setSize(setsize);
	}
	
	//Returns the size of a player.
	//The parameter player is the player.
	public static int getSize(Player player){
		return host.configmanager.getconfig(player).getSize();
	}	

	//All variables
	public static SuperTool host; //A reference to the main class
}
