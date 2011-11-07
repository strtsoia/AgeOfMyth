package global;

import java.util.Hashtable;
/*
 * this class contains all global data, such as, units, resources, tiles
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
	
	public enum CardType
	{
		Permanent,
		Random,
		Battle
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
	
}
