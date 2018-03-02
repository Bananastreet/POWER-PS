package com.powerps;
final class Item extends Animable {

	public final Model getRotatedModel() {
		ItemDef itemDef = ItemDef.forID(ID);
		return itemDef.getModel(itemCount);
	}

	public Item() {
	}

	public int ID;
	public int x;
	public int y;
	public int itemCount;
}
