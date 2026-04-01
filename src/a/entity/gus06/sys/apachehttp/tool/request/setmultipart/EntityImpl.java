package a.entity.gus06.sys.apachehttp.tool.request.setmultipart;

import a.framework.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191030";}
	
	public static final Charset CHARSET = Charset.forName("UTF-8");

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase) o[0];
		Map entity = (Map) o[1];
		
		if(entity==null) return;
		
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		entityBuilder.setCharset(CHARSET);
		
		Iterator it = entity.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = entity.get(key);
			
			if(value instanceof File)		entityBuilder.addBinaryBody(key, (File) value);
			else if(value instanceof byte[])	entityBuilder.addBinaryBody(key, (byte[]) value);
			else if(value instanceof InputStream)	entityBuilder.addBinaryBody(key, (InputStream) value);
			else if(value instanceof String)	entityBuilder.addTextBody(key, (String) value);
			
			else throw new Exception("Invalid value type: "+value.getClass().getName());
		}
		request.setEntity(entityBuilder.build());
	}
}
