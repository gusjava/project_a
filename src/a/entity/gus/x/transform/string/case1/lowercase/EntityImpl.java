package a.entity.gus.x.transform.string.case1.lowercase;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240113";}
	
	public Object t(Object obj) throws Exception {
		String s = (String) obj;
		return s.toLowerCase();
	}
}
