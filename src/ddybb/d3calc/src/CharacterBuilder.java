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

public class CharacterBuilder {
	
	public Text name;
	public Shell shell;
	
	public CharacterBuilder(Display display) {
		
		shell = new Shell(display);
        shell.setText("New Character");
        shell.setSize(300, 200);
        shell.setLocation(300, 300);
        
        initUI();
        
        shell.open();

        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
            display.sleep();
        	}
        }
	}
	
	private void initUI() {
		
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Name:");
		name = new Text(shell, SWT.NONE);
		name.setText("");
		
		label = new Label(shell, SWT.NONE);
		label.setText("Class:");
		final Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
        combo.add("Barbarian");
        combo.add("Demon Hunter");
        combo.add("Monk");
        combo.add("Witch Doctor");
        combo.add("Wizard");
        
        GridData data = new GridData();
        data.widthHint = 80;
        data.heightHint = 30;
        Button createButton = new Button(shell, SWT.PUSH);
        createButton.setText("Create");
        createButton.setLayoutData(data);
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.setLayoutData(data);
        
        shell.pack();
        
        createButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            	CharacterClass c = null;
            	switch (combo.getSelectionIndex()) {
            	case 0: c = CharacterClass.Barbarian; break;
            	case 1: c = CharacterClass.DemonHunter; break;
            	case 2: c = CharacterClass.Monk; break;
            	case 3: c = CharacterClass.WitchDoctor; break;
            	case 4: c = CharacterClass.Wizard; break;
            	default: break;
            	}
            	if (c != null) {
            		D3Calc.activeChar = new Character(name.getText(), c);
            		D3Calc.compareChar = new Character("", c);
            		shell.dispose();
            	}
            };
        });
        
        cancelButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            };
        });
		
	}
	
}
