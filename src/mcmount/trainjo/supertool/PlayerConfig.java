package mcmount.trainjo.supertool;

import mcmount.trainjo.supertool.tool.Tool;
import mcmount.trainjo.supertool.toolset.ToolSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

//Represents the settings of a player
public class PlayerConfig {

	//constructor of the class
	//parameter player represents the player the config is for
	//parameter a represents the id of the tool in the left hand
	//parameter b represents the id of the tool in the right hand
	//parameter setmaterial represents the material the player has set
	//parameter link is a link to the configmanager
	public PlayerConfig(Player player, int a, int b, Material setmaterial, ConfigManager link) {
		myPlayer = player;
		lefthand = getTool(a);
		righthand = getTool(b);
		selection = new Selection(null, null);
		material = setmaterial;
		manager = link;
		shape = Shape.CUBE;
		size = 5;
	}
	
	//returns the respective tool for the id 'hand'
	private Tool getTool(int hand) {
		return ToolLink.getTool(hand);
	}

	//returns the player this config is for
	public Player getPlayer() {
		return myPlayer;
	}
	
	//returns the id of the tool set to the left hand
	public int getLefthand(){
		return lefthand.hand();
	}
	
	//returns the id of the tool set to the right hand
	public int getRighthand(){
		return righthand.hand();
	}
	
	//returns the tool set to the left hand
	public Tool getLeftTool(){
		return lefthand;
	}
	
	//returns the tool set to the right hand
	public Tool getRightTool(){
		return righthand;
	}
	
	//sets the corner of the player's selection to a block with coordinates (x,y,z) in world 'world'
	//returns a message which can be send to the player
	public String setSelection(World world, int x, int y, int z){
		String string;
		if(firstselect) string = selection.setStart(new Location(world,x,y,z));
		else string = selection.setEnd(new Location(world,x,y,z));
		firstselect = !firstselect;
		return string;
	}
	
	//sets the corner of the player's selection to the block at location
	//returns a message which can be send to the player
	public String setSelection(Location location){
		String string;
		if(firstselect) string = selection.setStart(location);
		else string = selection.setEnd(location);
		firstselect = !firstselect;
		return string;
	}
	
	//returns the selection set for the player
	public Selection getSelection() {
		return selection;
	}
	
	//returns the material set for a player
	public Material getMaterial() {
		return material;
	}
	
	//sets the player's left tool to 'tool'
	//returns a message which can be send to the player
	public String setLeftTool(Tool tool){
		lefthand = tool;
		saveConfig();
		return (new StringBuilder()).append("The left tool was set to the ").append(tool.toolname()).append(" tool.").toString();
	}
	
	//sets the player's right tool to 'tool'
	//returns a message which can be send to the player
	public String setRightTool(Tool tool){
		righthand = tool;
		saveConfig();
		return (new StringBuilder()).append("The right tool was set to the ").append(tool.toolname()).append(" tool.").toString();
	}
	
	//sets the player's material to 'setmaterial'
	//returns a message which can be send to the player
	public String setMaterial(Material setmaterial){
		material = setmaterial;
		saveConfig();
		return (new StringBuilder().append("Your material has been set to ").append(material.getId()).toString());
	}
	
	//saves the playerconfig
	private void saveConfig(){
		manager.savePlayerConfig(myPlayer, this);
	}
	
	//returns the configmanager
	public ConfigManager getConfigManager(){
		return manager;
	}
	
	//returns a message about the player's material which can be send to the player
	public String getMaterialName(){
		return (new StringBuilder()).append("Your material is ").append(material.getId()).toString();
	}
	
	//returns a message about the player's left hand which can be send to the player
	public String getLeftHandName(){
		return (new StringBuilder()).append("Your left tool is the ").append(lefthand.toolname()).append(" tool.").toString();
	}

	//returns a message about the player's right hand which can be send to the player
	public String getRightHandName(){
		return (new StringBuilder()).append("Your right tool is the ").append(righthand.toolname()).append(" tool.").toString();
	}

	//returns a message about the player's shape which can be send to the player
	public String getShapeName(){
		return (new StringBuilder()).append("Your shape is now ").append(shape.name()).append(" shape.").toString();
	}

	//returns a message about the player's size which can be send to the player
	public String getSizeName(){
		return (new StringBuilder()).append("Your size is now ").append(size).append(".").toString();
	}

	//returns a message about the player's toolset which can be send to the player
	public String getToolSetName(){
		ToolSet toolset = ToolLink.getToolSet(lefthand, righthand);
		if(toolset == null) return "Your toolset is a custom toolset";
		return (new StringBuilder()).append("Your toolset is the ").append(toolset.toolsetname()).append(" toolset.").toString();
	}
	
	//sets the player's shape to 'setshape'
	//returns a message which can be send to the player
	public String setShape(Shape setshape){
		shape = setshape;
		return (new StringBuilder()).append("Your shape was set to a ").append(shape.name()).append(" shape.").toString();
	}
	
	//returns the player's shape
	public Shape getShape(){
		return shape;
	}
	
	//sets the player's size to 'setsize'
	//returns a message which can be send to the player
	public String setSize(int setsize){
		size = setsize;
		return (new StringBuilder()).append("Your size was set to ").append(size).append(".").toString();
	}
	
	//returns the player's size
	public int getSize(){
		return size;
	}
	
	//All variables
	private Player myPlayer;			//a reference to the player
	private Tool lefthand;				//the tool in the player's left hand (used with the left mouse button)
	private Tool righthand;				//the tool in the player's right hand (used with the right mouse button)
	private Selection selection;		//the player's selection
	private boolean firstselect = true;	//which corner of the selection is selected. True if it is the 'first' corner
	private Material material;			//the player's material (used by the swap and build tool as the swap/build material)
	private ConfigManager manager;		//a link to the configmanager
	private Shape shape;				//the player's shape (used by the build tool to determine the shape of the build)
	private int size;					//the player's size (used by the build tool to determine the size of the build)
}
