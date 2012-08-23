package ddybb.d3calc.src;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class D3Calc {
	
	public static Display display;
	public static Shell shell;
	public static Character activeChar = new Character("name", CharacterClass.None);
	public static Character compareChar = new Character("name", CharacterClass.None);
	private Composite main;
	public static boolean tempFlag = false;
	
	private final String[] slots = { "Helm", "Shoulder", "Chest", "Pants", "Gloves", "Bracers",
			"Belt", "Boots", "Amulet", "Ring 1", "Ring 2", "Main Hand", "Off Hand" };

	public D3Calc(Display display) {
		
		shell = new Shell(display);
        shell.setText("D3 Gear Calculator v1.1");
        shell.setSize(800, 600);
        shell.setLocation(300, 300);
        
        initMenu();

        shell.open();

        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
            display.sleep();
        	}
        }
	}
	
	private void drawUI() {
		
		//destroy old screen
		if (main != null) { main.dispose(); }
		
		//overall layout
		GridLayout layout = new GridLayout(4, false);
		main = new Composite(shell, SWT.NONE);
		main.setLayout(layout);
		
		//draw nameplate
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true,
				false, 4, 1);
		Label label = new Label(main, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(gridData);
		label = new Label(main, SWT.NONE);
		label.setText(activeChar.name + ", " + activeChar.characterClass.className + " (Paragon Level " + activeChar.paragon + ")");
		gridData = new GridData(GridData.CENTER, GridData.CENTER, false,
				false, 4, 1);
		label.setLayoutData(gridData);
		label = new Label(main, SWT.SEPARATOR | SWT.HORIZONTAL);
		gridData = new GridData(GridData.FILL, GridData.CENTER, true,
				false, 4, 1);
		label.setLayoutData(gridData);
		
		//draw character info and compare panes
		drawInfoPane("Current Stats", activeChar);
		drawInfoPane("Compare Stats", compareChar);
		
		//draw gear panes
		drawGearPane("Current Gear", activeChar);
		drawGearPane("Compare Gear", compareChar);
		
		//draw skillboxes
		drawSkills();
		
		main.pack();
		shell.pack();
	}
	
	private void initMenu() {
		Menu menuBar = new Menu(shell, SWT.BAR);
        
        //draw file menu
		
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&File");
        
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);

        MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
        newItem.setText("&New");
        newItem.setAccelerator(SWT.MOD1 | 'N');
        
        MenuItem impItem = new MenuItem(fileMenu, SWT.PUSH); 
        impItem.setText("Import");
        impItem.setAccelerator(SWT.MOD1 | 'I');
        
        MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
        saveItem.setText("&Save");
        saveItem.setAccelerator(SWT.MOD1 | 'S');
        
        MenuItem loadItem = new MenuItem(fileMenu, SWT.PUSH);
        loadItem.setText("&Load");
        loadItem.setAccelerator(SWT.MOD1 | 'L');
        
        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText("E&xit");
        exitItem.setAccelerator(SWT.MOD1 | 'X');
        
        //draw edit menu
        
        cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&Edit");
        
        Menu editMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(editMenu);

        MenuItem addItem = new MenuItem(editMenu, SWT.PUSH);
        addItem.setText("&Add Item");
        
        MenuItem copyItem = new MenuItem(editMenu, SWT.PUSH);
        copyItem.setText("&Copy Compare to Current");
        
        MenuItem resetItem = new MenuItem(editMenu, SWT.PUSH);
        resetItem.setText("C&opy Current to Compare");
        
        MenuItem tempItem = new MenuItem(editMenu, SWT.PUSH);
        tempItem.setText("&Set Paragon Level");
        
        shell.setMenuBar(menuBar);
        
        //add overrides
        
        newItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new CharacterBuilder(display);
                drawUI();
            }
        });
        
        impItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new ProfileImporter(display);
                compareChar = new Character ("", activeChar.characterClass);
                for (int i = 0; i < compareChar.equip.length; i ++) {
            		if (activeChar.equip[i] != null) {
            			compareChar.equip[i] = activeChar.equip[i];
            		}
            	}
                activeChar.update();
                compareChar.update();
                drawUI();
            }
        });
        
        saveItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SaveBot.save(activeChar);
            }
        });
        
        loadItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	FileDialog dialog = new FileDialog(shell, SWT.OPEN);
                String[] name = { "Character Files" };
                String[] extension = { "*.cha" };
            	dialog.setFilterPath(".");
                dialog.setFilterNames(name);
                dialog.setFilterExtensions(extension);
                String path = dialog.open();
                if (path != null) {
                	SaveBot.load(path);
                	compareChar = new Character ("", activeChar.characterClass);
                    for (int i = 0; i < compareChar.equip.length; i ++) {
                		if (activeChar.equip[i] != null) {
                			compareChar.equip[i] = activeChar.equip[i];
                		}
                	}
                    compareChar.paragon = activeChar.paragon;
                    compareChar.update();
                    drawUI();
                }
            }
        });
        
        exitItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.getDisplay().dispose();
                System.exit(0);
            }
        });
        
        addItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new ItemBuilder(display);
                drawUI();
            }
        });
        
        copyItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	for (int i = 0; i < compareChar.equip.length; i ++) {
            		if (compareChar.equip[i] != null) {
            			activeChar.equip[i] = compareChar.equip[i];
            		}
            	}
                activeChar.update();
                drawUI();
            }
        });
        
        resetItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	for (int i = 0; i < compareChar.equip.length; i ++) {
            		if (activeChar.equip[i] != null) {
            			compareChar.equip[i] = activeChar.equip[i];
            		}
            	}
                compareChar.update();
                drawUI();
            }
        });
        
        tempItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	new Paragon(display);
            	activeChar.update();
                compareChar.update();
                drawUI();
            }
        });
        
        
	}
	
	private void drawGearPane(String title, Character c) {
		Group groupMain = new Group(main, SWT.NONE);
		groupMain.setText(title);
		groupMain.setLayout(new GridLayout(3, false));
		Group group;
		for(int i = 0; i < slots.length; i++) {
			group = new Group(groupMain, SWT.NONE);
			group.setText(slots[i]);
			group.setLayout(new GridLayout(2, false));
			if (c.equip[i] != null) {
				for (ItemMods mod : c.equip[i].mods) {
					if (mod != null) {
						Text text = new Text(group, SWT.READ_ONLY);
						text.setText(mod.modName);
						text = new Text(group, SWT.READ_ONLY);
						text.setText(c.equip[i].getValueByMod(mod).toString());
					}
				}
			}
		}
		
	}
	
	private void drawInfoPane(String title, Character c) {
		
		GridData gridData = new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1);
		Group groupC = new Group(main, SWT.NONE);
		groupC.setText(title);
		groupC.setLayoutData(gridData);
		groupC.setLayout(new RowLayout(SWT.VERTICAL));
		DecimalFormat df = new DecimalFormat("#.##");
		//GridData data = new GridData(GridData.END, GridData.CENTER, false, false, 1, 1);
		
		Group group = new Group(groupC, SWT.NONE);
		group.setText("Base Stats");
		group.setLayout(new GridLayout(2, false));
		Text text = new Text(group, SWT.READ_ONLY);
		text.setText("Strength:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.strength));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Dexterity:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.dexterity));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Intelligence:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.intelligence));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Vitality:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.vitality));
		group.pack();
		
		group = new Group(groupC, SWT.NONE);
		group.setText("Damage");
		group.setLayout(new GridLayout(2, false));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Attack Speed:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Float.toString(c.atkSpeed));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Crit Chance:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Float.toString(c.critChance) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Crit Damage:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.critDamage) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("DPS:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(df.format(c.dps));
		group.pack();
		
		group = new Group(groupC, SWT.NONE);
		group.setText("Defense");
		group.setLayout(new GridLayout(2, false));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("HP:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.hp));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Effective HP:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.ehp));
		text.setToolTipText("Melee: " + Integer.toString(c.ehpM) + " Ranged: " + Integer.toString(c.ehpR));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("EHP w/Dodge:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.ehpD));
		text.setToolTipText("Melee: " + Integer.toString(c.ehpDM) + " Ranged: " + Integer.toString(c.ehpDR));
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Dodge:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(df.format(c.dodge) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Armor:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.armor));
		text.setToolTipText(df.format((c.armorPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Physical Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.physRes));
		text.setToolTipText(df.format((c.physResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Cold Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.coldRes));
		text.setToolTipText(df.format((c.coldResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Fire Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.fireRes));
		text.setToolTipText(df.format((c.fireResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Lightning Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.litRes));
		text.setToolTipText(df.format((c.litResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Poison Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.poisonRes));
		text.setToolTipText(df.format((c.poisonResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Arcane Resist:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(Integer.toString(c.arcRes));
		text.setToolTipText(df.format((c.arcResPct * 100f)) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Melee Reduction:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(df.format(c.reducM) + "%");
		text = new Text(group, SWT.READ_ONLY);
		text.setText("Range Reduction:");
		text = new Text(group, SWT.READ_ONLY);
		text.setText(df.format(c.reducR) + "%");
		
		group.pack();
		groupC.pack();
	}
	
	public void drawSkills() {
		
		Group group = new Group(main, SWT.NONE);
		group.setText("Skills");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		group.setLayoutData(data);	
		GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);
		
		Button[] skillButtons = new Button[activeChar.skills.size()];
		for (int i = 0; i < activeChar.skills.size(); i++) {
			
			skillButtons[i] = addSkillButton(new Button(group, SWT.CHECK), group, activeChar.skills.get(i), activeChar.skills.get(i).title);
			
		}
		
		group.pack();
		
	}
	
	private Button addSkillButton(Button button, Group group, Skill skill, String title) {
		button.setText(title);
		button.setSelection(skill.flag);
		addSkillListener(button, skill);
		return button;
	}
	
	private void addSkillListener(final Button button, final Skill skill) {
		button.addSelectionListener(new SelectionAdapter() {
        	
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		skill.flag = (button.getSelection()) ? true : false;
        		activeChar.update();
        		compareChar.update();
        		drawUI();
        	}
        	
        });
	}
	
	public static void main(String[] args) {
		display = new Display();
        new D3Calc(display);
        display.dispose();

	}

}
