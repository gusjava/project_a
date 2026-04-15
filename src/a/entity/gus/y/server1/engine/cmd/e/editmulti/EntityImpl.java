package a.entity.gus.y.server1.engine.cmd.e.editmulti;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	private Service entityEditMulti;
	private Service entityEngine;

	public EntityImpl() throws Exception {
		entityEditMulti = Outside.service(this, "gus.y.entitysys1.perform.entity.edit.multi");
		entityEngine = Outside.service(this, "gus.y.entitysys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		String name = (String) json.get("name");
		List opList = (List) json.get("op");
		
		List operations = new ArrayList();
		for(int i=0;i<opList.size();i++)
		{
			Map op = (Map) opList.get(i);
			Integer start = Integer.parseInt((String) get(op,"start",0));
			Integer end = Integer.parseInt((String) get(op,"end",start));
			String insert = (String) get(op,"insert","");
			
			operations.add(new Object[]{start,end,insert});
		}
		boolean done = entityEditMulti.f(new Object[]{entityEngine, name, operations});
		return done ? "done" : "edit failed";
	}
	
	private Object get(Map map, String key, Object defaultValue)
	{return map.containsKey(key) ? map.get(key) : defaultValue;}
}
