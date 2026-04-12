package a.entity.gus.first;

import a.framework.E;
import a.framework.Entity;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20231110";}

	public void e() throws Exception {
		System.out.println("This is my first entity");
	}
}