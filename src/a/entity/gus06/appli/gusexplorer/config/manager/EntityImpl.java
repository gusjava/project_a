package a.entity.gus06.appli.gusexplorer.config.manager;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl extends S1 implements Entity, R, G, V, E {

	public String creationDate() {return "20160419";}


	private Service namesPersister;
	private Service listPersister;
	
	private List names;



	public EntityImpl() throws Exception
	{
		namesPersister = Outside.service(this,"gus06.app.persister1.data.list");
		listPersister = Outside.service(this,"gus06.app.persister1.data.filelist");
		
		String keyNames = getClass().getName()+"_names";
		names = (List) namesPersister.r(keyNames);
		if(names==null) names = new ArrayList();
	}
	
	
	
	public Object g() throws Exception
	{return names;}
	
	
	public void e() throws Exception
	{updateNames();}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("remove")) {remove((String) obj);return;}
		if(key.equals("rename")) {rename((String[]) obj);return;}
		if(key.equals("init")) {init((Map) obj);return;}
		if(key.equals("persistList")) {persistList((Object[]) obj);return;}
		if(key.equals("addFile")) {addFile((Object[]) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("*")) return retrieveAll();
		return retrieveList(key);
	}
	
	
	
	
	private void init(Map map) throws Exception
	{
		Iterator it = map.keySet().iterator();
		boolean namesUpdated = false;
		while(it.hasNext())
		{
			String name = (String) it.next();
			List list = (List) map.get(name);
			persistList(name, list);
			
			if(!names.contains(name))
			{
				names.add(0,name);
				namesUpdated = true;
			}
		}
		for(int i=0;i<names.size();i++)
		{
			String name = (String) names.get(i);
			if(!map.containsKey(name))
			{
				names.remove(name);
				namesUpdated = true;
			}
		}
		if(namesUpdated) updateNames();
	}
	
	
	
	
	private void remove(String name) throws Exception
	{
		if(!names.contains(name)) return;
		
		names.remove(name);
		updateNames();
		persistList(name,null);
	}
	
	
	private void rename(String[] infos) throws Exception
	{
		if(infos.length!=2) throw new Exception("Invalid info length: "+infos.length);
		rename(infos[0],infos[1]);
	}
	
	
	private void rename(String oldName, String newName) throws Exception
	{
		if(!names.contains(oldName)) return;
		
		List list = retrieveList(oldName);
		persistList(newName,list);
		
		names.remove(oldName);
		names.add(0,newName);
		updateNames();
	}
	
	
	private List retrieveList(String name) throws Exception
	{
		String key = getClass().getName()+"_config_"+name;
		return (List) listPersister.r(key);
	}
	
	private Map retrieveAll() throws Exception
	{
		Map map = new HashMap();
		for(int i=0;i<names.size();i++)
		{
			String name = (String) names.get(i);
			map.put(name, retrieveList(name));
		}
		return map;
	}
	
	
	private void addFile(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		addFile((String) o[0], (File) o[1]);
	}
	
	private void addFile(String name, File file) throws Exception
	{
		List list = retrieveList(name);
		if(list.contains(file)) return;
		list.add(file);
		persistList(name, list);
	}
	
	
	private void persistList(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		persistList((String) o[0], (List) o[1]);
	}
	
	private void persistList(String name, List list) throws Exception
	{
		String key = getClass().getName()+"_config_"+name;
		listPersister.v(key,list);
	}
	
	private void updateNames() throws Exception
	{
		String keyNames = getClass().getName()+"_names";
		namesPersister.v(keyNames,names);
		updated();
	}
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void updated()
	{send(this,"updated()");}
}