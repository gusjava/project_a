package a.entity.gus06.y.maven1.query1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251214";}

	private Service call;
	private Service formatData;

	public EntityImpl() throws Exception
	{
		call = Outside.service(this,"gus06.y.maven1.webapi.call1");
		formatData = Outside.service(this,"gus06.y.maven1.formatdata");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String groupId = (String) o[0];
		String artifactId = (String) o[1]; 
		String version = (String) o[2];
		
		Map output = new HashMap();
		handle(output, groupId, artifactId, version);
		return output;
	}
	
	private void handle(Map output, String groupId, String artifactId, String version) throws Exception
	{
		String id = groupId+":"+artifactId+":"+version;
		if(output.containsKey(id)) return;
		
		Map m = call(groupId, artifactId, version);
		m = (Map) formatData.t(m);
		
		output.put(id, m);
		
		List deps = (List) m.get("dependencies");
		if(deps==null) return;
		
		for(int i=0;i<deps.size();i++)
		{
			Map dep = (Map) deps.get(i);
			
			String groupId_ = (String) dep.get("groupId");
			String artifactId_ = (String) dep.get("artifactId"); 
			String version_ = (String) dep.get("version");
			
			if(groupId_==null)
				throw new Exception("Invalid dependency node: "+dep+" (null groupId)");
			if(artifactId_==null)
				throw new Exception("Invalid dependency node: "+dep+" (null artifactId)");
			if(version_==null)
				throw new Exception("Invalid dependency node: "+dep+" (null version)");
			
			handle(output, groupId_, artifactId_, version_);
		}
	}
	
	private Map call(String groupId, String artifactId, String version) throws Exception
	{return (Map) call.t(new Object[]{groupId, artifactId, version});}
}
