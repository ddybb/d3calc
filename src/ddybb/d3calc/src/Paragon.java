package ddybb.d3calc.src;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class Paragon {
	
	private Shell shell;
	private Spinner spinner;
	
	public Paragon(Display display) {
		
		shell = new Shell(display);
        shell.setText("Paragon Level");
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
        
        Label label = new Label(shell, SWT.BORDER);
        label.setText("Set Paragon Level");
        spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMinimum(0);
		spinner.setMaximum(100);
		spinner.setSelection(D3Calc.activeChar.paragon);
		spinner.setIncrement(1);
		
		GridData data = new GridData();
        data.widthHint = 80;
        data.heightHint = 30;
        Button okButton = new Button(shell, SWT.PUSH);
        okButton.setText("OK");
        okButton.setLayoutData(data);
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.setLayoutData(data);
		
        okButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                D3Calc.activeChar.paragon = spinner.getSelection();
                D3Calc.compareChar.paragon = spinner.getSelection();
            	shell.dispose();
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
