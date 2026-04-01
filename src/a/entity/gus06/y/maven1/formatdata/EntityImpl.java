package a.entity.gus06.y.maven1.formatdata;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251214";}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		List children = (List) map.get("project");
		Map m = listToMap(children);
		
		String modelVersion = getString(m, "modelVersion");
		String groupId = getString(m, "groupId");
		String artifactId = getString(m, "artifactId");
		String version = getString(m, "version");
		String name = getString(m, "name");
		String description = getString(m, "description");
		String url = getString(m, "url");
		
		Map parent = listToMap(getList(m, "parent"));
		List dependencies = getList(m, "dependencies");
		
		if(groupId==null) groupId = getString(parent, "groupId");
		if(version==null) version = getString(parent, "version");
		
		if(groupId==null) throw new Exception("GroupId not found for "+m);
		if(artifactId==null) throw new Exception("ArtifactId not found for "+m);
		if(version==null) throw new Exception("Version not found for "+m);
		
		String id = groupId+":"+artifactId+":"+version;
		List depList = formatList(dependencies);
		
		List depList1 = new ArrayList();
		for(int i=0;i<depList.size();i++)
		{
			Map dep = listToMap((List) depList.get(i));
			
			String groupId_ = getString(dep, "groupId");
			String artifactId_ = getString(dep, "artifactId");
			String version_ = getString(dep, "version");
			String optional_ = getString(dep, "optional");
			
			if(groupId_==null) throw new Exception("GroupId not found for dependency: "+dep);
			if(artifactId_==null) throw new Exception("ArtifactId not found for dependency: "+dep);
			
			String id_ = groupId_+":"+artifactId_;
			if(version_!=null) id_ += ":"+version_;
			
			Map dep1 = new HashMap();
			dep1.put("groupId", groupId_);
			dep1.put("artifactId", artifactId_);
			if(version_!=null) dep1.put("version", version_);
			dep1.put("optional", optional_);
			dep1.put("id", id_);
			
			depList1.add(dep1);
		}
		
		Map output = new HashMap();
		output.put("id", id);
		output.put("groupId", groupId);
		output.put("artifactId", artifactId);
		output.put("version", version);
		output.put("name", name);
		output.put("description", description);
		output.put("url", url);
		output.put("modelVersion", modelVersion);
		output.put("dependencies", depList1);
		
		return output;
	}
	
	
	private Map listToMap(List list) throws Exception
	{
		Map output = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Map node = (Map) list.get(i);
			if(node.size()!=1) throw new Exception("Invalid node: "+node);
			String key = (String) node.keySet().iterator().next();
			Object value = node.get(key);
			output.put(key,value);
		}
		return output;
	}
	
	private List formatList(List list) throws Exception
	{
		List output = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Map node = (Map) list.get(i);
			if(node.size()!=1) throw new Exception("Invalid node: "+node);
			String key = (String) node.keySet().iterator().next();
			Object value = node.get(key);
			output.add(value);
		}
		return output;
	}
	
	private String getString(Map m, String key) throws Exception
	{
		if(!m.containsKey(key)) return null;
		return (String) m.get(key);
	}
	
	private List getList(Map m, String key) throws Exception
	{
		if(!m.containsKey(key)) return new ArrayList();
		Object value = m.get(key);
		if((value instanceof List)) return (List) value;
		if((value instanceof String)) return new ArrayList();
		throw new Exception("Invalid List for key: "+key+" [class="+value.getClass().getName()+"]");
		
	}
}
