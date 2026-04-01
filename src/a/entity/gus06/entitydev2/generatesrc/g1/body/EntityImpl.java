package a.entity.gus06.entitydev2.generatesrc.g1.body;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251205";}

	private Service extractMethodData;
	private Service formatBody;

	public EntityImpl() throws Exception
	{
		extractMethodData = Outside.service(this,"gus06.sys.javaparser1.extract.method.data");
		formatBody = Outside.service(this,"gus06.entitydev2.generatesrc.tool.formatbody");
	}
	
	public Object t(Object obj) throws Exception
	{
		String method = (String) obj;
		List list = (List) extractMethodData.t(method);
		if(list.size()!=1) throw new Exception("Invalid method src: "+method);
		Map data = (Map) list.get(0);
		
		List params = (List) data.get("params");
		String body = (String) data.get("body");
		String return_ = (String) data.get("return");
		
		if(return_==null) throw new Exception("Invalid method for G: "+method);
		if(!params.isEmpty()) throw new Exception("Invalid method for G: "+method);
		
		return (String) formatBody.t(body);
	}
}
