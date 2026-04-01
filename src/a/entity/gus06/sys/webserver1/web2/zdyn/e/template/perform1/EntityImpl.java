package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.perform1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141001";}


	public static final String DEFAULT_ = "default";
	

	private Service findPage;
	private Service perform2;
	
	public EntityImpl() throws Exception
	{
		findPage = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.page");
		perform2 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.perform2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String id = (String) o[1];
		
		Map templateConf = (Map) mr.r("data config template");
		String rule = findRule(templateConf,id);
		perform2.p(new Object[]{mr,rule});
	}
	
	
	
	
	
	private String findRule(Map m, String id)
	{
		if(m.containsKey(id)) return (String) m.get(id);
		if(m.containsKey(DEFAULT_)) return (String) m.get(DEFAULT_);
		return id;
	}
}
