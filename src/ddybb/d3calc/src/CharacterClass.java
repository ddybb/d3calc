package ddybb.d3calc.src;

public enum CharacterClass {
	
	Barbarian("Barbarian"),
	DemonHunter("Demon Hunter"),
	Monk("Monk"),
	WitchDoctor("Witch Doctor"),
	Wizard("Wizard"),
	None("");
	
	public String className;
	
	CharacterClass(String className) {
		this.className = className;
	}

}
