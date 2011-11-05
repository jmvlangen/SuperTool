package mcmount.trainjo.supertool.toolset;

import mcmount.trainjo.supertool.ToolLink;

//a basic toolset consisting of the break and get tool
public class BasicToolSet extends ToolSet {
	public BasicToolSet(){
		super(ToolLink.getTool("break"),ToolLink.getTool("get"), "basic");
	}
}
