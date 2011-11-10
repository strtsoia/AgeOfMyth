package global;

import java.util.Hashtable;
import building.*;

/*
 * this class contains all global data
 */
public class GlobalDef {

	public enum Resources
	{
		FOOD,
		WOOD,
		GOLD,
		FAVOR,
		VICTORY;
	}
	
	public enum Races
	{
		Egypt,
		Greek,
		Norse
	}
		
	public enum Terrain
	{
		Fertile,
		Forest,
		Hills,
		Mountains,
		Desert,
		Swamp
		
	}
	
	public enum Age
	{
		Ancient,
		Classical,
		Heroic,
		Mythic
	}
	
	// how many number of players in game
	private static int numOfPlayer;

	public static int getNumOfPlayer() {
		return numOfPlayer;
	}

	public static void setNumOfPlayer(int num) {
		numOfPlayer = num;
	}
	
	private static Hashtable<Integer, Building> buildMap = new Hashtable<Integer, Building>();
	public static Hashtable<Integer, Building> getBuildingMap()
	{
		if(buildMap.size() == 0)
		{
			buildMap.put(0, House.GetInstance());
			buildMap.put(1, Wall.GetInstance());
			buildMap.put(2, Tower.GetInstance());
			buildMap.put(3, StoreHouse.GetInstance());
			buildMap.put(4, Market.GetInstance());
			buildMap.put(5, Armory.GetInstance());
			buildMap.put(6, Quarry.GetInstance());
			buildMap.put(7, Monument.GetInstance());
			buildMap.put(8, Garnary.GetInstance());
			buildMap.put(9, GoldMint.GetInstance());
			buildMap.put(10, GreatTemple.GetInstance());
			buildMap.put(11, WoodWorkshop.GetInstance());
			buildMap.put(12, Wonder.GetInstance());
			buildMap.put(13, SiegeEngineWorkshop.GetInstance());
			return buildMap;
			
		}
		
		return buildMap;
	}
	
	private static Hashtable<Integer, Resources> resourceMap = new Hashtable<Integer, Resources>();
	public static Hashtable<Integer, Resources> getResourceMap()
	{
		if(resourceMap.size() == 0 )
		{
			resourceMap.put(0, Resources.FOOD);
			resourceMap.put(1, Resources.FAVOR);
			resourceMap.put(2, Resources.WOOD);
			resourceMap.put(3, Resources.GOLD);
			resourceMap.put(4, Resources.VICTORY);
			
			return resourceMap;
			
		}
		
		return resourceMap;
	}
}
