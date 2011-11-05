package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.*;

//basic class to be inherited by every tool
public class Tool {
	//constructor to be called when creating the class
	//The parameter hand1 is the id of the tool
	//The parameter action1 is the affiliated action class
	//The parameter name1 is the name of the tool
	protected Tool(int hand1,ToolAction action1,String name1){
		hand = hand1;
		action = action1;
		name = name1;
	}
	
	//returns the id of the tool
	public int hand(){
		return hand;
	}
	
	//returns the action affiliated with the tool
	public ToolAction action(){
		return action;
	}
	
	//returns the name of the tool
	public String toolname(){
		return name;
	}
	
	//all variables
	private int hand;			//the id of the tool
	private ToolAction action;	//the action affiliated with the tool
	private String name;		//the name of the tool
}
