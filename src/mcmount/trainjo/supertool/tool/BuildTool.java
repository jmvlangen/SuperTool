package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockBuildAction;

//the build tool
public class BuildTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public BuildTool(){
		super(6, new BlockBuildAction(), "build");
	}
}
