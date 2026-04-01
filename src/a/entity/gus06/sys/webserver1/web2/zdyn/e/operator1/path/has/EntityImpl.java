package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator1.path.has;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141026";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		Map path = (Map) mr.r("data config path");
		return ""+path.containsKey(info);
	}
}
