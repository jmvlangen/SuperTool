package mcmount.trainjo.supertool.tool;

import mcmount.trainjo.supertool.action.BlockSwapAction;

//the swap tool
public class SwapTool extends Tool {
	//constructor passing the necessary information to the tool constructor
	public SwapTool(){
		super(4,new BlockSwapAction(), "swap");
	}
}
