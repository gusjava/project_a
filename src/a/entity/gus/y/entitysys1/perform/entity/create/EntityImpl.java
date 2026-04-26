package a.entity.gus.y.entitysys1.perform.entity.create;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240116";}

	private Service logger;
	private Service generate;
	private Service validate;
	private Service completeName;

	public EntityImpl() throws Exception
	{
		logger = Outside.service(this, "logger");
		generate = Outside.service(this, "gus.x.entity.src.generate1");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		completeName = Outside.service(this,"gus.x.entity.completename");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String rule = (String) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");

		String[] nn = rule.split(" ", 2);
		String entityName = nn[0];
		String features = nn.length > 1 ? nn[1] : "";

		entityName = (String) completeName.t(new Object[]{devId, entityName});
		if (!validate.f(entityName))
			throw new Exception("Invalid entity name: " + entityName + " (expected: " + validate.g() + ")");
		
		boolean done = (Boolean) generate.f(new Object[] { rootDir, entityName, features });
		if (!done) throw new Exception("Entity already exists: " + entityName);

		log("Entity added: "+entityName);
		((V) engine).v("entityAdded", entityName);
		return true;
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception
	{logger.p(new Object[] {this, msg});}
}
