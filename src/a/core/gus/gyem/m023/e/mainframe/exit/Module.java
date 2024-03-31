package a.core.gus.gyem.m023.e.mainframe.exit;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		log(this, "Exiting application...");
		System.exit(0);
	}
}
