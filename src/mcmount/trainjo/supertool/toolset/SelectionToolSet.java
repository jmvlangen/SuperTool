package mcmount.trainjo.supertool.toolset;

import mcmount.trainjo.supertool.ToolLink;

//a selection toolset consisting of the select and swap tool.
public class SelectionToolSet extends ToolSet {
	public SelectionToolSet(){
		super(ToolLink.getTool("select"), ToolLink.getTool("swap"), "selection");
	}
}
