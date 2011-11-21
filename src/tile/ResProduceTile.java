package tile;

import java.util.Hashtable;
import global.GlobalDef;

/**
 * @author strtsoia
 */
public abstract class ResProduceTile {

	/**
	 */
	public abstract GlobalDef.Terrain getTerrainType();

	public abstract Hashtable<GlobalDef.Resources, Integer> getProductivity();

	public abstract int GetID();

	/**
	 */
	public abstract GlobalDef.Resources getResourceType();
}
