package a.entity.gus.y.entitysys1.find.mainfile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service findPackageDir;

	public EntityImpl() throws Exception {
		findPackageDir = Outside.service(this, "gus.y.entitysys1.find.packagedir");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		File packageDir = (File) findPackageDir.t(obj);
		return new File(packageDir, "EntityImpl.java");
	}
}
