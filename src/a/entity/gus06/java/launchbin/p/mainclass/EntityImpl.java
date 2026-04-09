package a.entity.gus06.java.launchbin.p.mainclass;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260409";}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		List list = (List) o[0];
		Class cls = (Class) o[1];

		list.add(cls.getName());
	}
}
