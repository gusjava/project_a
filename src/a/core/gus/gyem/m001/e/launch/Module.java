package a.core.gus.gyem.m001.e.launch;

import javax.swing.SwingUtilities;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E, Runnable {

	public void e() throws Exception {
		String config = (String) moduleG(M007_G_CONFIG_ID).g();
	
		log(this, "Starting application...");
		log(this, "CORE_ID="+CORE_ID);
		log(this, "CONFIG_ID="+config);

		if(isMainguiDisabled()) {
			log(this, "Maingui disabled");
			
			log(this, "Executing sequence: "+PROP_BEFORE);
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
			
			log(this, "Executing sequence: "+PROP_AFTER);
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_AFTER);
		}
		else SwingUtilities.invokeLater(this);
	}
	
	public void run() {
		try {
			if (!SwingUtilities.isEventDispatchThread())
				throw new Exception("Current thread is supposed to be EDT...");
			
			log(this, "Launching maingui inside EDT");
			
			log(this, "Executing sequence: "+PROP_BEFORE);
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
			
			log(this, "Initializing main frame");
			moduleE(M021_E_MAINFRAME).e();
			
			log(this, "Executing sequence: "+PROP_AFTER);
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_AFTER);

			log(this, "Gui started notification");
			moduleE(M054_E_STARTED).e();
		}
		catch (Exception e) {
			fatalEDT(e);
		}
	}
}
