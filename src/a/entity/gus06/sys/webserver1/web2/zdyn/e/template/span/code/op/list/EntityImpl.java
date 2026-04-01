package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.code.op.list;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}
	
	private Service toList;
	
	public EntityImpl() throws Exception
	{
		toList = Outside.service(this,"gus06.list.string.stringtolist.builder2");
	}
	
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map span = (Map) o[0];
		String info = (String) o[1];
		
		return toList.t(info);
	}
}
