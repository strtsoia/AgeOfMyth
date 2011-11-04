package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Armory extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Armory armory;
	
	private Armory()
	{
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.WOOD, 3);
	}
	
	public static Armory GetInstance()
	{
		if(armory == null){
			armory = new Armory();
			return armory;
		}else{
			return armory;
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


