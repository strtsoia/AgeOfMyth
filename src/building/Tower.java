package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class Tower extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Tower tower;
	
	private Tower()
	{
		cost.put(GlobalDef.Resources.WOOD, 3);
		cost.put(GlobalDef.Resources.GOLD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.FOOD, 0);
	}
	
	public static Tower GetInstance()
	{
		if(tower == null){
			tower = new Tower();
			return tower;
		}else{
			return tower;
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

