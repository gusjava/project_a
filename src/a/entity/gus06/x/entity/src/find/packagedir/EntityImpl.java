package a.entity.gus06.x.entity.src.find.packagedir;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		File srcDir = (File) o[0];
		String entityName = (String) o[1];
		if (entityName == null) return null;
		File dir = new File(new File(srcDir, "gus06"), "entity");
		return new File(dir, entityName.replace(".", File.separator));
	}
}
