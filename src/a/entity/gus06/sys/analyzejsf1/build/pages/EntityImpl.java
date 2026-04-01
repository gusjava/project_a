package a.entity.gus06.sys.analyzejsf1.build.pages;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190426";}

	public static final String KEY0_MAPPINGS = "mappings";
	public static final String KEY0_RESOURCES = "resources";
	public static final String KEY0_ROOTS = "roots";
	
	public static final String KEY1_ENV_PROP = "env-prop";
	public static final String KEY1_CONF_PROP = "conf-prop";
	public static final String KEY1_WEBAPP = "webapp";
	

	private Service browse;
	
	public EntityImpl() throws Exception
	{
		browse = Outside.service(this,"gus06.awt.desktop.browse");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map mappings = (Map) map.get(KEY0_MAPPINGS);
		Map resources = (Map) map.get(KEY0_RESOURCES);
		Map roots = (Map) map.get(KEY0_ROOTS);
		
		Map envProp = (Map) resources.get(KEY1_ENV_PROP);
		Map confProp = (Map) resources.get(KEY1_CONF_PROP);
		File webAppDir = (File) roots.get(KEY1_WEBAPP);
		
		String appFullURL = findConf(envProp,confProp,"appFullURL");
		
		Map pages = new HashMap();
		map.put("pages",pages);
		
		Iterator it = mappings.keySet().iterator();
		
		while(it.hasNext())
		{
			String name = (String) it.next();
			Map mapping = (Map) mappings.get(name);
			
			String pattern = (String) mapping.get("pattern");
			String beanName = (String) mapping.get("beanName");
			String viewId = (String) mapping.get("viewId");
			File javaFile = (File) mapping.get("javaFile");
			
			
			pattern = pattern.replace("#{","{").replace("/\\\\\\\\d+/","");
			String fullURL = (appFullURL+pattern).replace("//","/");
			
			String xhtmlPath = viewId;
			if(xhtmlPath.startsWith("/faces/")) xhtmlPath = xhtmlPath.substring(7);
			File xhtmlFile = new File(webAppDir,xhtmlPath);
			
			Map page = new HashMap();
			pages.put(name,page);
			
			page.put("name",name);
			page.put("pattern",pattern);
			page.put("viewId",viewId);
			page.put("xhtmlPath",xhtmlPath);
			page.put("fullURL",fullURL);
			page.put("beanName",beanName);
			page.put("javaFile",javaFile);
			page.put("xhtmlFile",xhtmlFile);
			
			transfert(mapping,page,"params");
			
			if(fullURL.contains("{"))
				page.put("browser",new BrowserP(fullURL));
			else 
				page.put("browser",new BrowserE(fullURL));
		}
	}
	
	
	
	private void transfert(Map m1, Map m2, String key)
	{
		if(!m1.containsKey(key)) return;
		m2.put(key,m1.get(key));
	}
	
	
	
	private String findConf(Map envProp, Map confProp, String key) throws Exception
	{
		if(envProp.containsKey(key)) return (String) envProp.get(key);
		if(confProp.containsKey(key)) return (String) confProp.get(key);
		throw new Exception("Conf not found: "+key);
	}
	
	
	
	
	private class BrowserE implements E
	{
		private String url;
		public BrowserE(String url) {this.url = url;}
		
		public void e() throws Exception
		{
			browse.p(url);
		}
	}
	
	private class BrowserP implements P
	{
		private String url;
		public BrowserP(String url) {this.url = url;}
		
		public void p(Object obj) throws Exception
		{
			String url_ = buildUrl(url,obj);
			browse.p(url_);
		}
	}
	
	
	
	private String buildUrl(String url, Object data) throws Exception
	{
		if(data==null)
		{
			return url.replaceAll("\\{[^\\}]+\\}","null");
		}
		if(data instanceof String)
		{
			String s = (String) data;
			return url.replaceAll("\\{[^\\}]+\\}",s);
		}
		if(data instanceof Number)
		{
			Number s = (Number) data;
			return url.replaceAll("\\{[^\\}]+\\}",""+s);
		}
		if(data instanceof Boolean)
		{
			Boolean s = (Boolean) data;
			return url.replaceAll("\\{[^\\}]+\\}",""+s);
		}
		if(data instanceof Object[])
		{
			Object[] tt = (Object[]) data;
			for(Object t:tt) url = url.replaceFirst("\\{[^\\}]+\\}",""+t);
			return url;
		}
		if(data instanceof List)
		{
			List tt = (List) data;
			for(Object t:tt) url = url.replaceFirst("\\{[^\\}]+\\}",""+t);
			return url;
		}
		if(data instanceof Map)
		{
			Map m = (Map) data;
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = ""+m.get(key);
				url = url.replace("{"+key+"}",value);
			}
			return url;
		}
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
