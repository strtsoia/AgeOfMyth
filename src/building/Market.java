package building;

import global.GlobalDef;
import java.util.Hashtable;

public class Market extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static Market market;
	
	private Market()
	{
		cost.put(GlobalDef.Resources.FAVOR, 2);
		cost.put(GlobalDef.Resources.GOLD, 3);
	}
	
	public static Market GetInstance()
	{
		if(market == null){
			market = new Market();
			return market;
		}else{
			return market;
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

