package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockBreakAction;

//the break tool
public class BreakTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public BreakTool(){
		super(1, new BlockBreakAction(), "break");
	}
}
