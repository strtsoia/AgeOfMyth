package settings;

import global.GlobalDef;

import java.util.Hashtable;

import building.*;
import tile.*;

import menuscene.PlayerScreen;

public class Bank {

	private static Bank bank;

	private Hashtable<GlobalDef.Resources, Integer> resourcePool = new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<Building, Integer> buildingPool = new Hashtable<Building, Integer>();
	private Hashtable<ResProduceTile, Integer> productionPool = new Hashtable<ResProduceTile, Integer>();
	private Hashtable<Integer, Integer> vpcOnCards = new Hashtable<Integer, Integer>();

	// constructor
	private Bank() {
		// initial pool based on number of players
		int numOfPlayer = PlayerScreen.getNumber();
		switch (numOfPlayer) {
		case 2:
			InitialResourcePool(20);
			break;
		case 3:
			InitialResourcePool(25);
			break;
		case 4:
			InitialResourcePool(30);
			break;
		default:// use for debug
			InitialResourcePool(80);
			break;
		}

		InitialBuildingPool();
		InitialProductionPool();
		InitialVPCOnCard();

	}

	// singleton pattern
	public static Bank getInstance() {
		if (bank == null) {
			bank = new Bank();
			return bank;
		} else
			return bank;
	}

	// utility function
	private void InitialResourcePool(int res) {
		resourcePool.put(GlobalDef.Resources.FOOD, res);
		resourcePool.put(GlobalDef.Resources.WOOD, res);
		resourcePool.put(GlobalDef.Resources.GOLD, res);
		resourcePool.put(GlobalDef.Resources.FAVOR, res);
		resourcePool.put(GlobalDef.Resources.VICTORY, 30);

	}

	// utility function
	private void InitialBuildingPool() {
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

	private void InitialProductionPool() {
		productionPool.put(FertileA.GetInstance(), 12);
		productionPool.put(FertileB.GetInstance(), 3);
		productionPool.put(FertileC.GetInstance(), 3);
		productionPool.put(FertileD.GetInstance(), 3);
		productionPool.put(ForestA.GetInstance(), 9);
		productionPool.put(ForestB.GetInstance(), 2);
		productionPool.put(ForestC.GetInstance(), 2);
		productionPool.put(ForestD.GetInstance(), 2);
		productionPool.put(HillsA.GetInstance(), 4);
		productionPool.put(HillsB.GetInstance(), 4);
		productionPool.put(HillsC.GetInstance(), 4);
		productionPool.put(HillsD.GetInstance(), 4);
		productionPool.put(MountainA.GetInstance(), 6);
		productionPool.put(MountainB.GetInstance(), 3);
		productionPool.put(MountainC.GetInstance(), 3);
		productionPool.put(DesertA.GetInstance(), 7);
		productionPool.put(DesertB.GetInstance(), 7);
		productionPool.put(SwampA.GetInstance(), 4);
		productionPool.put(SwampB.GetInstance(), 4);
		productionPool.put(SwampC.GetInstance(), 4);

	}
	
	private void InitialVPCOnCard()
	{
		vpcOnCards.put(0, 0);
		vpcOnCards.put(1, 0);
		vpcOnCards.put(2, 0);
		vpcOnCards.put(3, 0);
	}
	
	public void setResourcePool(Hashtable<GlobalDef.Resources, Integer> resPool) {
		resourcePool = resPool;
	}

	public Hashtable<GlobalDef.Resources, Integer> getResourcePool() {
		return resourcePool;
	}

	public Hashtable<Building, Integer> getBuildingPool() {
		return buildingPool;
	}

	public void setBuildingPool(Hashtable<Building, Integer> buildingPool) {
		this.buildingPool = buildingPool;
	}

	public Hashtable<ResProduceTile, Integer> getProductionPool() {
		return productionPool;
	}

	public void setProductionPool(
			Hashtable<ResProduceTile, Integer> productionPool) {
		this.productionPool = productionPool;
	}

	public Hashtable<Integer, Integer> getVpcOnCards() {
		return vpcOnCards;
	}

	public void setVpcOnCards(Hashtable<Integer, Integer> vpcOnCards) {
		this.vpcOnCards = vpcOnCards;
	}
	
	

}
