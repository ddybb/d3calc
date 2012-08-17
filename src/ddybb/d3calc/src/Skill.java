package ddybb.d3calc.src;


public enum Skill {
	
	//barb
	nails(0, false, "Tough as Nails"),
	nos(0, false, "Nerves of Steel"),
	war(0, false, "War Cry"),
	warImp(0, false, "WC/Impunity"),
	warInv(0, false, "WC/Invigorate"),
	warHW(0, false, "WC/Hardened Wrath"),
	warVW(0, false, "WC/Veteran's Warning"),
	threat(0, false, "Threatening Shout"),
	threatF(0, false, "TS/Falter"),
	
	ruth(0, false, "Ruthless"),
	weapS(0, false, "WM(Sword/Dagger)"),
	weapM(0, false, "WM(Mace/Axe)"),
	weapP(0, false, "WM(Spear/Polearm)"),
	
	//dh
	steady(1, false, "Steady Shot"),
	arcB(1, false, "Archery(Bow)"),
	arcX(1, false, "Archery(Crossbow)"),
	arcH(1, false, "Archery(Hand xBow)"),
	
	//monk
	owe(2, false, "One With Everything"),
	sti(2, false, "Sieze the Iniative"),
	moe(2, false, "Mantra of Evasion"),
	moeHT(2, false, "MoE/Hard Target"),
	mohTN(2, false, "MoH/Time of Need"),
	resolve(2, false, "Resolve"),
	gp(2, false, "Guardian's Path"),
	sixS(2, false, "Sixth Sense"),
	
	//wd
	jFort(3, false, "Jungle Fortitude"),
	bad(3, false, "Bad Medicine"),
	pierce(3, false, "Pierce the Veil"),
	
	//wiz
	//blur(4, false, "Blur"),
	energy(4, false, "Energy Armor"),
	energyP(4, false, "EA/Prismatic Armor"),
	energyB(4, false, "EA/Pinpoint Barrier"),
	glass(4, false, "Glass Cannon"),
	magic(4, false, "Magic Weapon"),
	magicF(4, false, "MW/Force Weapon"),
	fam(4, false, "Familiar/Sparkflint");
	
	
	public int cMask;
	public boolean flag;
	public String title;
	
	Skill(int cMask, boolean flag, String title) {
		this.cMask = cMask;
		this.flag = flag;
		this.title = title;
	}

}
