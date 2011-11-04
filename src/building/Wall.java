package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Wall extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Wall wall;
	
	private Wall()
	{
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
	}
	
	public static Wall GetInstance()
	{
		if(wall == null){
			wall = new Wall();
			return wall;
		}else{
			return wall;
		}
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}
	
	public void Behavior()
	{
		
	}
	
	public void UnBehavior(){
		
	}
}

