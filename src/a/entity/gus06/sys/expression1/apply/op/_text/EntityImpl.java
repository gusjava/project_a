package a.entity.gus06.sys.expression1.apply.op._text;

import a.framework.*;
import java.io.File;
import java.net.URL;
import javax.swing.JComponent;
import java.io.InputStream;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service readText;
	private Service urlToText;
	private Service processToText;
	private Service isToText;
	private Service mapToText;
	private Service compToText;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.autodetect");
		processToText = Outside.service(this,"gus06.process.tostring.autodetect");
		isToText = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
		mapToText = Outside.service(this,"gus06.tostring.map1");
		compToText = Outside.service(this,"gus06.swing.comp.gettext");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		return text(obj);
	}
	
	
	
	
	private String text(Object obj) throws Exception
	{
		if(obj instanceof Number)	return ""+obj;
		if(obj instanceof Boolean)	return ""+obj;
		if(obj instanceof File)		return (String) readText.t(obj);
		if(obj instanceof URL)		return (String) urlToText.t(obj);
		if(obj instanceof Map)		return (String) mapToText.t(obj);
		if(obj instanceof JComponent)	return (String) compToText.t(obj);
		if(obj instanceof InputStream)	return (String) isToText.t(obj);
		if(obj instanceof Process)	return (String) processToText.t(obj);
		if(obj instanceof byte[])	return new String((byte[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}