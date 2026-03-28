package a.entity.gus.x.tostring.map;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240709";}

	public Object t(Object obj) throws Exception {
		return mapToString((Map) obj);
	}

	private String mapToString(Map map) throws Exception {
		StringBuffer b = new StringBuffer();
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) map.get(key);
			
			if (key.contains("\n")) throw new Exception("Invalid key: " + key);
			if (key.contains("=")) throw new Exception("Invalid key: " + key);
			if (value.contains("\n")) throw new Exception("Invalid value: " + value);
			
			b.append(key + "=" + value + "\n");
		}
		if (b.length() > 0)
			b.deleteCharAt(b.length() - 1);
		return b.toString();
	}
}