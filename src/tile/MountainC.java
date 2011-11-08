package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class MountainC extends ResProduceTile{
	
	private static MountainC mountainC;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Mountains;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FAVOR;
	
	private final int ID = 14;
	
	private MountainC()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}
	
	public static MountainC GetInstance()
	{
		if(mountainC == null){
			mountainC = new MountainC();
			return mountainC;
		}
		
		return mountainC;
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
