package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockMaterialAction;

//the material tool
public class MaterialTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public MaterialTool(){
		super(5, new BlockMaterialAction(), "material");
	}
}
