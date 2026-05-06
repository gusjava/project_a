package a.entity.gus.y.entitysys1.find.mainfile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service findEntityFile;

	public EntityImpl() throws Exception
	{
		findEntityFile = Outside.service(this, "gus.x.entity.src.find.entityfile");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		return findEntityFile.t(new Object[] { rootDir, entityName });
	}
}
