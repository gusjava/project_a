package a.entity.gus.z.appli1.gui1_5_1.x;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231231";}
	
	private Service docView;
	private Service appEntries;
	private Map pathsByDev;
	private Icon icon;
	
	public EntityImpl() throws Exception {
		docView = Outside.service(this, "*gus.y.docview1.gui3a");
		appEntries = Outside.service(this, "gus.y.appentries1.self");
		icon = (Icon) Outside.resource(this, "icon#ELEMENT_entity");
		
		pathsByDev = buildPathsByDev();
		
		docView.v("icon", icon);
		docView.v("delim", ".x.");
		docView.p(pathsByDev);
	}
	
	public Object i() throws Exception {
		return docView.i();
	}
	
	private Map buildPathsByDev() throws Exception {
		Map map = new HashMap();
		Map mapConfigByDev = (Map) appEntries.r("mapConfigByDev");

		Iterator it = mapConfigByDev.keySet().iterator();
		while (it.hasNext()) {
			String dev = (String) it.next();
			List paths = (List) mapConfigByDev.get(dev);

			String root = "a/config/" + dev + "/doc1/fr/src/entity/x/";
			List paths1 = new ArrayList();
			for (int i = 0; i < paths.size(); i++) {
				String path = (String) paths.get(i);
				if (path.startsWith(root))
					paths1.add(path);
			}
			if (!paths1.isEmpty())
				map.put(dev, paths1);
		}
		return map;
	}
}
