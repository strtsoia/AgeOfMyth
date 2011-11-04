package building;

import global.GlobalDef;
import java.util.Hashtable;

public class House extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static House house;
	
	private House()
	{
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.FOOD, 2);
	}
	
	public static House GetInstance()
	{
		if(house == null){
			house = new House();
			return house;
		}else{
			return house;
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
