package a.entity.gus06.y.entitysys1.perform.paste;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20251125";}

	private Service clipboard;
	private Service stringToList;
	private Service importSrc;
	private Service cutSrc;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.x.clipboard.string");
		stringToList = Outside.service(this,"gus06.x.string.split.n.list");
		importSrc = Outside.service(this,"gus06.y.entitysys1.perform.entity.importsrc");
		cutSrc = Outside.service(this,"gus06.y.entitysys1.perform.paste.split");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		Object anchor = o[1];
		
		String s = (String) clipboard.g();
		if(s==null || s.equals("")) return false;
		
		if(s.contains("package gus06.entity."))
			return pasteSrcCode(engine, s);
		return pasteLock(engine, s);
	}
	
	private boolean pasteSrcCode(Object engine, String s) throws Exception
	{
		List srcList = (List) cutSrc.t(s);
		boolean added = false;
		for(int i=0;i<srcList.size();i++)
		{
			String src = (String) srcList.get(i);
			if(importSrc.f(new Object[]{engine, src})) added = true;
		}
		return added;
	}
	
	private boolean pasteLock(Object engine, String s) throws Exception
	{
		List list = (List) stringToList.t(s);
		((V) engine).v("lock", list);
		return true;
	}
}
