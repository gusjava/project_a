package a.core.gus.gyem.m025.i.mainframe.content;

import java.awt.Container;

import a.core.gus.gyem.GyemSystem;
import a.framework.I;

public class Module extends GyemSystem implements I {
	
	private Container content;

	public Object i() throws Exception {
		if(content==null) init();
		return content;
	}
	
	private void init() throws Exception {
		try {
			content = (Container) moduleI(M026_I_BUILDGUI_ENTITY).i();
			if(content==null) content = (Container) moduleI(M027_I_BUILDGUI_DEFAULTPANEL).i();
		}
		catch(Exception e) {
			content = (Container) moduleT(M028_T_BUILDGUI_ERRORPANEL).t(e);
			e.printStackTrace();
		}
	}
}
