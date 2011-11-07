package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class ForestC extends ResProduceTile{
	
	private static ForestC forestC;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Forest;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private ForestC()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 1);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static ForestC GetInstance()
	{
		if(forestC == null){
			forestC = new ForestC();
			return forestC;
		}
		
		return forestC;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
