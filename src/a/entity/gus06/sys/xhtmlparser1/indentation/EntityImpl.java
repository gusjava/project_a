package a.entity.gus06.sys.xhtmlparser1.indentation;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170218";}
	

	private Service handle;
	
	public EntityImpl() throws Exception
	{
		handle = Outside.service(this,"gus06.sys.xhtmlparser1.indentation.handle");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) return "";
		
		StringBuffer b = new StringBuffer();
		handleMap(b,map,"");
		return b.toString().trim();
	}
	
	private void handleMap(StringBuffer b, Map map, String offset) throws Exception
	{handle.p(new Object[]{b,map,offset});}
}
