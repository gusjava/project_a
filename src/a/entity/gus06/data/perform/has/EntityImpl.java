package a.entity.gus06.data.perform.has;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160905";}


	private Service colContains;
	private Service mapContainsKey;
	private Service arrayContains;
	private Service compContains;
	private Service fileContains;
	
	
	public EntityImpl() throws Exception
	{
		colContains = Outside.service(this,"gus06.collection.contains.coltof");
		mapContainsKey = Outside.service(this,"gus06.map.contains.key.maptof");
		arrayContains = Outside.service(this,"gus06.array.contains.arraytof");
		compContains = Outside.service(this,"gus06.swing.comp.contains.comptof");
		fileContains = Outside.service(this,"gus06.dirfile.contains.filetof");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return new F0();
		
		if(obj instanceof List) return colContains.t(obj);
		if(obj instanceof Set) return colContains.t(obj);
		if(obj instanceof Map) return mapContainsKey.t(obj);
		if(obj instanceof Object[]) return arrayContains.t(obj);
		if(obj instanceof JComponent) return compContains.t(obj);
		if(obj instanceof File) return fileContains.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class F0 implements F
	{
		public boolean f(Object obj) throws Exception
		{return false;}
	}
}