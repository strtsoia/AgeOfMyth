package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class HillsD extends ResProduceTile{
	
	private static HillsD hillsD;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Hills;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FAVOR;
	
	private final int ID = 11;
	
	private HillsD()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}
	
	public static HillsD GetInstance()
	{
		if(hillsD == null){
			hillsD = new HillsD();
			return hillsD;
		}
		
		return hillsD;
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
