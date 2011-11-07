package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class ForestD extends ResProduceTile{
	
	private static ForestD forestD;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Forest;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private ForestD()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}
	
	public static ForestD GetInstance()
	{
		if(forestD == null){
			forestD = new ForestD();
			return forestD;
		}
		
		return forestD;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
