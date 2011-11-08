package building;

import global.GlobalDef;

import java.util.Hashtable;

public abstract class Building {
	
	public abstract void Behavior();
	public abstract void UnBehavior();
	public abstract Hashtable<GlobalDef.Resources, Integer> getCost();
}
