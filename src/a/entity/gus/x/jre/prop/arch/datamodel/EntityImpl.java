package a.entity.gus.x.jre.prop.arch.datamodel;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231129";}

	private String arch = System.getProperty("sun.arch.data.model");

	public Object g() throws Exception {
		return arch;
	}
}
