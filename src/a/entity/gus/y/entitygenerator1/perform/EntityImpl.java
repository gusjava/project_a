package a.entity.gus.y.entitygenerator1.perform;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231202";}

	private Service generate;
	private Service validate;
	private Service findDev;
	
	private File rootDir;

	public EntityImpl() throws Exception {
		generate = Outside.service(this, "gus.x.entity.srcfile.generate1");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		rootDir = (File) Outside.service(this, "gus.y.srcroot1").g();
	}

	public void p(Object obj) throws Exception {
		String rule = (String) obj;

		String devId = (String) findDev.g();
		if (devId == null)
			throw new Exception("dev not found");

		String[] nn = rule.split(" ", 2);
		String entityName = nn[0];
		String features = nn.length > 1 ? nn[1] : "";
		
		if (entityName.trim().equals(""))
			throw new Exception("Invalid empty entity name");

		if (!entityName.startsWith(devId + "."))
			entityName = devId + "." + entityName;

		if (!validate.f(entityName))
			throw new Exception("Invalid entity name: " + entityName);
		generate.p(new Object[] { rootDir, entityName, features });
	}
}
