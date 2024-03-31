package a.entity.gus.z.appli1.gui2_3_1.all.detail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240112";}

	private Service engine;
	private Service editor;
	
	private String entityName;
	
	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.z.appli1.gui2_3_1.all.engine");
		editor = Outside.service(this, "*gus.y.entityeditor1.maingui");
	}
	
	public Object i() throws Exception {
		return editor.i();
	}
	
	public void p(Object obj) throws Exception {
		entityName = (String) obj;
		editor.p(new Object[] {engine, entityName});
	}
	
//	public void actionPerformed(ActionEvent e) {
//		refresh();
//	}
//	
//	private void refresh() {
//		try {
//			editor.e();
//		}
//		catch(Exception e) {
//			Outside.err(this, "refresh()", e);
//		}
//	}
}
