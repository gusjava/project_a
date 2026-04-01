package a.entity.gus06.data.viewer.object.builder.async;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150214";}


	private Service build;
	private Service objToNames;
	
	private Map map;
	private Map previous;
	
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.async.guibuilder.dataholder");
		objToNames = Outside.service(this,"gus06.data.viewer.object.objtoname");
		
		map = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		resetPrevious();
		if(obj==null) return null;
		
		Map names = objToNames(obj);
		Map editors = new HashMap();
		
		Iterator it = names.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String name = (String) names.get(key);
			
			Object editor = find(name,obj);
			editors.put(key,editor);
		}
		
		previous = editors;
		return editors;
	}
	
	
	
	
	private void resetPrevious() throws Exception
	{
		if(previous==null) return;
		Iterator it = previous.keySet().iterator();
		while(it.hasNext())
		((P) previous.get(it.next())).p(null);
		previous = null;
	}
	
	
	private Map objToNames(Object obj) throws Exception
	{return (Map) objToNames.t(obj);}
	
	
	
	
	private Object find(String name, Object data) throws Exception
	{
		if(!map.containsKey(name))
		{
			Object editor = build(name,data);
			map.put(name,editor);
			return editor;
		}
		
		Object editor = map.get(name);
		((P)editor).p(data);
		return editor;
	}
	
	
	
	private Object build(String name, Object data) throws Exception
	{return build.t(new Object[]{name,data});}
}
