package a.entity.gus.y.entitysys1.find.srcpart;

import java.io.File;
import java.nio.file.Files;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service findMainFile;

	public EntityImpl() throws Exception {
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		int[] range = (int[]) o[2];
		
		if (range.length != 2) throw new Exception("Wrong range size: " + range.length);
		
		File mainFile = (File) findMainFile.t(new Object[]{engine, entityName});
		if (!mainFile.isFile()) return null;

		String src = new String(Files.readAllBytes(mainFile.toPath()), "UTF-8");

		if (range[0] < 0 || range[1] > src.length() || range[0] > range[1])
			throw new IllegalArgumentException("Invalid range");
		
		return src.substring(range[0], range[1]);
	}
}
