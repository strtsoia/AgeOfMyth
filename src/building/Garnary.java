package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Garnary extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Garnary garnary;
	
	private Garnary()
	{
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 3);
	}
	
	public static Garnary GetInstance()
	{
		if(garnary == null){
			garnary = new Garnary();
			return garnary;
		}else{
			return garnary;
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


