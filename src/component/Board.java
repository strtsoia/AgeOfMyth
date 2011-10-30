package component;

import global.GlobalDef;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

public abstract class Board {

	boolean[][] productionOccupied = new boolean[4][4];
	// different culture has different board terrain,must be determined by subclass
	GlobalDef.Terrain[][] boardTerrain = new GlobalDef.Terrain[4][4];
	Hashtable<GlobalDef.Resources,Integer>[][] productivity = 
			new Hashtable[4][4];
	
	boolean[][] cityOccupied = new boolean[4][4];
	// actual tile in building area
	Tile[][] constructedBuilding = new Tile[4][4];
	Hashtable<GlobalDef.Resources, Integer> holdingAreaResource = new Hashtable<GlobalDef.Resources, Integer>();
	Hashtable<GlobalDef.Unit, Integer> holdingAreaUnits = new Hashtable<GlobalDef.Unit, Integer>();
	
	// different culture has different board terrain
	abstract void InitialBoardTerrain();
	
	public void Explore(Tile ptile)
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(ptile.equals(boardTerrain[row][col]))
					productionOccupied[row][col] = true;
			}	
	}
	
	// 0: gather by terrain. 1: gather by resource type
	public Hashtable<GlobalDef.Resources, Integer> Gather(boolean gatherType, GlobalDef.Resources resType,
			GlobalDef.Terrain choosentype)
	{
		Hashtable<GlobalDef.Resources, Integer> gatheredRes =
				new Hashtable<GlobalDef.Resources, Integer>();
		
		// gathered by resource type
		if(gatherType)
		{
			int numRes = 0;
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++)
				{
					if(productionOccupied[row][col] == true && productivity[row][col].containsKey(resType))
					{
						numRes += productivity[row][col].get(resType);
					}
					
				}
			gatheredRes.put(resType, numRes);
			return gatheredRes;
		}
		else
		{// gathered by terrain type
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++)
				{
					if(productionOccupied[row][col] == true && boardTerrain[row][col].equals(choosentype))
					{
						Set<GlobalDef.Resources> key = productivity[row][col].keySet();
						Iterator<GlobalDef.Resources> iter = key.iterator();
						GlobalDef.Resources kRes = iter.next();
						int num = productivity[row][col].get(kRes);
						gatheredRes.put(kRes, num);
					}
				}
			return gatheredRes;
		}
	}
	
	public void RemoveBuilding(int row, int col)
	{
		cityOccupied[row][col] = false;
		constructedBuilding[row][col] = null;
	}
	
	public void RemoveUnits(GlobalDef.Unit unit, int numRemove)
	{
		int num = holdingAreaUnits.get(unit);
		num = num - numRemove;
		holdingAreaUnits.put(unit, num);
		
	}
	
	public void RemoveResource(GlobalDef.Resources res, int numRemove)
	{
		int num = holdingAreaResource.get(res);
		num = num - numRemove;
		holdingAreaResource.put(res, num);
	}

	public void PlaceBuilding(Tile bTile)
	{
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(cityOccupied[row][col] == false)
				{
					cityOccupied[row][col] = true;
					constructedBuilding[row][col] = bTile;
				}
			}
	}
	
	public void PlaceUnit(GlobalDef.Unit unit, int numAdd)
	{
		int num = holdingAreaUnits.get(unit);
		num = num + numAdd;
		holdingAreaUnits.put(unit, num);
	}
	
	public void PlaceResource(Hashtable<GlobalDef.Resources, Integer> hRes)
	{
		Set<GlobalDef.Resources> kSet = hRes.keySet();
		Iterator<GlobalDef.Resources> iter = kSet.iterator();
		
		while(iter.hasNext()){
			GlobalDef.Resources rs = iter.next();
			int num = hRes.get(rs);
			int numHold = holdingAreaResource.get(rs);
			holdingAreaResource.put(rs, num + numHold);
		}
	}
}
