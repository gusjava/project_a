package a.entity.gus06.sys.expression1.apply.op._utf8;

import a.framework.*;
import java.nio.charset.Charset;
import java.io.File;
import java.net.URL;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	public final static Charset CHARSET = Charset.forName("UTF-8");


	private Service readText;
	private Service urlToText;
	private Service processToText;
	private Service isToText;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.cs.utf8");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.utf8");
		processToText = Outside.service(this,"gus06.process.tostring.utf8");
		isToText = Outside.service(this,"gus06.io.transfer.tostring.utf8");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File)
			return readText.t(obj);
		if(obj instanceof URL)
			return urlToText.t(obj);
		if(obj instanceof InputStream)
			return (String) isToText.t(obj);
		if(obj instanceof Process)
			return (String) processToText.t(obj);
		if(obj instanceof byte[])
			return new String((byte[]) obj,CHARSET);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
