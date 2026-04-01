package a.entity.gus06.app.jarfile.manager.gyem.module.finddep;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140917";}


	private Service findSrc_main;
	private Service findSrc_module;
	private Service moduleCalls;
	private Service classToName;
	
	private Map classMap;


	public EntityImpl() throws Exception
	{
		findSrc_main = Outside.service(this,"gus06.app.jarfile.manager.gyem.main.findsrc");
		findSrc_module = Outside.service(this,"gus06.app.jarfile.manager.gyem.module.findsrc");
		moduleCalls = Outside.service(this,"gus06.java.srccode.extract.module.calls");
		classToName = Outside.service(this,"gus06.app.manager.gyem.module.classpathtoname");
		
		classMap = (Map) Outside.resource(this,"moduleclassmap");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String src = findSrc((String) obj);
		List calls = (List) moduleCalls.t(src);
		return convertList(calls);
	}
	
	
	
	private String findSrc(String name) throws Exception
	{
		if(name.equals("GyemMain")) return (String) findSrc_main.t(name);
		if(name.equals("GyemManager")) return (String) findSrc_main.t(name);
		return (String) findSrc_module.t(name);
	}
	
	
	
	
	private List convertList(List list) throws Exception
	{
		List l = new ArrayList();
		for(Object key:list)
		{
			if(!classMap.containsKey(key))
				throw new Exception("Key not found: "+key);
			Class c = (Class) classMap.get(key);
			l.add(classToName(c));
		}
		return l;
	}



	private String classToName(Class c) throws Exception
	{return (String) classToName.t(c.getName());}
}
