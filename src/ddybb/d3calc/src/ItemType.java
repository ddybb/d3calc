package ddybb.d3calc.src;

public enum ItemType {
	
	Helm(0, 0, 0, "Helm"),
	Shoulders(1, 1, 0, "Shoulders"),
	Chest(2, 2, 0, "Chest"),
	Pants(3, 3, 0, "Pants"),
	Gloves(4, 4, 0, "Gloves"),
	Bracers(5, 5, 0, "Bracers"),
	Belt(6, 6, 0, "Belt"),
	Boots(7, 7, 0, "Boots"),
	Amulet(8, 8, 2, "Amulet"),
	Ring1(9, 9, 2, "Ring 1"),
	Ring2(10, 10, 2, "Ring 2"),
	Weapon2H(11, 11, 1, "Two Handed Weapon"),
	WeaponBow(12, 11, 1, "Bow/Crossbow"),
	Weapon1(13, 11, 1, "Main Hand Weapon"),
	Weapon2(14, 12, 1, "Off Hand Weapon"),
	Source(15, 12, 2, "Source"),
	Shield(16, 12, 3, "Shield"),
	Quiver(17, 12, 0, "Quiver");
	
	public int index;
	public int slot;
	public int mask;
	public String name;
	
	ItemType(int index, int slot, int mask, String name) {
		this.index = index;
		this.slot = slot;
		this.mask = mask;
		this.name = name;
		
	}
	
	public static ItemType getTypeByIndex(int i) {
		ItemType type;
		switch (i) {
        case 0: type = ItemType.Helm; break;
        case 1: type = ItemType.Shoulders; break;
        case 2: type = ItemType.Chest; break;
        case 3: type = ItemType.Pants; break;
        case 4: type = ItemType.Gloves; break;
        case 5: type = ItemType.Bracers; break;
        case 6: type = ItemType.Belt; break;
        case 7: type = ItemType.Boots; break;
        case 8: type = ItemType.Amulet; break;
        case 9: type = ItemType.Ring1; break;
        case 10: type = ItemType.Ring2; break;
        case 11: type = ItemType.Weapon2H; break;
        case 12: type = ItemType.WeaponBow; break;
        case 13: type = ItemType.Weapon1; break;
        case 14: type = ItemType.Weapon2; break;
        case 15: type = ItemType.Source; break;
        case 16: type = ItemType.Shield; break;
        case 17: type = ItemType.Quiver; break;
        default: type = null; break;
		}
        return type;
	}
	
	public static ItemType getTypeBySlot(int i) {
		ItemType type;
		switch (i) {
        case 0: type = ItemType.Helm; break;
        case 1: type = ItemType.Shoulders; break;
        case 2: type = ItemType.Chest; break;
        case 3: type = ItemType.Pants; break;
        case 4: type = ItemType.Gloves; break;
        case 5: type = ItemType.Bracers; break;
        case 6: type = ItemType.Belt; break;
        case 7: type = ItemType.Boots; break;
        case 8: type = ItemType.Amulet; break;
        case 9: type = ItemType.Ring1; break;
        case 10: type = ItemType.Ring2; break;
        case 11: type = ItemType.Weapon1; break;
        case 12: type = ItemType.Weapon2; break;
        default: type = null; break;
		}
        return type;
	}

}
