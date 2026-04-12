package a.entity.gus06.y.entitysys1.perform.entity.create;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251113";}

	private Service generate;
	private Service validate;

	public EntityImpl() throws Exception
	{
		generate = Outside.service(this, "gus.x.entity.src.generate1");
		validate = Outside.service(this, "gus.x.entity.name.validate");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String rule = (String) o[1];

		File srcDir = (File) ((R) engine).r("srcDir");
		String devId = (String) ((R) engine).r("devId");

		String[] nn = rule.split(" ", 2);
		String entityName = nn[0];
		String features = nn.length > 1 ? nn[1] : "";

		if (devId != null && !entityName.startsWith(devId + "."))
		entityName = devId + "." + entityName;

		if (!validate.f(entityName)) return false;
		boolean done = generate.f(new Object[] { srcDir, entityName, features });
		if (!done) return false;

		((V) engine).v("entityAdded", entityName);
		return true;
	}
}
