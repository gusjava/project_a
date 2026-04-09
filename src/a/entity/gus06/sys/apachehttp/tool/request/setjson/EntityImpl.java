package a.entity.gus06.sys.apachehttp.tool.request.setjson;

import a.framework.*;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191030";}


	private Service generateJson;
	
	public EntityImpl() throws Exception
	{
		generateJson = Outside.service(this,"gus.x.json.build1");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase) o[0];
		String json = toJson(o[1]);
		
		if(json==null) return;
		
		StringEntity sEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
		sEntity.setContentEncoding("UTF-8");
		request.setEntity(sEntity);
	}
	
	
	private String toJson(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Map) return (String) generateJson.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
