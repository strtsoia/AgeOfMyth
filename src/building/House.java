package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class House extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static House house;
	
	private House()
	{
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 0);
		cost.put(GlobalDef.Resources.FAVOR, 0);
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
	
	public void Behavior(Culture c)
	{
		
	}
	
	public void UnBehavior(Culture c){
		
	}
}
