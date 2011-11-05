package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockSelectAction;

//the select tool
public class SelectTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public SelectTool(){
		super(3,new BlockSelectAction(),"select");
	}
}
