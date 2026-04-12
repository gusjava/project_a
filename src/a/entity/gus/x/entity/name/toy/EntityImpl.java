package a.entity.gus.x.entity.name.toy;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240113";}

	public Object t(Object obj) throws Exception {
		String name = (String) obj;
		String[] n = name.split("\\.");
		if (n.length > 2 && n[1].equals("y"))
			return n[0] + "." + n[2];
		return null;
	}
}
