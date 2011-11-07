package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class SwampA extends ResProduceTile{
	
	private static SwampA swampA;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Swamp;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private SwampA()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 1);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static SwampA GetInstance()
	{
		if(swampA == null){
			swampA = new SwampA();
			return swampA;
		}
		
		return swampA;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
