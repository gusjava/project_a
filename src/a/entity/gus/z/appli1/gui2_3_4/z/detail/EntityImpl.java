package a.entity.gus.z.appli1.gui2_3_4.z.detail;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240113";}

	private Service engine;
	private Service editor;
	
	private String zName;
	
	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.z.appli1.gui2_3_4.z.engine");
		editor = Outside.service(this, "*gus.y.entityeditor1.maingui");
	}
	
	public Object i() throws Exception {
		return editor.i();
	}
	
	public void p(Object obj) throws Exception {
		zName = (String) obj;
//		editor.p(new Object[] {engine, zName});
	}
}
