package component;

import global.GlobalDef;
import building.*;
import battlecard.*;
import tile.*;

import java.util.Hashtable;

import settings.Bank;
import utility.ResourceHandler;

public class Board {

	/* production area */
	boolean[][] productionOccupied = new boolean[4][4];
	// different culture has different board terrain,must be determined by subclass
	GlobalDef.Terrain[][] terrainOnBoard = new GlobalDef.Terrain[4][4];
	Hashtable<GlobalDef.Resources,Integer>[][] productivity = new Hashtable[4][4];
	ResProduceTile[][] pTilesOnBoard = new ResProduceTile[4][4];
	
	/* city area */
	boolean[][] cityOccupied = new boolean[4][4];
	Building[][] constructedBuilding = new Building[4][4];
	
	/* holding area */
	Hashtable<BattleCard, Integer> holdingUnits = new Hashtable<BattleCard, Integer>();
	Hashtable<GlobalDef.Resources, Integer> holdResource = new Hashtable<GlobalDef.Resources, Integer>();
	Hashtable<Integer, BattleCard> unitsOnBoard = new Hashtable<Integer, BattleCard>();
	Hashtable<BattleCard, Integer> unitsPool = new Hashtable<BattleCard, Integer>();
	
	// constructor
	public Board(GlobalDef.Races race)
	{
		InitialProductionBoard(race);
		InitialCityBoard();
		InitialHoldingArea(race);
		
	}
	
	private void InitialHoldingArea(GlobalDef.Races race)
	{
		// begin initial holding resource for player
		holdResource.put(GlobalDef.Resources.FOOD, 0);
		holdResource.put(GlobalDef.Resources.GOLD, 0);
		holdResource.put(GlobalDef.Resources.WOOD, 0);
		holdResource.put(GlobalDef.Resources.FAVOR, 0);
		
		// initial resource each player get
		Hashtable<GlobalDef.Resources, Integer> iniRes = new Hashtable<GlobalDef.Resources, Integer>();
		iniRes.put(GlobalDef.Resources.FAVOR, 4);
		iniRes.put(GlobalDef.Resources.FOOD, 4);
		iniRes.put(GlobalDef.Resources.GOLD, 4);
		iniRes.put(GlobalDef.Resources.WOOD, 4);
		
		ResourceHandler.Add(holdResource, iniRes);
		ResourceHandler.Delete(Bank.getInstance().getResourcePool(), iniRes);
		// end of initial holding resource
		
		// begin initial units for player
		if(race.equals(GlobalDef.Races.Egypt))
		{
			InitalUnitsForEgypt();
		}else if(race.equals(GlobalDef.Races.Greek))
		{
			InitialUnitsForGreek();
		}else{
			InitialUnitsForNorse();
		}
		// end of units initialization	
	}
	
	private void InitialCityBoard()
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				cityOccupied[row][col] = false;
				constructedBuilding[row][col] = null;
			}
	}
	
	// different culture has different board terrain
	private void InitialProductionBoard(GlobalDef.Races race)
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				productionOccupied[row][col] = false;
			}
		
		if(race.equals(GlobalDef.Races.Egypt))
		{
			AssignEgyptBoard();
			return;
		}else if(race.equals(GlobalDef.Races.Greek))
		{
			AssignGreekBoard();
			return;
		}else
		{
			AssignNorseBoard();
			return;
		}
		
	}
	
	// production area of Egypt board
	private void AssignEgyptBoard()
	{
		terrainOnBoard[0][0] = GlobalDef.Terrain.Desert;
		terrainOnBoard[0][1] = GlobalDef.Terrain.Desert;
		terrainOnBoard[0][2] = GlobalDef.Terrain.Swamp;
		terrainOnBoard[0][3] = GlobalDef.Terrain.Swamp;
		terrainOnBoard[1][0] = GlobalDef.Terrain.Forest;
		terrainOnBoard[1][1] = GlobalDef.Terrain.Desert;
		terrainOnBoard[1][2] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[1][3] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[2][0] = GlobalDef.Terrain.Desert;
		terrainOnBoard[2][1] = GlobalDef.Terrain.Desert;
		terrainOnBoard[2][2] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[2][3] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[3][0] = GlobalDef.Terrain.Desert;
		terrainOnBoard[3][1] = GlobalDef.Terrain.Hills;
		terrainOnBoard[3][2] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[3][3] = GlobalDef.Terrain.Hills;
		
	}
	
	// production area of Greek board
	private void AssignGreekBoard()
	{
		terrainOnBoard[0][0] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[0][1] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[0][2] = GlobalDef.Terrain.Forest;
		terrainOnBoard[0][3] = GlobalDef.Terrain.Swamp;
		terrainOnBoard[1][0] = GlobalDef.Terrain.Hills;
		terrainOnBoard[1][1] = GlobalDef.Terrain.Mountains;
		terrainOnBoard[1][2] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[1][3] = GlobalDef.Terrain.Forest;
		terrainOnBoard[2][0] = GlobalDef.Terrain.Hills;
		terrainOnBoard[2][1] = GlobalDef.Terrain.Hills;
		terrainOnBoard[2][2] = GlobalDef.Terrain.Hills;
		terrainOnBoard[2][3] = GlobalDef.Terrain.Hills;
		terrainOnBoard[3][0] = GlobalDef.Terrain.Desert;
		terrainOnBoard[3][1] = GlobalDef.Terrain.Hills;
		terrainOnBoard[3][2] = GlobalDef.Terrain.Hills;
		terrainOnBoard[3][3] = GlobalDef.Terrain.Hills;
	}
	
	// production area of Norse board
	private void AssignNorseBoard()
	{
		terrainOnBoard[0][0] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[0][1] = GlobalDef.Terrain.Mountains;
		terrainOnBoard[0][2] = GlobalDef.Terrain.Mountains;
		terrainOnBoard[0][3] = GlobalDef.Terrain.Mountains;
		terrainOnBoard[1][0] = GlobalDef.Terrain.Fertile;
		terrainOnBoard[1][1] = GlobalDef.Terrain.Forest;
		terrainOnBoard[1][2] = GlobalDef.Terrain.Hills;
		terrainOnBoard[1][3] = GlobalDef.Terrain.Mountains;
		terrainOnBoard[2][0] = GlobalDef.Terrain.Hills;
		terrainOnBoard[2][1] = GlobalDef.Terrain.Swamp;
		terrainOnBoard[2][2] = GlobalDef.Terrain.Forest;
		terrainOnBoard[2][3] = GlobalDef.Terrain.Hills;
		terrainOnBoard[3][0] = GlobalDef.Terrain.Desert;
		terrainOnBoard[3][1] = GlobalDef.Terrain.Forest;
		terrainOnBoard[3][2] = GlobalDef.Terrain.Forest;
		terrainOnBoard[3][3] = GlobalDef.Terrain.Fertile;
	}
	
	private void InitalUnitsForEgypt()
	{
		// initial units pool
		unitsPool.put(Scorpion.getInstance(), 4);
		unitsPool.put(Sphinx.getInstance(), 4);
		unitsPool.put(Wadjet.getInstance(), 4);
		unitsPool.put(Anubite.getInstance(), 2);
		unitsPool.put(Phoenix.getInstance(), 4);
		unitsPool.put(Priest.getInstance(), 1);
		unitsPool.put(Pharaoh.getInstance(), 1);
		unitsPool.put(Osiris.getInstance(), 1);
		unitsPool.put(Mummy.getInstance(), 4);
		unitsPool.put(Chariot.getInstance(), 4);
		unitsPool.put(Spearman.getInstance(), 2);
		unitsPool.put(Elephant.getInstance(), 2);
		
		// initial holding units
		holdingUnits.put(Scorpion.getInstance(), 0);
		holdingUnits.put(Sphinx.getInstance(), 0);
		holdingUnits.put(Wadjet.getInstance(), 0);
		holdingUnits.put(Anubite.getInstance(), 2);
		holdingUnits.put(Phoenix.getInstance(), 0);
		holdingUnits.put(Priest.getInstance(), 0);
		holdingUnits.put(Pharaoh.getInstance(), 0);
		holdingUnits.put(Osiris.getInstance(), 0);
		holdingUnits.put(Mummy.getInstance(), 0);
		holdingUnits.put(Chariot.getInstance(), 0);
		holdingUnits.put(Spearman.getInstance(), 2);
		holdingUnits.put(Elephant.getInstance(), 2);
		
		// build UnitOnBoard table, the key is position of that unit
		unitsOnBoard.put(0, Scorpion.getInstance());
		unitsOnBoard.put(1, Sphinx.getInstance());
		unitsOnBoard.put(2, Wadjet.getInstance());
		unitsOnBoard.put(3, Anubite.getInstance());
		unitsOnBoard.put(4, Phoenix.getInstance());
		unitsOnBoard.put(5, Priest.getInstance());
		unitsOnBoard.put(6, Pharaoh.getInstance());
		unitsOnBoard.put(7, Osiris.getInstance());
		unitsOnBoard.put(8, Mummy.getInstance());
		unitsOnBoard.put(9, Chariot.getInstance());
		unitsOnBoard.put(10, Spearman.getInstance());
		unitsOnBoard.put(11, Elephant.getInstance());
		
	}
	
	private void InitialUnitsForGreek()
	{
		// initial units pool
		unitsPool.put(Cyclops.getInstance(), 4);
		unitsPool.put(Medusa.getInstance(), 4);
		unitsPool.put(Minotaur.getInstance(), 4);
		unitsPool.put(Centaur.getInstance(), 4);
		unitsPool.put(Hydra.getInstance(), 4);
		unitsPool.put(ClassicalGreekHero.getInstance(), 1);
		unitsPool.put(HeroicGreekHero.getInstance(), 1);
		unitsPool.put(MythicGreekHero.getInstance(), 1);
		unitsPool.put(Manticore.getInstance(), 4);
		unitsPool.put(Toxotes.getInstance(), 2);
		unitsPool.put(Hoplite.getInstance(), 2);
		unitsPool.put(Hippokon.getInstance(), 2);
		
		holdingUnits.put(Cyclops.getInstance(), 0);
		holdingUnits.put(Medusa.getInstance(), 0);
		holdingUnits.put(Minotaur.getInstance(), 0);
		holdingUnits.put(Centaur.getInstance(), 0);
		holdingUnits.put(Hydra.getInstance(), 0);
		holdingUnits.put(ClassicalGreekHero.getInstance(), 0);
		holdingUnits.put(HeroicGreekHero.getInstance(), 0);
		holdingUnits.put(MythicGreekHero.getInstance(), 0);
		holdingUnits.put(Manticore.getInstance(), 0);
		holdingUnits.put(Toxotes.getInstance(), 2);
		holdingUnits.put(Hoplite.getInstance(), 2);
		holdingUnits.put(Hippokon.getInstance(), 2);
		
		unitsOnBoard.put(0, Cyclops.getInstance());
		unitsOnBoard.put(1, Medusa.getInstance());
		unitsOnBoard.put(2, Minotaur.getInstance());
		unitsOnBoard.put(3, Centaur.getInstance());
		unitsOnBoard.put(4, Hydra.getInstance());
		unitsOnBoard.put(5, ClassicalGreekHero.getInstance());
		unitsOnBoard.put(6, HeroicGreekHero.getInstance());
		unitsOnBoard.put(7, MythicGreekHero.getInstance());
		unitsOnBoard.put(8, Manticore.getInstance());
		unitsOnBoard.put(9, Toxotes.getInstance());
		unitsOnBoard.put(10, Hoplite.getInstance());
		unitsOnBoard.put(11, Hippokon.getInstance());
	}
	
	private void InitialUnitsForNorse()
	{
		// initial units pool
		unitsPool.put(Valkyric.getInstance(), 4);
		unitsPool.put(Dwarf.getInstance(), 4);
		unitsPool.put(Troll.getInstance(), 4);
		unitsPool.put(Jarl.getInstance(), 2);
		unitsPool.put(Huskarl.getInstance(), 2);
		unitsPool.put(Throwing.getInstance(), 2);
		unitsPool.put(Nidhogg.getInstance(), 4);
		unitsPool.put(ClassicalNorseHero.getInstance(), 1);
		unitsPool.put(HeroicNorseHero.getInstance(), 1);
		unitsPool.put(MythicNorseHero.getInstance(), 1);
		unitsPool.put(ForestGiant.getInstance(), 4);
		
		holdingUnits.put(Valkyric.getInstance(), 0);
		holdingUnits.put(Dwarf.getInstance(), 0);
		holdingUnits.put(Troll.getInstance(), 0);
		holdingUnits.put(Jarl.getInstance(), 2);
		holdingUnits.put(Huskarl.getInstance(), 2);
		holdingUnits.put(Throwing.getInstance(), 2);
		holdingUnits.put(Nidhogg.getInstance(), 0);
		holdingUnits.put(ClassicalNorseHero.getInstance(), 0);
		holdingUnits.put(HeroicNorseHero.getInstance(), 0);
		holdingUnits.put(MythicNorseHero.getInstance(), 0);
		holdingUnits.put(ForestGiant.getInstance(), 0);
		
		unitsOnBoard.put(0, Valkyric.getInstance());
		unitsOnBoard.put(1, Dwarf.getInstance());
		unitsOnBoard.put(2, Troll.getInstance());
		unitsOnBoard.put(3, Jarl.getInstance());
		unitsOnBoard.put(4, Huskarl.getInstance());
		unitsOnBoard.put(5, Throwing.getInstance());
		unitsOnBoard.put(6, Nidhogg.getInstance());
		unitsOnBoard.put(7, ClassicalNorseHero.getInstance());
		unitsOnBoard.put(8, HeroicNorseHero.getInstance());
		unitsOnBoard.put(9, MythicNorseHero.getInstance());
		unitsOnBoard.put(10, ForestGiant.getInstance());
	}
	
	public void RemoveUnits(int ID)
	{
		int num = unitsPool.get(unitsOnBoard.get(ID));
		unitsPool.put(unitsOnBoard.get(ID), num + 1);
		holdingUnits.put(unitsOnBoard.get(ID), num - 1);
		
	}
	
	public void PlaceUnit(BattleCard unit)
	{
		int num = unitsPool.get(unit);
		unitsPool.put(unit, num - 1);
		holdingUnits.put(unit, num + 1);
	}
	
	public void RemoveBuilding(int row, int col)
	{
		cityOccupied[row][col] = false;
		constructedBuilding[row][col].UnBehavior();
		constructedBuilding[row][col] = null;
	}
	
	public void PlaceBuilding(Building build)
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(cityOccupied[row][col] == false)
				{
					cityOccupied[row][col] = true;
					constructedBuilding[row][col] = build;
					build.Behavior();
					return;
				}
			}
	}
	
	public void Explore(ResProduceTile pTile)
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(pTile.getTerrainType()  == terrainOnBoard[row][col]
						&& productionOccupied[row][col] == false)
				{
					productionOccupied[row][col] = true;
					productivity[row][col] = pTile.getProductivity();
					pTilesOnBoard[row][col] = pTile;
				}
					
			}	
	}
	
	// return value: actual amount removed from board
	// rt: required resource for removing
	public Hashtable<GlobalDef.Resources,Integer> RemoveResource(Hashtable<GlobalDef.Resources, Integer> rt)
	{
		return ResourceHandler.Delete(holdResource, rt);
	}
		
	public void PlaceResource(Hashtable<GlobalDef.Resources, Integer> res)
	{
		ResourceHandler.Add(holdResource, res);
	}
	
	// gatherType: 0 for gather by terrain. 1 for gather by resource type
	public Hashtable<GlobalDef.Resources, Integer> Gather(boolean gatherType, GlobalDef.Resources resType,
			GlobalDef.Terrain choosenType)
	{
		Hashtable<GlobalDef.Resources, Integer> gatheredRes =
				new Hashtable<GlobalDef.Resources, Integer>();
		gatheredRes.put(GlobalDef.Resources.FAVOR, 0);
		gatheredRes.put(GlobalDef.Resources.FOOD, 0);
		gatheredRes.put(GlobalDef.Resources.GOLD, 0);
		gatheredRes.put(GlobalDef.Resources.WOOD, 0);
		
		// gather by terrain type
		if(gatherType)
		{
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++)
				{
					if(productionOccupied[row][col] == true && pTilesOnBoard[row][col].getTerrainType() == choosenType)
					{
						ResourceHandler.Add(gatheredRes, pTilesOnBoard[row][col].getProductivity());
					}
				}
			return gatheredRes;
		}
		else // gathered by resource type
		{
			int numRes = 0;
			
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++)
				{
					GlobalDef.Resources currResType = pTilesOnBoard[row][col].getResourceType();
					if(currResType == resType)
					{
						numRes += pTilesOnBoard[row][col].getProductivity().get(resType);
					}
				}
			
			gatheredRes.put(resType, numRes);
			return gatheredRes;
		}
	}
	
}
