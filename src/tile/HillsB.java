package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class HillsB extends ResProduceTile{
	
	private static HillsB hillsB;
	
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Hills;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final int ID = 9;
	
	private HillsB()
	{
		productivity.put(GlobalDef.Resources.FOOD, 1);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}
	
	public static HillsB GetInstance()
	{
		if(hillsB == null){
			hillsB = new HillsB();
			return hillsB;
		}
		
		return hillsB;
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
