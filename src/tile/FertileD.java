package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class FertileD extends ResProduceTile{
	
	private static FertileD fertileD;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Fertile;
	
	private static Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final int ID = 3;
	
	private FertileD()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 1);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static FertileD GetInstance()
	{
		if(fertileD == null){
			fertileD = new FertileD();
			return fertileD;
		}
		
		return fertileD;
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
