package a.entity.gus.y.entitysys1.perform.entity.edit.remove;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260415";}

	private Service edit;

	public EntityImpl() throws Exception {
		edit = Outside.service(this, "gus.y.entitysrcedit1.remove");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		int[] range = (int[]) o[2];

		File rootDir = (File) ((R) engine).r("rootDir");

		boolean done = edit.f(new Object[] { rootDir, entityName, range });
		if (!done) throw new Exception("Entity not found: " + entityName);

		((V) engine).v("entityModified", entityName);
		return true;
	}
}
