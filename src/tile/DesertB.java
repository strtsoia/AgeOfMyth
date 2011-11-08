package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class DesertB extends ResProduceTile{
	
	private static DesertB desertB;
	
	public static final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Desert;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private GlobalDef.Resources resourceType = GlobalDef.Resources.GOLD;
	private final int ID = 16;
	
	private DesertB()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 1);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static DesertB GetInstance()
	{
		if(desertB == null){
			desertB = new DesertB();
			return desertB;
		}
		
		return desertB;
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
