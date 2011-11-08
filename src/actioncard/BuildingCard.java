package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

public class BuildingCard extends Card{

	private static BuildingCard buildingCard;
	
	private Hashtable<GlobalDef.Resources, Integer> cost =
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private int maxBuildNumber;
	
	private BuildingCard()
	{
		maxBuildNumber = 2;
	}
	
	public static BuildingCard GetInstance()
	{
		if(buildingCard == null)
		{
			buildingCard = new BuildingCard();
			return buildingCard;
		}
		
		return buildingCard;
	}

	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	
	public void setCost(Hashtable<GlobalDef.Resources, Integer> cost) {
		this.cost = cost;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action()
	{
		
	}

}
