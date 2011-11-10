package building;

import global.GlobalDef;

import java.util.Hashtable;

import component.Culture;

public class StoreHouse extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static StoreHouse storeHouse;
	
	private StoreHouse()
	{
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.FOOD, 2);
		cost.put(GlobalDef.Resources.GOLD, 2);
		cost.put(GlobalDef.Resources.WOOD, 2);
	}
	
	public static StoreHouse GetInstance()
	{
		if(storeHouse == null){
			storeHouse = new StoreHouse();
			return storeHouse;
		}else{
			return storeHouse;
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


