package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Monument extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Monument monument;
	
	private Monument()
	{
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 2);
	}
	
	public static Monument GetInstance()
	{
		if(monument == null){
			monument = new Monument();
			return monument;
		}else{
			return monument;
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

