package ddybb.d3calc.src;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ItemBuilder {
	
	public Shell shell;
	private Combo[] combo = new Combo[8];
	private Text[] text = new Text[8];
	private Combo slot;
	private ItemType type;
	private boolean initFlag;
	private double[] values = new double[8];
	private Item itemEdit;
	
	public ItemBuilder(Display display, Item item) {
		
		itemEdit = item;
		
		shell = new Shell(display);
        shell.setText("New Item");
        shell.setSize(240, 240);
        shell.setLocation(300, 300);
        
        initUI();
        
        shell.open();

        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
            display.sleep();
        	}
        }        
	}
	
	public ItemBuilder(Display display) {
		new ItemBuilder(display, null);
	}
	
	private void initUI() {
		
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		
		Label label = new Label(shell, SWT.None);
		label.setText("Slot:");
		slot = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		GridData data = new GridData(GridData.END, GridData.CENTER, false, false, 1, 1);
		slot.setLayoutData(data);
		for (ItemType i : ItemType.values()) {
			slot.add(i.name);
			
		}
		
		if (itemEdit != null) {
			type = itemEdit.type;
			initMods(type);
			initFlag = true;
		}
		
		slot.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                type = ItemType.getTypeByIndex(slot.getSelectionIndex());
            	if (!initFlag) {
            		initMods(type);
            		initFlag = true;
            	} else {
            		drawMods(type);
            	}
            };
        });
			
	}
	
	private void drawMods(ItemType type) {
		
		for (int i = 0; i < 8; i++) {
			combo[i].removeAll();
			for (ItemMods mod : ItemMods.values()) {
				if (mod.mask == type.mask || mod.mask == 0 || (mod.mask == 4 && type.mask != 1) || (mod.mask == 5 && type.mask != 3)) {
					combo[i].add(mod.modName);
				}
			}
			text[i].setText("0");
		}
		
	}
	
	private void initMods(ItemType type) {
		
		GridData data = new GridData(GridData.END, GridData.CENTER, false, false, 1, 1);
		
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
			for (ItemMods mod : ItemMods.values()) {
				if (mod.mask == type.mask || mod.mask == 0 || (mod.mask == 4 && type.mask != 1) || (mod.mask == 5 && type.mask != 3)) {
					combo[i].add(mod.modName);
				}
			}
			data = new GridData(GridData.END, GridData.CENTER, false, false, 1, 1);
			data.widthHint = 60;
			text[i] = new Text(shell, SWT.BORDER);
			text[i].setOrientation(SWT.RIGHT_TO_LEFT);
			text[i].setText("0");
			text[i].setLayoutData(data);
	        
		}
		
		String[] modList = combo[0].getItems();
		if (itemEdit != null) {
			slot.select(type.index);
			int i = 0;
			for (ItemMods mod : itemEdit.mods) {
				if (mod != null) {
					for (int j = 0; j < modList.length; j++) {
						if (modList[j].contains(mod.modName)) {
							combo[i].select(j);
							text[i].setText(itemEdit.getValueByMod(mod).toString());
						}
					}
					i++;
				}
			}
		}
		
		Button equipButton = new Button(shell, SWT.PUSH);
        equipButton.setText("Equip");
        data = new GridData();
        data.widthHint = 100;
        equipButton.setLayoutData(data);
        Button compareButton = new Button(shell, SWT.PUSH);
        compareButton.setText("Compare");
        compareButton.setLayoutData(data);
        
        shell.pack();
        
        equipButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            	buildItem(D3Calc.activeChar);
            };
        });
        
        compareButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            	buildItem(D3Calc.compareChar);
            };
        });
		
	}
	
	public void buildItem(Character c) {
		
		for (int i = 0; i < text.length; i++) {
			try {
				values[i] = new Double(text[i].getText());
			} catch (Exception e) {
				return;
			}
		}
		
		Item item = new Item(type);
		
		int j = 0;
		for (int i = 0; i < combo.length; i++) {
			if (combo[i] != null && values[i] > 0) {
				for (ItemMods mod : ItemMods.values()) {
					if (mod.modName.equals(combo[i].getText())) {
						setItemValues(item, mod, values[i]);
						item.mods[j] = mod;
						j++;
					}
				}
			}
		}
		
		//allowed main/offhand combo filters
		if (type == ItemType.Weapon2H) {
			c.equip[12] = null;
		}
		if (type == ItemType.WeaponBow) {
			if (c.equip[12] != null) {
				if (c.equip[12].type != ItemType.Quiver) {
					c.equip[12] = null;
				}
			}
		}
		if (type == ItemType.Shield || type == ItemType.Source) {
			if (c.equip[11] != null) {
				if (c.equip[11].type != ItemType.Weapon1) {
					c.equip[11] = null;
				}
			}
		}
		if (type == ItemType.Quiver) {
			if (c.equip[11] != null) {
				if (c.equip[11].type != ItemType.Weapon1 && c.equip[11].type != ItemType.WeaponBow) {
					c.equip[11] = null;
				}
			}
		}
		
		c.equip[type.slot] = item;
		c.update();
		shell.dispose();
		
	}
	
	public void setItemValues(Item item, ItemMods mod, double value) {
		
		switch (mod) {
		case Dexterity: item.dexterity = (int)value; break;
		case Intelligence: item.intelligence = (int)value; break;
		case Strength: item.strength = (int)value; break;
		case Vitality: item.vitality = (int)value; break;
		case Armor: item.armor = (int)value; break;
		case AllRes: item.allRes = (int)value; break;
		case PhysRes: item.physRes = (int)value; break;
		case ColdRes: item.coldRes = (int)value; break;
		case FireRes: item.fireRes = (int)value; break;
		case LitRes: item.litRes = (int)value; break;
		case PoisonRes: item.poisonRes = (int)value; break;
		case ArcRes: item.arcRes = (int)value; break;
		case Life: item.life = (int)value; break;
		case CritChance: item.critChance = (float)value; break;
		case CritDamage: item.critDamage = (int)value; break;
		case AtkSpeed: item.atkSpeed = (int)value; break;
		case Dps: item.dps = (float)value; break;
		case AtkSpeedBase: item.atkSpeedBase = (float)value; break;
		case DamageMin: item.damageMin = (int)value; break;
		case DamageMax: item.damageMax = (int)value; break;
		case BlockChance: item.blockChance = (int)value; break;
		case BlockChanceBase: item.blockChanceBase = (float)value; break;
		case BlockAmount: item.blockAmount = (int)value; break;
		case ReducM: item.reducM = (float)value; break;
		case ReducR: item.reducR = (float)value; break;
		default: break;
		}
		
	}

}
