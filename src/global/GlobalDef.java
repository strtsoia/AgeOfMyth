package global;

import java.util.Hashtable;
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
	
	
}
