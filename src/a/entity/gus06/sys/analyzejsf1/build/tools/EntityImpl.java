package a.entity.gus06.sys.analyzejsf1.build.tools;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190426";}


	private Service buildCx;
	
	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.jdbc.connection.builder");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map resources = (Map) map.get("resources");
		Map envProp = (Map) resources.get("env-prop");
		Map confProp = (Map) resources.get("conf-prop");
		
		String url = findConf(envProp,confProp,"spring.datasource.url");
		String username = findConf(envProp,confProp,"spring.datasource.username");
		String password = findConf(envProp,confProp,"spring.datasource.password");
		
		
		T confFinder = new ConfFinder(envProp,confProp);
		G cxBuilder = new CxBuilder(url,username,password);
		
		
		Map tools = new HashMap();
		map.put("tools",tools);
		
		tools.put("conf-finder",confFinder);
		tools.put("cx-builder",cxBuilder);
	}
	
	
	private String findConf(Map envProp, Map confProp, String key) throws Exception
	{
		if(envProp.containsKey(key)) return (String) envProp.get(key);
		if(confProp.containsKey(key)) return (String) confProp.get(key);
		throw new Exception("Conf not found: "+key);
	}
	
	private Object buildCx(String url, String username, String password) throws Exception
	{
		return buildCx.t(new String[]{url,username,password});
	}
	
	
	
	private class ConfFinder implements T
	{
		private Map envProp;
		private Map confProp;
		
		public ConfFinder(Map envProp, Map confProp)
		{
			this.envProp = envProp;
			this.confProp = confProp;
		}
		
		public Object t(Object obj) throws Exception
		{return findConf(envProp,confProp,(String) obj);}
	}
	
	
	
	private class CxBuilder implements G
	{
		private String url;
		private String username;
		private String password;
		
		public CxBuilder(String url, String username, String password)
		{
			this.url = url;
			this.username = username;
			this.password = password;
		}
		
		public Object g() throws Exception
		{return buildCx(url,username,password);}
	}
}
