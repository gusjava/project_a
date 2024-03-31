package a.entity.gus.y.docview1.gui1a;

import a.framework.*;

public class EntityImpl implements Entity, P, I, V {
	public String creationDate() {return "20231231";}
	
	private Service pathToText;
	private Service gui;

	public EntityImpl() throws Exception {
		pathToText = Outside.service(this, "gus.y.docview1.pathtotext");
		gui = Outside.service(this, "*gus.y.docview1.gui1");
	}
	
	public void p(Object obj) throws Exception {
		String text = (String) pathToText.t(obj);
		gui.p(text);
	}
	
	public Object i() throws Exception {
		return gui.i();
	}
	
	public void v(String key, Object obj) throws Exception {
		gui.v(key, obj);
	}
}
