package a.entity.gus06.sys.webserver1.web2.zdyn.e.language.find;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141003";}

	public static final String DEFAULTID = "fr";
	public static final String PATH = "session access lang";
	
	
	public Object t(Object obj) throws Exception
	{
		R mr = (R) obj;
		F mf = (F) obj;
		
		if(!mf.f(PATH)) return DEFAULTID;
		return (String) mr.r(PATH);
	}
}
