package a.entity.gus.y.appentries1.self;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20240105";}

	private Service appLocation;
	private Service buildLists;
	
	private List listFramework;
	private List listCore;
	private List listEntity;
	private List listConfig;
	
	private Map mapCoreByDev;
	private Map mapEntityByDev;
	private Map mapConfigByDev;
	
	private Set devs;
	
	public EntityImpl() throws Exception {
		appLocation = Outside.service(this, "gus.x.app.location");
		buildLists = Outside.service(this, "gus.y.appentries1.build.lists");
		
		File location = (File) appLocation.g();
		List[] lists = (List[]) buildLists.t(location);

		listFramework = lists[0];
		listCore = lists[1];
		listEntity = lists[2];
		listConfig = lists[3];
		
		mapCoreByDev = buildByDev(listCore);
		mapEntityByDev = buildByDev(listEntity);
		mapConfigByDev = buildByDev(listConfig);
		
		devs = new HashSet();
		devs.addAll(mapCoreByDev.keySet());
		devs.addAll(mapEntityByDev.keySet());
		devs.addAll(mapConfigByDev.keySet());
	}
	
	public Object r(String key) throws Exception {
		if (key.equals("listFramework")) return listFramework;
		if (key.equals("listCore")) return listCore;
		if (key.equals("listEntity")) return listEntity;
		if (key.equals("listConfig")) return listConfig;
		
		if (key.equals("mapCoreByDev")) return mapCoreByDev;
		if (key.equals("mapEntityByDev")) return mapEntityByDev;
		if (key.equals("mapConfigByDev")) return mapConfigByDev;
		
		if (key.equals("devs")) return devs;
		
		if (key.equals("keys"))
			return new String[] { "listFramework", "listCore", "listEntity", "listConfig", "mapCoreByDev", "mapEntityByDev", "mapConfigByDev", "devs" };
		throw new Exception("Unknown key: " + key);
	}
	
	private Map buildByDev(List list) {
		Map map = new HashMap();
		for(int i=0;i<list.size();i++) {
			String path = (String) list.get(i);
			if(path.startsWith("/")) path = path.substring(1);
			String dev = path.split("\\/")[2];
			addToMap(map,dev,path);
		}
		return map;
	}
	
	private void addToMap(Map map, String key, String value) {
		if(!map.containsKey(key)) map.put(key, new ArrayList<>());
		((List) map.get(key)).add(value);
	}
}
