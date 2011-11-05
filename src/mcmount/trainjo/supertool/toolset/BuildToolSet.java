package mcmount.trainjo.supertool.toolset;

import mcmount.trainjo.supertool.ToolLink;

public class BuildToolSet extends ToolSet {

	public BuildToolSet() {
		super(ToolLink.getTool("build"), ToolLink.getTool("material"), "build");
	}
	
}
