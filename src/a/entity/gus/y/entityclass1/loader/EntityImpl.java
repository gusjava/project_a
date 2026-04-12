package a.entity.gus.y.entityclass1.loader;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service nameToPath;
	private Service findCl;

	public EntityImpl() throws Exception {
		nameToPath = Outside.service(this, "nametopath");
		findCl = Outside.service(this, "gus.y.entityclass1.cl.find");
	}

	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		String classPath = (String) nameToPath.t(entityName);

		ClassLoader cl = (ClassLoader) findCl.g();
		return Class.forName(classPath, true, cl);
	}
}
