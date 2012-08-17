package ddybb.d3calc.src;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 2L;
	public int dexterity = 0;
	public int intelligence = 0;
	public int strength = 0;
	public int vitality = 0;
	
	public int life = 0;
	public int armor = 0;
	public int allRes = 0;
	public int physRes = 0;
	public int coldRes = 0;
	public int fireRes = 0;
	public int litRes = 0;
	public int poisonRes = 0;
	public int arcRes = 0;
	public int reducM = 0;
	public int reducR = 0;
	
	public float critChance = 0f;
	public int critDamage = 0;
	public int atkSpeed = 0;
	//weapon only
	public float dps = 0f;
	public float atkSpeedBase = 0f;
	//source only
	public int damageMin = 0;
	public int damageMax = 0;
	public float damageAvg = 0f;
	
	public boolean twoHanded = false;
	
	public int blockChance = 0;
	//shield only
	public float blockChanceBase = 0f;
	public int blockAmount = 0;
	
	public ItemType type;
	public ItemMods[] mods = new ItemMods[8];
	
	public Item(ItemType type) {
		
		this.type = type;
		
	}
	
	public Object getValueByMod(ItemMods mod) {
		
		switch (mod) {
		case Dexterity: return dexterity;
		case Intelligence: return intelligence;
		case Strength: return strength;
		case Vitality: return vitality;
		case Armor: return armor;
		case AllRes: return allRes;
		case PhysRes: return physRes;
		case ColdRes: return coldRes;
		case FireRes: return fireRes;
		case LitRes: return litRes;
		case PoisonRes: return poisonRes;
		case ArcRes: return arcRes;
		case Life: return arcRes;
		case CritChance: return critChance;
		case CritDamage: return critDamage;
		case AtkSpeed: return atkSpeed;
		case Dps: return dps;
		case AtkSpeedBase: return atkSpeedBase;
		case DamageMin: return damageMin;
		case DamageMax: return damageMax;
		case BlockChance: return blockChance;
		case BlockChanceBase: return blockChanceBase;
		case BlockAmount: return blockAmount;
		case ReducM: return reducM;
		case ReducR: return reducR;
		default: return 0f;
		}
		
	}

}
