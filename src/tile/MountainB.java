package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class MountainB extends ResProduceTile{
	
	private static MountainB mountainB;
	
	public static final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Mountains;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private MountainB()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 1);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static MountainB GetInstance()
	{
		if(mountainB == null){
			mountainB = new MountainB();
			return mountainB;
		}
		
		return mountainB;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
