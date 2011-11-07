package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class FertileB extends ResProduceTile{
	
	private static FertileB fertileB;
	
	public static final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Fertile;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private FertileB()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 1);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static FertileB GetInstance()
	{
		if(fertileB == null){
			fertileB = new FertileB();
			return fertileB;
		}
		
		return fertileB;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
