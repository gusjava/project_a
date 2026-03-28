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
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_AFTER);
		}
		else SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		try {
			if (!SwingUtilities.isEventDispatchThread())
				throw new Exception("Current thread is supposed to be EDT...");
			
			log(this, "Launching maingui inside EDT");
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
			moduleE(M021_E_MAINFRAME).e();
			moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_AFTER);
		}
		catch (Exception e) {
			fatalEDT(e);
		}
	}
}
