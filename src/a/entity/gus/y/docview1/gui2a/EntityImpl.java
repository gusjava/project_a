package a.entity.gus.y.docview1.gui2a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, P, I, V {
	public String creationDate() {return "20240105";}

	private Service pathToText;
	private Service gui;

	public EntityImpl() throws Exception {
		pathToText = Outside.service(this, "gus.y.docview1.pathtotext");
		gui = Outside.service(this, "*gus.y.docview1.gui2");
	}
	
	public void p(Object obj) throws Exception {
		Map pathByDev = (Map) obj;
		Map srcByDev = new HashMap();
		Iterator it = pathByDev.keySet().iterator();
		while(it.hasNext()) {
			String dev = (String) it.next();
			String path = (String) pathByDev.get(dev);
			
			String src = (String) pathToText.t(path);
			srcByDev.put(dev, src);
		}
		gui.p(srcByDev);
	}
	
	public Object i() throws Exception {
		return gui.i();
	}
	
	public void v(String key, Object obj) throws Exception {
		gui.v(key, obj);
	}
}
