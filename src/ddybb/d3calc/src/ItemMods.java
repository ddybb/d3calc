package ddybb.d3calc.src;

public enum ItemMods {
	
	Dexterity(0, "Dexterity"),
	Intelligence(0, "Intelligence"),
	Strength(0, "Strength"),
	Vitality(0, "Vitality"),
	
	Life(0, "Life %"),
	Armor(0, "Armor"),
	AllRes(0, "Resist All"),
	PhysRes(0, "Phsyical Resist"),
	ColdRes(0, "Cold Resist"),
	FireRes(0, "Fire Resist"),
	LitRes(0, "Lightning Resist"),
	PoisonRes(0, "Poison Resist"),
	ArcRes(0, "Arcane Resist"),
	ReducM(0, "Reduced Melee Damage"),
	ReducR(0, "Reduced Ranged Damage"),
	
	CritChance(0, "Crit Chance"),
	CritDamage(0, "Crit Damage"),
	AtkSpeed(4, "Attack Speed"),
	//weapon only
	Dps(1, "DPS"),
	AtkSpeedBase(1, "Weapon Attack Speed"),
	//source only
	DamageMin(2, "Min Damage"),
	DamageMax(2, "Max Damage"),
	
	BlockChance(5, "Block Chance Bonus"),
	//shield only
	BlockChanceBase(3, "Shield Block Chance"),
	BlockAmount(3, "Block Amount"),
	BlockMax(3, "Block Amount Max");
	
	public int mask;
	public String modName;
	
	ItemMods(int mask, String modName) {
		this.mask = mask;
		this.modName = modName;
	}

}
