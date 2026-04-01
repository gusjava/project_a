package a.entity.gus06.data.viewer.object.builder.sync;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140731";}


	private Service entitynew;
	private Service objToNames;
	
	private Map map;
	private Map previous;
	
	
	public EntityImpl() throws Exception
	{
		entitynew = Outside.service(this,"entitynew");
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
			
			Object editor = find(name);
			((P)editor).p(obj);
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
	
	
	
	private Object find(String name) throws Exception
	{
		if(!map.containsKey(name)) map.put(name,build(name));
		return map.get(name);
	}
	
	
	private Object build(String name) throws Exception
	{return entitynew.t(name);}
}
