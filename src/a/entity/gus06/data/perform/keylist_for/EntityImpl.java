package a.entity.gus06.data.perform.keylist_for;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200429";}


	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object struct = o[0];
		Object value = o[1];
		
		if(struct instanceof Map) return perform((Map) struct,value);
		if(struct instanceof File) return perform((File) struct,value);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List perform(Map m, Object value)
	{
		List list = new ArrayList();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value0 = m.get(key);
			if(value.equals(value0)) list.add(key);
		}
		return list;
	}
	
	private List perform(File f, Object value) throws Exception
	{
		Map m = (Map) readFile.t(f);
		return perform(m,value);
	}
}
