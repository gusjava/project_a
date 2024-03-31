package a.core.gus.gyem.m001.e.launch;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		String config = (String) moduleG(M007_G_CONFIG_ID).g();
	
		log(this, "Starting application...");
		log(this, "CORE_ID="+CORE_ID);
		log(this, "CONFIG_ID="+config);
		
		moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
		moduleE(M021_E_MAINFRAME).e();
		moduleP(M031_P_EXECUTE_SEQUENCE).p(PROP_AFTER);
	}
}
