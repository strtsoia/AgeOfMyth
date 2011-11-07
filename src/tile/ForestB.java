package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class ForestB extends ResProduceTile{
	
	private static ForestB forestB;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Forest;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final int ID = 5;
	
	private ForestB()
	{
		productivity.put(GlobalDef.Resources.FOOD, 1);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static ForestB GetInstance()
	{
		if(forestB == null){
			forestB = new ForestB();
			return forestB;
		}
		
		return forestB;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}
	
	public int GetID()
	{
		return ID;
	}
}
