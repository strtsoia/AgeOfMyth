package tile;

import global.GlobalDef;

import java.util.Hashtable;

public final class ForestD extends ResProduceTile {

	private static ForestD forestD;

	/**
	 */
	public final GlobalDef.Terrain terrainType = GlobalDef.Terrain.Forest;

	/**
	 */
	private Hashtable<GlobalDef.Resources, Integer> productivity = new Hashtable<GlobalDef.Resources, Integer>();

	/**
	 */
	private final GlobalDef.Resources resourceType = GlobalDef.Resources.FAVOR;

	/**
	 */
	private final int ID = 7;

	private ForestD() {
		productivity.put(GlobalDef.Resources.FOOD, 0);
		productivity.put(GlobalDef.Resources.GOLD, 0);
		productivity.put(GlobalDef.Resources.WOOD, 0);
		productivity.put(GlobalDef.Resources.FAVOR, 1);
	}

	public static ForestD GetInstance() {
		if (forestD == null) {
			forestD = new ForestD();
			return forestD;
		}

		return forestD;
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
