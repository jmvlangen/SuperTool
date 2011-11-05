package mcmount.trainjo.supertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mcmount.trainjo.supertool.tool.*;
import mcmount.trainjo.supertool.toolset.*;

//class for quick tool reference. This class allows you to register new tools and toolsets!!
public class ToolLink {
	//constructor registering the main class
	public ToolLink(SuperTool link){
		if(link != null) host = link;
		init();
	}
	
	//initializing the tools and toolsets
	private void init(){
		readToolFile();
		nextuniqueid = 13 + toolprops.size();
		addBasicTools();
		addBasicToolSets();
	}
	
	//adding the toolsets in the plugin itself
	private void addBasicToolSets() {
		RegisterToolSet(new BasicToolSet());
		RegisterToolSet(new SelectionToolSet());
		RegisterToolSet(new BuildToolSet());
	}

	//adding the tools in the plugin itself
	private void addBasicTools() {
		tools.add(new NoTool());
		RegisterTool(new BreakTool());
		RegisterTool(new BuildTool());
		RegisterTool(new GetTool());
		RegisterTool(new MaterialTool());
		RegisterTool(new SelectTool());
		RegisterTool(new SwapTool());
	}

	//reading tool.txt to make sure every tool pre-registered still gets the same id as before
	private void readToolFile() {
		File cfgdir = host.getDataFolder();
		File cfgfile = new File(cfgdir, "tool.txt");
		toolprops = new Properties();
		if(cfgfile.exists()){
			try{
				FileInputStream opener = new FileInputStream(cfgfile);
				toolprops.load(opener);
				opener.close();
			}
			catch(IOException ioexception){
				host.getServer().getLogger().fine("[SuperTool] Couldn't read tool.txt, skipping it!");
			}
		}
		else host.getServer().getLogger().fine("[SuperTool] Couldn't find tool.txt, creating it on disabling!");
		toolprops.setProperty("no", "0");
		toolprops.setProperty("break", "1");
		toolprops.setProperty("get", "2");
		toolprops.setProperty("select", "3");
		toolprops.setProperty("swap", "4");
		toolprops.setProperty("material", "5");
		toolprops.setProperty("build", "6");
	}

	//returns a unique id for the given tool name. If the tool is pre-registered it will return the id it was pre-registered with.
	//This is also the function which will register the name with the id, so it should be used to make sure people get the same tool every time!!!
	public static int GetUniqueToolID(String name){
		try{
			int i = Integer.parseInt(toolprops.getProperty(name, "-1"));
			if(i != -1) return i;
		}
		catch(NumberFormatException numberformatexception){
			host.getServer().getLogger().fine("[SuperTool] Internal error occured: tools.txt was manually modified!");
		}
		nextuniqueid++;
		toolprops.setProperty(name, (new StringBuilder()).append(nextuniqueid).toString());
		save();
		return nextuniqueid;
	}
	
	//registers a new tool.
	//returns true if the tool was registered and false otherwise
	public static boolean RegisterTool(Tool tool){
		if(getTool(tool.toolname()) == getTool(0) && getTool(tool.hand()) == getTool(0)){
			tools.add(tool);
			return true;
		}
		return false;
	}

	//registers a new toolset.
	//returns true if the toolset was registered and false otherwise
	public static boolean RegisterToolSet(ToolSet toolset){
		if(getToolSet(toolset.toolsetname()) == null){
			toolsets.add(toolset);
			return true;
		}
		return false;
	}
	
	//returns the tool that was registered with the specific id
	//returns the NoTool if no tool was registered with this id.
	//Do not use this to check for empty id's! Use the GetUniqueToolID method instead!!!!
	public static Tool getTool(int id){
		int i = 0;
		while(i < tools.size()){
			if(tools.get(i).hand() == id) break;
			i++;
		}
		if(i >= tools.size()) return getTool(0);
		return tools.get(i);
	}

	//returns the tool that was registered with the specific name
	//returns the NoTool if no tool was registered with this name
	public static Tool getTool(String name){
		int i = 0;
		while(i < tools.size()){
			if(tools.get(i).toolname().equalsIgnoreCase(name)) break;
			i++;
		}
		if(i >= tools.size()) return getTool(0);
		return tools.get(i);
	}

	//returns the toolset that was registered with the specific name
	//returns null if no tool was registered with this name
	public static ToolSet getToolSet(String name){
		int i = 0;
		while(i < toolsets.size()){
			if(toolsets.get(i).toolsetname().equalsIgnoreCase(name)) break;
			i++;
		}
		if(i >= toolsets.size()) return null;
		return toolsets.get(i);
	}
	
	//returns the toolset that was registered with the left tool 'lefthand' and the right tool 'righthand'
	//returns null if no such toolset was found.
	public static ToolSet getToolSet(Tool lefthand, Tool righthand) {
		int i = 0;
		while(i < toolsets.size()){
			if(toolsets.get(i).lefthand() == lefthand && toolsets.get(i).righthand() == righthand) return toolsets.get(i);
			i++;
		}
		return null;
	}
	
	//saves the registered names and ids to tool.txt
	@SuppressWarnings("deprecation")
	public static void save() {
		File cfgdir = host.getDataFolder();
		File cfgfile = new File(cfgdir, "tool.txt");
		try{
			if(cfgfile.exists()) cfgfile.mkdirs();
			FileOutputStream opener = new FileOutputStream(cfgfile);
			toolprops.save(opener, "This file stores which ids belong to which tools. Do not modify it manually!!");
			opener.close();
			return;
		}
		catch(IOException ioexception){
			host.getServer().getLogger().fine("[SuperTool] Couldn't read tool.txt, skipping it!");
		}
	}
	
	//all variables
	private static SuperTool host;										//a link to the main class
	private static int nextuniqueid;									//the next id which can be given to a tool. (Use the GetUniqueToolID to get it)
	private static List<Tool> tools = new ArrayList<Tool>();			//a list of all tools registered
	private static List<ToolSet> toolsets = new ArrayList<ToolSet>();	//a list of all toolsets registered
	private static Properties toolprops;								//a properties class containing all names and IDs
	
}
