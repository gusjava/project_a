package a.entity.gus06.swt.webbrowser.show;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.util.concurrent.ArrayBlockingQueue;

public class WebBrowser {

	private static final String AT_REST = "Ready";

	private Display display;
	private Shell shell;
	
	private Browser browser;
	private Text locationText;
	private Label statusLabel;
	private Label throbberLabel;
	
	private ArrayBlockingQueue queue;


	public WebBrowser() {
		queue = new ArrayBlockingQueue(100);
		
		display = new Display();
		shell = new Shell(display);
		shell.setText("Web Browser");

		shell.setLayout(new FormLayout());

		Composite controls = new Composite(shell, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		controls.setLayoutData(data);

		statusLabel = new Label(shell, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		statusLabel.setLayoutData(data);

		browser = new Browser(shell, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(controls);
		data.bottom = new FormAttachment(statusLabel);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		browser.setLayoutData(data);

		controls.setLayout(new GridLayout(7, false));

		Button button = new Button(controls, SWT.PUSH);
		button.setText("Back");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.back();
			}
		});

		button = new Button(controls, SWT.PUSH);
		button.setText("Forward");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.forward();
			}
		});

		button = new Button(controls, SWT.PUSH);
		button.setText("Refresh");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.refresh();
			}
		});

		button = new Button(controls, SWT.PUSH);
		button.setText("Stop");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.stop();
			}
		});

		locationText = new Text(controls, SWT.BORDER);
		locationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		locationText.setFocus();

		button = new Button(controls, SWT.PUSH);
		button.setText("Go");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.setUrl(locationText.getText());
			}
		});

		throbberLabel = new Label(controls, SWT.NONE);
		throbberLabel.setText(AT_REST);

		shell.setDefaultButton(button);

		browser.addCloseWindowListener(new AdvancedCloseWindowListener());
		browser.addLocationListener(new AdvancedLocationListener());
		browser.addProgressListener(new AdvancedProgressListener());
		browser.addStatusTextListener(new AdvancedStatusTextListener());
	}
	
	
	
	public void open(String location) {
		if (location != null) {
			browser.setUrl(location);
		}
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	
	
	
	
	
	private class AdvancedCloseWindowListener implements CloseWindowListener {
		public void close(WindowEvent event) {
			((Browser) event.widget).getShell().close();
		}
	}

	private class AdvancedLocationListener implements LocationListener {
		public void changing(LocationEvent event) {
			locationText.setText("Loading " + event.location + "...");
		}

		public void changed(LocationEvent event) {
			locationText.setText(event.location);
			putInfo(event.location);
		}
	}

	private class AdvancedProgressListener implements ProgressListener {
		public void changed(ProgressEvent event) {
			if (event.total != 0) {
				int percent = (int) (event.current / event.total);
				throbberLabel.setText(percent + "%");
			} else {
				throbberLabel.setText("?");
			}
		}

		public void completed(ProgressEvent event) {
			throbberLabel.setText(AT_REST);
		}
	}

	private class AdvancedStatusTextListener implements StatusTextListener {
		public void changed(StatusTextEvent event) {
			statusLabel.setText(event.text);
		}
	}
	
	public void setLocation(final String location) {
		display.asyncExec(new Runnable() {
			public void run() {
				browser.setUrl(location);
			}
		});
	}
	
	
	
	private void putInfo(String info) {
		try{queue.put(info);}
		catch (InterruptedException e){}
	}
	
	public ArrayBlockingQueue getQueue() {
		return queue;
	}
}