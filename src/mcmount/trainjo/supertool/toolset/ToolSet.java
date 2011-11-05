package mcmount.trainjo.supertool.toolset;

import mcmount.trainjo.supertool.tool.Tool;

//a basic class that needs to be intherited by all toolsets
public class ToolSet {
	//a constructor which needs to be called when constructing a toolset
	//the parameter 'left' is the left tool in this toolset
	//the parameter 'right' is the right tool in this toolset
	//the parameter 'setname' is the name of the toolset
	protected ToolSet(Tool left, Tool right, String setname){
		lefthand = left;
		righthand = right;
		name = setname;
	}
	
	//returns the left tool of this toolset
	public Tool lefthand(){
		return lefthand;
	}
	
	//returns the right tool of this toolset
	public Tool righthand(){
		return righthand;
	}
	
	//returns the name of the toolset
	public String toolsetname(){
		return name;
	}
	
	//all variables
	private Tool lefthand;	//the left tool in this toolset
	private Tool righthand;	//the right tool in this toolset
	private String name;	//the name of this toolset
}
