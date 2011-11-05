package mcmount.trainjo.supertool.command;

import mcmount.trainjo.supertool.SuperTool;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//A class which sends the call to a command to the respective class
public class SuperToolCommands {
	//constructor registering the main class
	public SuperToolCommands(SuperTool link){
		host = link;
	}
	
	//all variables
	public SuperTool host;											//a link to the main class
	private LeftToolCommand lefttool = new LeftToolCommand();		//class for the command /lefttool
	private RightToolCommand righttool = new RightToolCommand();	//class for the command /righttool
	private ToolSetCommand toolset = new ToolSetCommand();			//class for the command /toolset
	private SwapCommand swap = new SwapCommand();					//class for the command /swap
	private MaterialCommand material = new MaterialCommand();		//class for the command /material
	private ShapeCommand shape = new ShapeCommand();				//class for the command /shape
	private SizeCommand size = new SizeCommand();					//class for the command /size

	//the method which passes the command to the correct class
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("Supertool commands only work for players");
			return true;
		}
		if(command.getName().equalsIgnoreCase("lefttool")) return lefttool.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("righttool")) return righttool.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("toolset")) return toolset.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("swap")) return swap.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("material")) return material.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("shape")) return shape.call(args, (Player) sender, host);
		if(command.getName().equalsIgnoreCase("size")) return size.call(args, (Player) sender, host);
		return false;
	}
}
