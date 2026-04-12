package a.entity.gus.y.entitysys1.find.src;

import java.io.File;
import java.nio.file.Files;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	private Service findMainFile;

	public EntityImpl() throws Exception {
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		File mainFile = (File) findMainFile.t(obj);
		if (!mainFile.isFile())
			return null;

		return new String(Files.readAllBytes(mainFile.toPath()), "UTF-8");
	}
}
