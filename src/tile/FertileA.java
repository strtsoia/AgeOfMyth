package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class FertileA extends ResProduceTile {

	private static FertileA fertileA;

	/**
	 */
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Fertile;

	private static Hashtable<GlobalDef.Resources, Integer> productivity = new Hashtable<GlobalDef.Resources, Integer>();

	/**
	 */
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FOOD;
	/**
	 */
	private final int ID = 0;

	private FertileA() {
		productivity.put(GlobalDef.Resources.FOOD, 2);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}

	public static FertileA GetInstance() {
		if (fertileA == null) {
			fertileA = new FertileA();
			return fertileA;
		}

		return fertileA;
	}

	/**
	 * @return
	 */
	public GlobalDef.Terrain getTerrainType() {
		return terrainType;
	}

	public Hashtable<GlobalDef.Resources, Integer> getProductivity() {
		return productivity;
	}

	public int GetID() {
		return ID;
	}

	/**
	 * @return
	 */
	public GlobalDef.Resources getResourceType() {
		return resourceType;
	}
}
