package a.entity.gus.y.entitygenerator1.multi.gui;

import javax.swing.JButton;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231202";}

	private Service gui;
	private Service generator;
	private Service findDev;

	public EntityImpl() throws Exception {
		gui = Outside.service(this, "*gus.x.features.p.string.inputgui1");
		generator = Outside.service(this, "gus.y.entitygenerator1.multi");
		findDev = Outside.service(this, "gus.y.srcroot1.dev");

		gui.p(generator);
		
		String devId = (String) findDev.g();
		((JButton) gui.r("buttonPerform")).setEnabled(devId!=null);
		((JButton) gui.r("buttonClear")).setEnabled(devId!=null);
	}

	public Object i() throws Exception {
		return gui.i();
	}
}
