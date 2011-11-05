package mcmount.trainjo.supertool.tool;

//represents no tool. This tool should not be used in any way!!!! It will cause nullpointer errors!
public class NoTool extends Tool{
	//constructor passing the necessary information to the tool constructor
	public NoTool(){
		super(0, null, "no");
	}
}
