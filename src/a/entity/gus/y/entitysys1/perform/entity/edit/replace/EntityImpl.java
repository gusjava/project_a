package a.entity.gus.y.entitysys1.perform.entity.edit.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260414";}

	private Service edit;

	public EntityImpl() throws Exception {
		edit = Outside.service(this, "gus.y.entitysrcedit1.replace");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		int[] range = (int[]) o[2];
		String replacement = (String) o[3];

		File rootDir = (File) ((R) engine).r("rootDir");

		boolean done = (Boolean) edit.f(new Object[] { rootDir, entityName, range, replacement });
		if (!done) throw new Exception("Entity already exists: " + entityName);

		((V) engine).v("entityModified", entityName);
		return true;
	}
}
