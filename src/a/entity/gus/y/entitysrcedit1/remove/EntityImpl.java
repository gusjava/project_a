package a.entity.gus.y.entitysrcedit1.remove;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260414";}

	private Service replace;

	public EntityImpl() throws Exception
	{replace = Outside.service(this,"gus.y.entitysrcedit1.replace");}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		Object localizer = o[2];

		return replace.f(new Object[]{rootDir, entityName, localizer, ""});
	}
}
