package building;

import global.GlobalDef;

import java.util.Hashtable;
import component.Culture;

public abstract class Building {
	
	public abstract void Behavior(Culture c);
	public abstract void UnBehavior(Culture c);
	public abstract Hashtable<GlobalDef.Resources, Integer> getCost();
}
