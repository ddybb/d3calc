package ddybb.d3calc.src;

public enum ItemType {
	
	Helm(0, 0, "Helm"),
	Shoulders(1, 0, "Shoulders"),
	Chest(2, 0, "Chest"),
	Pants(3, 0, "Pants"),
	Gloves(4, 0, "Gloves"),
	Bracers(5, 0, "Bracers"),
	Belt(6, 0, "Belt"),
	Boots(7, 0, "Boots"),
	Amulet(8, 2, "Amulet"),
	Ring1(9, 2, "Ring 1"),
	Ring2(10, 2, "Ring 2"),
	Weapon2H(11, 1, "Two Handed Weapon"),
	WeaponBow(11,1, "Bow/Crossbow"),
	Weapon1(11, 1, "Main Hand Weapon"),
	Weapon2(12, 1, "Off Hand Weapon"),
	Source(12, 2, "Source"),
	Shield(12, 3, "Shield"),
	Quiver(12, 0, "Quiver");
	
	public int slot;
	public int mask;
	public String name;
	
	ItemType(int slot, int mask, String name) {
		
		this.slot = slot;
		this.mask = mask;
		this.name = name;
		
	}

}
