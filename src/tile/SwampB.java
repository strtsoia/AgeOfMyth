package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class SwampB extends ResProduceTile {

	private static SwampB swampB;

	/**
	 */
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Swamp;

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> productivity = new Hashtable<GlobalDef.Resources, Integer>();

	/**
	 */
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FOOD;

	/**
	 */
	private final int ID = 18;

	private SwampB() {
		productivity.put(GlobalDef.Resources.FOOD, 1);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 0);
	}

	public static SwampB GetInstance() {
		if (swampB == null) {
			swampB = new SwampB();
			return swampB;
		}

		return swampB;
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
