package mcmount.trainjo.supertool;

import org.bukkit.Location;
import org.bukkit.World;

//A class representing an in game selection
public class Selection {
	//constructor using two locations to determine the selection: 'setstart' and 'setend'
	public Selection(Location setstart, Location setend){
		start = setstart;
		end = setend;
	}
	
	//constructor using two block coordinates to determine the selection
	//The first block is at (startX,startY,startZ)
	//The second block is at (endX,endY,endZ)
	//Both blocks are in world 'world'
	public Selection(World world, int startX, int startY, int startZ, int endX, int endY, int endZ){
		start = new Location(world, startX, startY, startZ);
		end = new Location(world, endX, endY, endZ);
	}
	
	//returns the location of the first corner block
	public Location getStart(){
		return start;
	}
	
	//returns the location of the second corner block
	public Location getEnd(){
		return end;
	}
	
	//returns the size along the x-dimension
	public int getXsize(){
		if(start == null || end == null) return 0;
		int startX = start.getBlockX();
		int endX = end.getBlockX();
		if(startX > endX) return startX - endX + 1;
		else return endX - startX + 1;
	}
	
	//returns the size along the y-dimension
	public int getYsize(){
		if(start == null || end == null) return 0;
		int startY = start.getBlockY();
		int endY = end.getBlockY();
		if(startY > endY) return startY - endY + 1;
		else return endY - startY + 1;
	}
	
	//returns the size along the z-dimension
	public int getZsize(){
		if(start == null || end == null) return 0;
		int startZ = start.getBlockZ();
		int endZ = end.getBlockZ();
		if(startZ > endZ) return startZ - endZ + 1;
		else return endZ - startZ + 1;
	}
	
	//returns the total volume of the selection in blocks
	public int getVolume(){
		return getXsize() * getYsize() * getZsize();
	}
	
	//returns the world the selection is in
	public World getWorld(){
		if(start == null || end == null) return null;
		return start.getWorld();
	}
	
	//returns whether the selection is a valid one
	public boolean isValid(){
		return start != null && end != null;
	}
	
	//returns whether the selection is a allowed according to the settings in the config. This also returns false if the selection isn't valid!
	public boolean isAllowed(){
		if(!isValid()) return false;
		if(getXsize() > ConfigLink.getMaxLength()) return false;
		if(getYsize() > ConfigLink.getMaxLength()) return false;
		if(getZsize() > ConfigLink.getMaxLength()) return false;
		if(getVolume() > ConfigLink.getMaxVolume()) return false;
		return true;
	}
	
	//Sets the start and returns a message to send to a player or the console
	public String setStart(Location set){
		if(start != null){
			if(start.getWorld() != set.getWorld()) end = null;
		}
		else{
			if(end != null){
				if(end.getWorld() != set.getWorld()) end = null;
			}
		}
		start = set;
		return ((new StringBuilder()).append("Location 1 was set to (").append(start.getBlockX()).append(",").append(start.getBlockY()).append(",").append(start.getBlockZ()).append(")").toString());
	}
	
	//Set the end and returns a message to send to a player or the console
	public String setEnd(Location set){
		if(end != null){
			if(end.getWorld() != set.getWorld()) start = null;
		}
		end = set;
		return ((new StringBuilder()).append("Location 2 was set to (").append(end.getBlockX()).append(",").append(end.getBlockY()).append(",").append(end.getBlockZ()).append(")").toString());
	}
	
	//returns the lowest x coordinate in the selection
	public int getminX(){
		if(start.getBlockX()<end.getBlockX()) return start.getBlockX();
		else return end.getBlockX();
	}
	
	//returns the lowest y coordinate in the selection
	public int getminY(){
		if(start.getBlockY()<end.getBlockY()) return start.getBlockY();
		else return end.getBlockY();
	}
	
	//returns the lowest z coordinate in the selection
	public int getminZ(){
		if(start.getBlockZ()<end.getBlockZ()) return start.getBlockZ();
		else return end.getBlockZ();
	}
	
	//returns the largest x coordinate in the selection
	public int getmaxX(){
		if(start.getBlockX()>end.getBlockX()) return start.getBlockX();
		else return end.getBlockX();
	}
	
	//returns the largest y coordinate in the selection
	public int getmaxY(){
		if(start.getBlockY()>end.getBlockY()) return start.getBlockY();
		else return end.getBlockY();
	}
	
	//return the largest z coordinate in the selection
	public int getmaxZ(){
		if(start.getBlockZ()>end.getBlockZ()) return start.getBlockZ();
		else return end.getBlockZ();
	}
	
	//all variables
	private Location start;	//the location of the first corner
	private Location end;	//the location of the second corner
}
