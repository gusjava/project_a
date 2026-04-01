package a.entity.gus06.sys.expression1.apply.op._extractlinks_int_href;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}


	private Service extract;
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.url.extractlinks.internal.href");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return extract.t(obj);
		if(obj instanceof URL) return extract.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
