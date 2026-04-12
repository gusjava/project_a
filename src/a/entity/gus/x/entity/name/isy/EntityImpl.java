package a.entity.gus.x.entity.name.isy;

import a.framework.*;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20240113";}

	public boolean f(Object obj) throws Exception {
		String name = (String) obj;
		String[] n = name.split("\\.");
		return n.length > 2 && n[1].equals("y");
	}
}
