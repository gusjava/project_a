package a.core.gus.gyem.m021.e.mainframe;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		Object frame = moduleG(M022_G_MAINFRAME_BUILD).g();
		moduleP(M024_P_MAINFRAME_HANDLE).p(frame);
	}
}
