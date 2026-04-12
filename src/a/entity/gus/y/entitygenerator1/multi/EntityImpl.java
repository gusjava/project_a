package a.entity.gus.y.entitygenerator1.multi;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231202";}

	private Service generator;

	public EntityImpl() throws Exception {
		generator = Outside.service(this, "gus.y.entitygenerator1.perform");
	}

	public void p(Object obj) throws Exception {
		String[] lines = ((String) obj).split("\n");

		for (String line : lines) {
			generator.p(line);
		}
	}
}
