package a.entity.gus.y.entitysys1.find.src;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service findMainFile;
	private Service readFile;

	public EntityImpl() throws Exception
	{
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
		readFile = Outside.service(this,"gus.x.entity.src.read1");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		File file = (File) findMainFile.t(obj);
		if (!file.isFile()) return null;

		return readFile.t(file);
	}
}
