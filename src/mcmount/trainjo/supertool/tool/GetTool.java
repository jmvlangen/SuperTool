package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockGetAction;

//the get tool
public class GetTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public GetTool(){
		super(2,new BlockGetAction(),"get");
	}
}
