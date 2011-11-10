package building;

import global.GlobalDef;
import java.util.Hashtable;

import component.Culture;

public class GoldMint extends Building{

	private Hashtable<GlobalDef.Resources, Integer> cost = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private static GoldMint goldMint;
	
	private GoldMint()
	{
		cost.put(GlobalDef.Resources.WOOD, 2);
		cost.put(GlobalDef.Resources.FOOD, 3);
		cost.put(GlobalDef.Resources.FAVOR, 0);
		cost.put(GlobalDef.Resources.GOLD, 0);
	}
	
	public static GoldMint GetInstance()
	{
		if(goldMint == null){
			goldMint = new GoldMint();
			return goldMint;
		}else{
			return goldMint;
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

