package a.entity.gus.z.appli1.gui1_4.rules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231231";}

	private Service docView;
	private Service appEntries;
	private Map pathByDev;
	private Icon icon;
	
	public EntityImpl() throws Exception {
		docView = Outside.service(this, "*gus.y.docview1.gui2a");
		appEntries = Outside.service(this, "gus.y.appentries1.self");
		icon = (Icon) Outside.resource(this, "icon#ELEMENT_rule");
		
		pathByDev = buildPathByDev();
		
		docView.v("icon", icon);
		docView.v("delim", "#");
		docView.p(pathByDev);
	}
	
	public Object i() throws Exception {
		return docView.i();
	}
	
	private Map buildPathByDev() throws Exception {
		Map m = new HashMap();
		Set devs = (Set) appEntries.r("devs");
		List listConfig = (List) appEntries.r("listConfig");
		
		Iterator it = devs.iterator();
		while(it.hasNext()) {
			String dev = (String) it.next();
			String path = "a/config/"+dev+"/doc1/fr/rules/rules.txt";
			if(listConfig.contains(path)) m.put(dev, path);
		}
		return m;
	}
}
