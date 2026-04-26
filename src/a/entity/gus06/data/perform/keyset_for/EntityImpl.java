package a.entity.gus06.data.perform.keyset_for;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.util.Iterator;
import java.util.Objects;

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
	
	
	private Set perform(Map m, Object value)
	{
		Set set = new HashSet();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value0 = m.get(key);
			if(Objects.equals(value,value0)) set.add(key);
		}
		return set;
	}
	
	
	private Set perform(File f, Object value) throws Exception
	{
		Map m = (Map) readFile.t(f);
		return perform(m,value);
	}
	
}
