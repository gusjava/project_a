package a.entity.gus.y.server1.engine.cmd.e.nj.editremove;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityEditRemove;
	private Service entityEngine;

	public EntityImpl() throws Exception
	{
		entityEditRemove = Outside.service(this, "gus.y.entitysys1.perform.entity.edit.remove");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		
		String name = (String)  get(json, "name");
		Object localizer = buildLocalizer(json);
		
		if(name==null) throw new Exception("Name not found inside json");
		if(localizer==null) throw new Exception("Localizer not found inside json");
		
		boolean done = entityEditRemove.f(new Object[]{entityEngine, name, localizer});
		return done ? "done" : "edit failed";
	}
	
	private Object buildLocalizer(Map json)
	{
		Integer start = getAsInt(json, "start");
		Integer end = getAsInt(json, "end");
		
		if(start!=null || end!=null)
		{
			if(start==null) start = -1;
			if(end==null) end = -1;
			return new int[]{start, end};
		}
		
		String search = (String) get(json, "search");
		String mode = (String) get(json, "mode");
		
		if(search!=null)
		{
			if(mode==null) mode = "+";
			else if(mode.equals("first")) mode = "+";
			else if(mode.equals("all")) mode = "*";
			else if(mode.equals("last")) mode = "-";
			
			return mode+search;
		}
		return null;
	}
	
	private Object get(Map json, String key)
	{
		if(!json.containsKey(key)) return null;
		return json.get(key);
	}
	
	private Integer getAsInt(Map json, String key)
	{
		Object val = get(json,key);
		if(val==null) return null;
		return Integer.parseInt(""+val);
	}
}
