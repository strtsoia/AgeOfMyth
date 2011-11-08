package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class ForestA extends ResProduceTile{
	
	private static ForestA forestA;
	
	public static final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Forest;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.WOOD;
	
	private final int ID = 4;
	
	private ForestA()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 2);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static ForestA GetInstance()
	{
		if(forestA == null){
			forestA = new ForestA();
			return forestA;
		}
		
		return forestA;
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
	
	public GlobalDef.Resources getResourceType()
	{
		return resourceType;
	}
}
