package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class HillsC extends ResProduceTile{
	
	private static HillsC hillsC;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Hills;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.WOOD;
	
	private final int ID = 10;
	
	private HillsC()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 1);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static HillsC GetInstance()
	{
		if(hillsC == null){
			hillsC = new HillsC();
			return hillsC;
		}
		
		return hillsC;
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
