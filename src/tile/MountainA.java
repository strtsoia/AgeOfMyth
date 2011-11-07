package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class MountainA extends ResProduceTile{
	
	private static MountainA mountainA;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Mountains;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final int ID = 12;
	
	private MountainA()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 2);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static MountainA GetInstance()
	{
		if(mountainA == null){
			mountainA = new MountainA();
			return mountainA;
		}
		
		return mountainA;
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
