package a.entity.gus.y.entitysys1.find.packagedir;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service findPackageDir;

	public EntityImpl() throws Exception {
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		return findPackageDir.t(new Object[] { rootDir, entityName });
	}
}
