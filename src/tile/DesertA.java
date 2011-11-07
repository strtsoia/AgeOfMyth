package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class DesertA extends ResProduceTile{
	
	private static DesertA desertA;
	
	private final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Desert;
	
	private Hashtable<GlobalDef.Resources, Integer> productivity = 
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private final int ID = 15;
	
	private DesertA()
	{
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 2);
	}
	
	public static DesertA GetInstance()
	{
		if(desertA == null){
			desertA = new DesertA();
			return desertA;
		}
		
		return desertA;
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
