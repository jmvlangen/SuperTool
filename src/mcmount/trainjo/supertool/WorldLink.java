package mcmount.trainjo.supertool;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

//A class containing some methods for easy world reference
public class WorldLink {

	//sends a message to a player
	public static void sendMessage(Player player, String message) {
		player.sendRawMessage(message);
	}
	
	//returns the material from a name which can be either the id of the material or the real name
	public static Material getMaterial(String name){
		try{
			int i = Integer.parseInt(name);
			return Material.getMaterial(i);
		}
			catch (NumberFormatException exception){
			Material[] material = Material.values();
			int i;
			for(i = 0;i < material.length;i++){
				if(material[i].name().equalsIgnoreCase(name)) break;
			}
			if(i >= material.length) return null;
			return material[i];
		}
	}

	//Changes an area into a shape of the specified size and material
	//The player parameter is used to determine which player 'build' the selection (passes an event)
	public static boolean ChangeArea(Shape shape, int xsize, int ysize, int zsize, Location location, int type, Player player) {
		if(xsize == 0||ysize == 0||zsize == 0) return false;
		World world = location.getWorld();
		int startx = (int) ( ((float)location.getBlockX()) + 1 - ( ((float)xsize) /2) );
		int starty = (int) ( ((float)location.getBlockY()) + 1 - ( ((float)ysize) /2) );
		int startz = (int) ( ((float)location.getBlockZ()) + 0 - ( ((float)zsize) /2) );
		switch(shape){
		case CUBE:
			return ChangeAreaCube(world,startx,starty,startz,xsize,ysize,zsize,type, player);
		case CIRCLE:
			return ChangeAreaCircle(world,startx,starty,startz,xsize,ysize,zsize,type, player);
		case CILINDER:
			return ChangeAreaCilinder(world,startx,starty,startz,xsize,ysize,zsize,type, player);
		}
		return false;
	}
	
	//changes an area inside a selection with a specific shape
	//The player parameter is used to determine which player 'build' the selection (passes an event)
	public static boolean ChangeArea(Shape shape, Selection selection, int type, Player player){
		if(!selection.isValid()) return false;
		switch(shape){
		case CUBE:
			return ChangeAreaCube(selection,type, player);
		case CIRCLE:
			return ChangeAreaCircle(selection,type, player);
		case CILINDER:
			return ChangeAreaCilinder(selection,type, player);
		}
		return false;
	}

	//changes an area in a cilinder shape
	private static boolean ChangeAreaCilinder(World world, int startx,
			int starty, int startz, int xsize, int ysize, int zsize, int type, Player player) {
		boolean succeeded = false;
		float circlesize;
		if(xsize > zsize) circlesize = ((float) zsize)/ 2;
		else circlesize = ((float) xsize)/ 2;
		float xmiddle = ((float) (xsize))/2 + (float) startx;
		float zmiddle = ((float) (zsize))/2 + (float) startz;
		for(int i = 0;i < xsize;i++){
			for(int j = 0;j < ysize;j++){
				for(int k = 0;k< zsize;k++){
					Block block = world.getBlockAt(startx + i,starty + j,startz + k);
					if(block != null){
						if(getBlockDistance(block, xmiddle, zmiddle) <= circlesize * circlesize){
							if(setBlock(block, type , player)) succeeded = true;
						}
					}
				}
			}
		}
		return succeeded;
	}

	//used to make a cilinder shape
	private static float getBlockDistance(Block block, float xmiddle,
			float zmiddle) {
		float xdistance = xmiddle - (float) block.getX() - (1 / 2);
		float zdistance = zmiddle - (float) block.getZ() - (1 / 2);
		return xdistance * xdistance + zdistance * zdistance;
	}

	//used to make a circle (ball) shape
	private static float getBlockDistance(Block block, float xmiddle, float ymiddle, 
			float zmiddle) {
		float xdistance = xmiddle - (float) block.getX() - (1 / 2);
		float ydistance = ymiddle - (float) block.getY() - (1 / 2);
		float zdistance = zmiddle - (float) block.getZ() - (1 / 2);
		return xdistance * xdistance + ydistance * ydistance + zdistance * zdistance;
	}

	//changes an area in a circle (ball) shape
	private static boolean ChangeAreaCircle(World world, int startx,
			int starty, int startz, int xsize, int ysize, int zsize, int type, Player player) {
		boolean succeeded = false;
		float ballsize;
		if(xsize > zsize && ysize > zsize) ballsize = ((float) zsize)/ 2;
		else if(xsize > ysize) ballsize = ((float) ysize)/ 2;
		else ballsize = ((float) xsize)/ 2;
		float xmiddle = ((float) (xsize))/2 + (float) startx;
		float ymiddle = ((float) (ysize))/2 + (float) starty;
		float zmiddle = ((float) (zsize))/2 + (float) startz;
		for(int i = 0;i < xsize;i++){
			for(int j = 0;j < ysize;j++){
				for(int k = 0;k< zsize;k++){
					Block block = world.getBlockAt(startx + i,starty + j,startz + k);
					if(block != null){
						if(getBlockDistance(block, xmiddle, ymiddle, zmiddle) <= ballsize * ballsize){
							if(setBlock(block, type , player)) succeeded = true;
						}
					}
				}
			}
		}
		return succeeded;
	}

	//changes an area in a cube shape
	private static boolean ChangeAreaCube(World world, int startx, int starty,
			int startz, int xsize, int ysize, int zsize, int type, Player player) {
		boolean succeeded = false;
		for(int i = 0;i < xsize;i++){
			for(int j = 0;j < ysize;j++){
				for(int k = 0;k< zsize;k++){
					Block block = world.getBlockAt(startx + i,starty + j,startz + k);
					if(block != null){
						if(setBlock(block, type , player)) succeeded = true;
					}
				}
			}
		}
		return succeeded;
	}
	
	//sets 1 block to a certain material
	private static boolean setBlock(Block block, int id, Player player){
		BlockState state = block.getState();
		if(block.setTypeId(id)){
			if(id == 0) sendEvent(new BlockBreakEvent(block, player));
			else sendEvent(new BlockPlaceEvent(block, state, block, player.getItemInHand(), player, true));
			return true;
		}
		return false;
	}
	
	//changes an area in a cilinder shape
	private static boolean ChangeAreaCilinder(Selection selection, int type, Player player){
		if(selection.isValid()) return ChangeAreaCilinder(selection.getWorld(),selection.getminX(),selection.getminY(),selection.getminZ(),selection.getXsize(),selection.getYsize(),selection.getZsize(),type, player);
		else return false;
	}
	
	//changes an area in a circle (ball) shape
	private static boolean ChangeAreaCircle(Selection selection, int type, Player player){
		if(selection.isValid()) return ChangeAreaCircle(selection.getWorld(),selection.getminX(),selection.getminY(),selection.getminZ(),selection.getXsize(),selection.getYsize(),selection.getZsize(),type, player);
		else return false;
	}
	
	//changes an area in a cube shape
	private static boolean ChangeAreaCube(Selection selection, int type, Player player){
		if(selection.isValid()) return ChangeAreaCube(selection.getWorld(),selection.getminX(),selection.getminY(),selection.getminZ(),selection.getXsize(),selection.getYsize(),selection.getZsize(),type, player);
		else return false;
	}

	//returns the respective material for the block, eg for a crop it returns wheat, for a door it returns the door item.
	public static Material getInventoryItem(Block block) {
		switch(block.getType()){
		case STONE:
		case GRASS:
		case DIRT:
		case COBBLESTONE:
		case WOOD:
		case SAPLING:
		case WATER:
		case STATIONARY_WATER:
		case LAVA:
		case STATIONARY_LAVA:
		case SAND:
		case GRAVEL:
		case GOLD_ORE:
		case IRON_ORE:
		case COAL_ORE:
		case LOG:
		case LEAVES:
		case SPONGE:
		case GLASS:
		case LAPIS_ORE:
		case LAPIS_BLOCK:
		case DISPENSER:
		case SANDSTONE:
		case NOTE_BLOCK:
		case POWERED_RAIL:
		case DETECTOR_RAIL:
		case WEB:
		case LONG_GRASS:
		case DEAD_BUSH:
		case WOOL:
		case YELLOW_FLOWER:
		case RED_ROSE:
		case BROWN_MUSHROOM:
		case RED_MUSHROOM:
		case GOLD_BLOCK:
		case IRON_BLOCK:
		case DOUBLE_STEP:
		case STEP:
		case BRICK:
		case TNT:
		case BOOKSHELF:
		case MOSSY_COBBLESTONE:
		case OBSIDIAN:
		case TORCH:
		case MOB_SPAWNER:
		case WOOD_STAIRS:
		case CHEST:
		case DIAMOND_ORE:
		case DIAMOND_BLOCK:
		case WORKBENCH:
		case FURNACE:
		case LADDER:
		case RAILS:
		case COBBLESTONE_STAIRS:
		case LEVER:
		case STONE_PLATE:
		case WOOD_PLATE:
		case REDSTONE_ORE:
		case REDSTONE_TORCH_ON:
		case STONE_BUTTON:
		case ICE:
		case SNOW_BLOCK:
		case CACTUS:
		case CLAY:
		case JUKEBOX:
		case FENCE:
		case PUMPKIN:
		case NETHERRACK:
		case SOUL_SAND:
		case GLOWSTONE:
		case JACK_O_LANTERN:
		case LOCKED_CHEST:
		case TRAP_DOOR:
		case MONSTER_EGGS:
		case SMOOTH_BRICK:
		case HUGE_MUSHROOM_1:
		case HUGE_MUSHROOM_2:
		case IRON_FENCE:
		case THIN_GLASS:
		case VINE:
		case FENCE_GATE:
		case BRICK_STAIRS:
		case SMOOTH_STAIRS:
		case BEDROCK:
		case FIRE:
		case SNOW:
		case PORTAL:
			return block.getType();
		case PISTON_STICKY_BASE:
			return Material.PISTON_STICKY_BASE;
		case BED_BLOCK:
			return Material.BED;
		case PISTON_BASE:
		case PISTON_EXTENSION:
		case PISTON_MOVING_PIECE:
			return Material.PISTON_BASE;
		case REDSTONE_WIRE:
			return Material.REDSTONE;
		case CROPS:
			return Material.SEEDS;
		case SOIL:
			return Material.DIRT;
		case BURNING_FURNACE:
			return Material.FURNACE;
		case SIGN_POST:
		case WALL_SIGN:
			return Material.SIGN;
		case IRON_DOOR_BLOCK:
			return Material.IRON_DOOR;
		case WOODEN_DOOR:
			return Material.WOOD_DOOR;
		case GLOWING_REDSTONE_ORE:
			return Material.REDSTONE_ORE;
		case REDSTONE_TORCH_OFF:
			return Material.REDSTONE_TORCH_ON;
		case SUGAR_CANE_BLOCK:
			return Material.SUGAR_CANE;
		case CAKE_BLOCK:
			return Material.CAKE;
		case DIODE_BLOCK_ON:
		case DIODE_BLOCK_OFF:
			return Material.DIODE;
		case MELON_BLOCK:
		case MELON_STEM:
			return Material.MELON_SEEDS;
		case PUMPKIN_STEM:
			return Material.PUMPKIN_SEEDS;
		default:
			return null;
		}
	}
	
	//returns a block that is placeable in a selection eg no flowers etc. (defaults to air)
	public static Material getPlaceableBlock(Material material) {
		switch(material){
		case STONE:
		case GRASS:
		case DIRT:
		case COBBLESTONE:
		case WOOD:
		case SAPLING:
		case WATER:
		case STATIONARY_WATER:
		case LAVA:
		case STATIONARY_LAVA:
		case SAND:
		case GRAVEL:
		case GOLD_ORE:
		case IRON_ORE:
		case COAL_ORE:
		case LOG:
		case LEAVES:
		case SPONGE:
		case GLASS:
		case LAPIS_ORE:
		case LAPIS_BLOCK:
		case DISPENSER:
		case SANDSTONE:
		case NOTE_BLOCK:
		case WEB:
		case WOOL:
		case GOLD_BLOCK:
		case IRON_BLOCK:
		case DOUBLE_STEP:
		case STEP:
		case BRICK:
		case TNT:
		case BOOKSHELF:
		case MOSSY_COBBLESTONE:
		case OBSIDIAN:
		case MOB_SPAWNER:
		case WOOD_STAIRS:
		case DIAMOND_ORE:
		case DIAMOND_BLOCK:
		case WORKBENCH:
		case FURNACE:
		case COBBLESTONE_STAIRS:
		case REDSTONE_ORE:
		case ICE:
		case SNOW_BLOCK:
		case CACTUS:
		case CLAY:
		case JUKEBOX:
		case FENCE:
		case PUMPKIN:
		case NETHERRACK:
		case SOUL_SAND:
		case GLOWSTONE:
		case JACK_O_LANTERN:
		case LOCKED_CHEST:
		case MONSTER_EGGS:
		case SMOOTH_BRICK:
		case HUGE_MUSHROOM_1:
		case HUGE_MUSHROOM_2:
		case IRON_FENCE:
		case THIN_GLASS:
		case BRICK_STAIRS:
		case SMOOTH_STAIRS:
		case BEDROCK:
		case PORTAL:
			return material;
		case PISTON_STICKY_BASE:
			return Material.PISTON_STICKY_BASE;
		case PISTON_BASE:
		case PISTON_EXTENSION:
		case PISTON_MOVING_PIECE:
			return Material.PISTON_BASE;
		case SOIL:
			return Material.DIRT;
		case BURNING_FURNACE:
			return Material.FURNACE;
		default:
			return Material.AIR;
		}
	}
	
	//gives a player 'i' items of 'material' 
	@SuppressWarnings("deprecation")
	public static void givePlayer(Player player, Material material, int i) {
		player.getInventory().addItem(new ItemStack(material, i));
		player.updateInventory();
	}
	
	//sends an event
	public static void sendEvent(Event event){
		PluginManager pm = Bukkit.getPluginManager();
		pm.callEvent(event);
	}

	//returns the permission manager of permissionex. Returns null if the permissionex plugin isn't loaded
	public static PermissionManager getPermissionManager() {
		PermissionManager manager = null;
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) manager = PermissionsEx.getPermissionManager();
		else Logger.getLogger("Minecraft").warning("SuperTool couldn't find permissionsex -> Supposing all permissions are false!");
		return manager;
	}
	
}
