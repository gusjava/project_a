package a.entity.gus06.sys.webserver1.web2.zdyn.e.find.action;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}
	
	
	public Object t(Object obj) throws Exception
	{
		R mr = (R) obj;
		F mf = (F) obj;
		
		if(!mf.f("data action")) return null;
		return mr.r("data action");
	}
}
