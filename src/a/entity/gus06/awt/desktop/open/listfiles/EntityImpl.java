package a.entity.gus06.awt.desktop.open.listfiles;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151017";}


	private Service open;
	private Service toList;


	public EntityImpl() throws Exception
	{
		open = Outside.service(this,"gus06.awt.desktop.open");
		toList = Outside.service(this,"gus06.convert.stringtolist");
	}
	
	
	public void p(Object obj) throws Exception
	{
		List list = toList(obj);
		for(int i=0;i<list.size();i++)
		open.p(list.get(i));
	}
	
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof File) return Collections.singletonList(obj);
		if(obj instanceof List) return (List) obj;
		if(obj instanceof String) return (List) toList.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
