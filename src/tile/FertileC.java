package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class FertileC extends ResProduceTile{
	
	private static FertileC fertileC;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Fertile;
	
	private static Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private FertileC()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}
	
	public static FertileC GetInstance()
	{
		if(fertileC == null){
			fertileC = new FertileC();
			return fertileC;
		}
		
		return fertileC;
	}

	public GlobalDef.Terrain getTerrainType()
	{
		return terrainType;
	}
	
	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

}
