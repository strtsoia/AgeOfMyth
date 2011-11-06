package settings;

import global.GlobalDef;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import building.Building;
import building.Armory;
import building.Garnary;
import building.GoldMint;
import building.GreatTemple;
import building.House;
import building.Market;
import building.Monument;
import building.Quarry;
import building.SiegeEngineWorkshop;
import building.StoreHouse;
import building.Tower;
import building.Wall;
import building.Wonder;
import building.WoodWorkshop;

import menuscene.PlayerScreen;

public class Bank {

	private static Bank bank;
	
	private Hashtable<GlobalDef.Resources, Integer> resourcePool = 
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<Building, Integer> buildingPool =
			new Hashtable<Building, Integer>();
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
		buildingPool.put(Armory.GetInstance(), 1);
		buildingPool.put(Garnary.GetInstance(), 4);
		buildingPool.put(Wall.GetInstance(), 4);
		buildingPool.put(Quarry.GetInstance(), 4);
		buildingPool.put(GoldMint.GetInstance(), 3);
		buildingPool.put(Market.GetInstance(), 4);
		buildingPool.put(Wonder.GetInstance(), 4);
		buildingPool.put(SiegeEngineWorkshop.GetInstance(), 4);
		buildingPool.put(Monument.GetInstance(), 4);
		buildingPool.put(GreatTemple.GetInstance(), 4);
		buildingPool.put(StoreHouse.GetInstance(), 4);
		buildingPool.put(WoodWorkshop.GetInstance(), 4);
		buildingPool.put(Tower.GetInstance(), 4);
		buildingPool.put(House.GetInstance(), 40);
		
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
	
	public void UpdateResourcePool(Hashtable<GlobalDef.Resources, Integer> table)
	{
		this.resourcePool = table;
	}
	
	public void UpdateBuildingPool(Hashtable<Building, Integer> table)
	{
		this.buildingPool = table;
	}
	
	public void UpdateProductionPool(Hashtable<GlobalDef.ProductionTiles, Integer> table)
	{
		this.productionPool = table;
	}
	
}
