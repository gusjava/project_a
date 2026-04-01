package a.entity.gus06.data.perform.has.i;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}


	private Service colContains;
	private Service mapContainsKey;
	private Service arrayContains;
	
	public EntityImpl() throws Exception
	{
		colContains = Outside.service(this,"gus06.collection.contains.coltof.i");
		mapContainsKey = Outside.service(this,"gus06.map.contains.key.maptof.i");
		arrayContains = Outside.service(this,"gus06.array.contains.arraytof.i");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new F0();
		
		if(obj instanceof List) return colContains.t(obj);
		if(obj instanceof Set) return colContains.t(obj);
		if(obj instanceof Map) return mapContainsKey.t(obj);
		if(obj instanceof Object[]) return arrayContains.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class F0 implements F
	{
		public boolean f(Object obj) throws Exception
		{return false;}
	}
}