package global;

import java.util.Hashtable;
import building.*;
import tile.*;
import battlecard.*;

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
	
	private static Hashtable<Integer, Terrain> terrainMap = new Hashtable<Integer, Terrain>();
	public static Hashtable<Integer, Terrain>getTerrainMap()
	{
		if(terrainMap.size() == 0)
		{
			terrainMap.put(0, Terrain.Fertile);
			terrainMap.put(1, Terrain.Forest);
			terrainMap.put(2, Terrain.Hills);
			terrainMap.put(3, Terrain.Mountains);
			terrainMap.put(4, Terrain.Desert);
			terrainMap.put(5, Terrain.Swamp);
			
			return terrainMap;
		}
		
		return terrainMap;
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
	
	private static Hashtable<Integer, ResProduceTile> tileMap = new Hashtable<Integer, ResProduceTile>();
	public static Hashtable<Integer, ResProduceTile> getTileMap()
	{
		if(tileMap.size() == 0)
		{
			tileMap.put(0, FertileA.GetInstance());
			tileMap.put(1, FertileB.GetInstance());
			tileMap.put(2, FertileC.GetInstance());
			tileMap.put(3, FertileD.GetInstance());
			tileMap.put(4, ForestA.GetInstance());
			tileMap.put(5, ForestB.GetInstance());
			tileMap.put(6, ForestC.GetInstance());
			tileMap.put(7, ForestD.GetInstance());
			tileMap.put(8, HillsA.GetInstance());
			tileMap.put(9, HillsB.GetInstance());
			tileMap.put(10, HillsC.GetInstance());
			tileMap.put(11, HillsD.GetInstance());
			tileMap.put(12, MountainA.GetInstance());
			tileMap.put(13, MountainB.GetInstance());
			tileMap.put(14, MountainC.GetInstance());
			tileMap.put(15, DesertA.GetInstance());
			tileMap.put(16, DesertB.GetInstance());
			tileMap.put(17, SwampA.GetInstance());
			tileMap.put(18, SwampB.GetInstance());
			tileMap.put(19, SwampC.GetInstance());
			
			return tileMap;
		}
		
		return tileMap;
	}

	private static Hashtable<Integer, BattleCard> egyptBattleCard = new Hashtable<Integer, BattleCard>();
	public static Hashtable<Integer, BattleCard> getEgyptBattleCard()
	{
		if(egyptBattleCard.size() == 0)
		{
			egyptBattleCard.put(0, Anubite.getInstance());
			egyptBattleCard.put(1, Chariot.getInstance());
			egyptBattleCard.put(2, Elephant.getInstance());
			egyptBattleCard.put(3, Mummy.getInstance());
			egyptBattleCard.put(4, Pharaoh.getInstance());
			egyptBattleCard.put(5, Phoenix.getInstance());
			egyptBattleCard.put(6, Priest.getInstance());
			egyptBattleCard.put(7, Scorpion.getInstance());
			egyptBattleCard.put(8, Osiris.getInstance());
			egyptBattleCard.put(9, Spearman.getInstance());
			egyptBattleCard.put(10, Sphinx.getInstance());
			egyptBattleCard.put(11, Wadjet.getInstance());
			
			return egyptBattleCard;
		}
		
		return egyptBattleCard;
	}
	
	private static Hashtable<Integer, BattleCard> greekBattleCard = new Hashtable<Integer, BattleCard>();
	public static Hashtable<Integer, BattleCard> getGreekBattleCard()
	{
		if(greekBattleCard.size() == 0)
		{
			return greekBattleCard;
		}
		
		return greekBattleCard;
	}
	
	private static Hashtable<Integer, BattleCard> norsekBattleCard = new Hashtable<Integer, BattleCard>();
	public static Hashtable<Integer, BattleCard> getNorseBattleCard()
	{
		if(norsekBattleCard.size() == 0)
		{
			return norsekBattleCard;
		}
		
		return norsekBattleCard;
	}
	
}
