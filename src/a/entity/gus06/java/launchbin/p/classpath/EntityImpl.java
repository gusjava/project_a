package a.entity.gus06.java.launchbin.p.classpath;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260409";}


	private Service toStr;

	public EntityImpl() throws Exception
	{
		toStr = Outside.service(this, "gus.x.app.classpath.str");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		List list = (List) o[0];
		File[] classpath = (File[]) o[1];

		String cp = (String) toStr.t(classpath);
		list.add("-cp");
		list.add(cp);
	}
}
