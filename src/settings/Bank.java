package settings;

import global.GlobalDef;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import menuscene.PlayerScreen;

public class Bank {

	private static Bank bank;
	
	private Hashtable<GlobalDef.Resources, Integer> resourcePool = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.BuildingTiles, Integer> buildingPool =
			new Hashtable<GlobalDef.BuildingTiles, Integer>();
	private Hashtable<GlobalDef.ProductionTiles, Integer> productionPool =
			new Hashtable<GlobalDef.ProductionTiles, Integer>();
	
	// constructor
	private Bank()
	{
		// initial pool based on number of players
		int numOfPlayer = PlayerScreen.getNumber();
		switch(numOfPlayer)
		{
			case 2:
				InitialResourcePool(20);
				break;
			case 3:
				InitialResourcePool(25);
				break;
			case 4:
				InitialResourcePool(30);
				break;
		}
		
		InitialBuildingPool();
		InitialProductionPool();
	}
	
	// singleton pattern
	public static Bank getInstance()
	{
		if(bank == null){
			bank = new Bank();
			return bank;
		}
		else
			return bank;
	}
	
	// utility function
	private void InitialResourcePool(int res)
	{
		resourcePool.put(GlobalDef.Resources.FOOD, res);
		resourcePool.put(GlobalDef.Resources.WOOD, res);
		resourcePool.put(GlobalDef.Resources.GOLD, res);
		resourcePool.put(GlobalDef.Resources.FAVOR, res);
		resourcePool.put(GlobalDef.Resources.VICTORY, 30);
		
	}
	
	// utility function
	private void InitialBuildingPool()
	{
		buildingPool.put(GlobalDef.BuildingTiles.Wonder, 1);
		buildingPool.put(GlobalDef.BuildingTiles.Granary, 4);
		buildingPool.put(GlobalDef.BuildingTiles.Wall, 4);
		buildingPool.put(GlobalDef.BuildingTiles.Quarry, 4);
		buildingPool.put(GlobalDef.BuildingTiles.GoldMint, 3);
		buildingPool.put(GlobalDef.BuildingTiles.Market, 4);
		buildingPool.put(GlobalDef.BuildingTiles.Armory, 4);
		buildingPool.put(GlobalDef.BuildingTiles.SiegeEngineWorkshop, 4);
		buildingPool.put(GlobalDef.BuildingTiles.Monument, 4);
		buildingPool.put(GlobalDef.BuildingTiles.GreatTemple, 4);
		buildingPool.put(GlobalDef.BuildingTiles.StoreHouse, 4);
		buildingPool.put(GlobalDef.BuildingTiles.WoodWorkshop, 4);
		buildingPool.put(GlobalDef.BuildingTiles.Tower, 4);
		buildingPool.put(GlobalDef.BuildingTiles.House, 40);
		
	}
	
	private void InitialProductionPool()
	{
		productionPool.put(GlobalDef.ProductionTiles.FertileA, 12);
		productionPool.put(GlobalDef.ProductionTiles.FertileB, 3);
		productionPool.put(GlobalDef.ProductionTiles.FertileC, 3);
		productionPool.put(GlobalDef.ProductionTiles.FertileD, 3);
		productionPool.put(GlobalDef.ProductionTiles.FertileA, 9);
		productionPool.put(GlobalDef.ProductionTiles.FertileB, 2);
		productionPool.put(GlobalDef.ProductionTiles.FertileC, 2);
		productionPool.put(GlobalDef.ProductionTiles.FertileD, 2);
		productionPool.put(GlobalDef.ProductionTiles.HillA, 4);
		productionPool.put(GlobalDef.ProductionTiles.HillB, 4);
		productionPool.put(GlobalDef.ProductionTiles.HillC, 4);
		productionPool.put(GlobalDef.ProductionTiles.HillD, 4);
		productionPool.put(GlobalDef.ProductionTiles.MountainA, 6);
		productionPool.put(GlobalDef.ProductionTiles.MountainB, 3);
		productionPool.put(GlobalDef.ProductionTiles.MountainC, 3);
		productionPool.put(GlobalDef.ProductionTiles.DesertA, 7);
		productionPool.put(GlobalDef.ProductionTiles.DesertB, 7);
		productionPool.put(GlobalDef.ProductionTiles.SwampA, 4);
		productionPool.put(GlobalDef.ProductionTiles.SwampB, 4);
		productionPool.put(GlobalDef.ProductionTiles.SwampC, 4);
		
	}
	
	/*// add resource to resource pool
	public void AddToResPool(Hashtable<GlobalDef.Resources, Integer> ht)
	{
		Set<GlobalDef.Resources> kSet = ht.keySet();
		Iterator<GlobalDef.Resources> iter = kSet.iterator();
		
		//iterate all resource and add to pool
		while(iter.hasNext()){
			GlobalDef.Resources res = iter.next();
			int num = ht.get(res);
			int numPool = resourcePool.get(res);
			resourcePool.put(res, num + numPool);
		}
	}
	
	// remove from resource pool. note: after this call, parameter ht may change
	// since it reflects actual resource get from resource pool. E.g, request resource
	// may be 5 cubes, but resource pool only has 3, so ht will change to store 3 after
	// this call.
	public void RemoveFromResPool(Hashtable<GlobalDef.Resources, Integer> ht)
	{
		Set<GlobalDef.Resources> kSet = ht.keySet();
		Iterator<GlobalDef.Resources> iter = kSet.iterator();
		
		//iterate all resource and remove from pool
		while(iter.hasNext()){
			GlobalDef.Resources res = iter.next();
			int num = ht.get(res);
			int numPool = resourcePool.get(res);
			
			if(numPool < num){
				resourcePool.put(res, 0);
				ht.put(res, numPool);
			}
			else{
				resourcePool.put(res, numPool - num);
			}
		}
	}
*/
}
