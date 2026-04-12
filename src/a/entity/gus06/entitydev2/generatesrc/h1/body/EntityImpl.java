package a.entity.gus06.entitydev2.generatesrc.h1.body;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251206";}

	private Service extractMethodData;
	private Service paramConvertor;
	private Service formatBody;

	public EntityImpl() throws Exception
	{
		extractMethodData = Outside.service(this,"gus06.sys.javaparser1.extract.method.data");
		paramConvertor = Outside.service(this,"gus06.entitydev2.generatesrc.tool.paramconvertor");
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
		
		if(return_==null) throw new Exception("Invalid method for H: "+method);
		if(params.size()!=1) throw new Exception("Invalid method for H: "+method);
		
		if(!return_.equals("double")) throw new Exception("Invalid method for H: "+method);
		if(!params.get(0).equals("double")) throw new Exception("Invalid method for H: "+method);
		
		String params1 = (String) paramConvertor.t(params);
		String body1 = (String) formatBody.t(body);
		
		return params1+body1;
	}
}
