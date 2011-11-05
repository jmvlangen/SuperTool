package mcmount.trainjo.supertool;

//This class keeps track of settings which are global
public class GlobalConfig {
	
	//constructor registering the main class and the configmanager
	public GlobalConfig(SuperTool link, ConfigManager link2) {
		host = link;
		manager = link2;
	}
	
	//sets the maximum size a player may have to value
	public void setMaxSize(int value){
		maxsize = value;
	}
	
	//sets the maximum volume of a selection to value
	public void setMaxVolume(int value){
		maxvolume = value;
	}
	
	//sets the maximum length of a selection to value
	public void setMaxLength(int value){
		maxlength = value;
	}
	
	//sets the id of the item which is the supertool to value
	public void setSuperTool(int value){
		supertool = value;
	}
	
	//returns the maximum size a player may have
	public int getMaxSize(){
		return maxsize;
	}
	
	//returns the maximum volume of a selection
	public int getMaxVolume(){
		return maxvolume;
	}
	
	//returns the maximum length of a selection
	public int getMaxLength(){
		return maxlength;
	}
	
	//returns the id of the item which is the supertool
	public int getSuperTool(){
		return supertool;
	}
	
	//all variables
	private SuperTool host;			//a link to the main class
	private ConfigManager manager;	//a link to the manager
	private int maxsize;			//the maximum size a player may have
	private int maxvolume;			//the maximum volume a selection may be
	private int maxlength;			//the maximum length a selection may be
	private int supertool;			//the id of the item which is the supertool
}
