package server.model.items;

public class ItemList {
	public int itemId;
	public String itemName;
	public String itemDescription;
	public double ShopValue;
	public double LowAlch;
	public double HighAlch;
	public int[] Bonuses = new int[100];

	public ItemList(int _itemId) {
		itemId = _itemId;
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getShopValue() {
		return ShopValue;
	}

	public void setShopValue(double shopValue) {
		ShopValue = shopValue;
	}

	public double getLowAlch() {
		return LowAlch;
	}

	public void setLowAlch(double lowAlch) {
		LowAlch = lowAlch;
	}

	public double getHighAlch() {
		return HighAlch;
	}

	public void setHighAlch(double highAlch) {
		HighAlch = highAlch;
	}

	public int[] getBonuses() {
		return Bonuses;
	}

	public void setBonuses(int[] bonuses) {
		Bonuses = bonuses;
	}
	
	
}
