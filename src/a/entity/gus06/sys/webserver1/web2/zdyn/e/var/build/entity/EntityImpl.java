package a.entity.gus06.sys.webserver1.web2.zdyn.e.var.build.entity;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141011";}

	public static final String START = "gus.sys.webserver1.web2.zdyn.e";
	
	
	private Service unique;

	public EntityImpl() throws Exception
	{unique = Outside.service(this,"entityunique");}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule.startsWith(".")) rule = START+rule;
		return unique.t(rule);
	}
}
