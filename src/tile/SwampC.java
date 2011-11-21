package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class SwampC extends ResProduceTile {

	private static SwampC swampC;

	/**
	 */
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Swamp;

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> productivity = new Hashtable<GlobalDef.Resources, Integer>();

	/**
	 */
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FAVOR;

	/**
	 */
	private final int ID = 19;

	private SwampC() {
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}

	public static SwampC GetInstance() {
		if (swampC == null) {
			swampC = new SwampC();
			return swampC;
		}

		return swampC;
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
