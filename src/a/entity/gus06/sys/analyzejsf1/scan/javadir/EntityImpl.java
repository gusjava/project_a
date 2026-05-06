package a.entity.gus06.sys.analyzejsf1.scan.javadir;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190308";}

	public static final String KEY0_CONF = "conf";
	public static final String KEY0_ROOTS = "roots";
	public static final String KEY0_BEANS = "beans";
	public static final String KEY0_SERVICES = "services";
	public static final String KEY0_DAOS = "daos";
	public static final String KEY0_MAPPINGS = "mappings";
	
	public static final String KEY1_JAVA = "java";
	public static final String KEY1_SERVICE_VALIDATOR = "service_validator";
	public static final String KEY1_BEAN_VALIDATOR = "bean_validator";
	

	private Service scan;
	private Service readFile;
	private Service extractAll;
	private Service extractOne;
	private Service name0;

	public EntityImpl() throws Exception
	{
		scan = Outside.service(this,"gus06.dir.perform.scanfiles.byext");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		extractAll = Outside.service(this,"gus06.string.extract.extract2.find.s.a");
		extractOne = Outside.service(this,"gus06.string.extract.extract2.find.s.f");
		name0 = Outside.service(this,"gus.x.file.getname0");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Map conf = (Map) get1(map,KEY0_CONF);
		Map roots = (Map) get1(map,KEY0_ROOTS);
		
		File javaRoot = (File) get1(roots,KEY1_JAVA);
		
		F serviceValidator = (F) get(conf,KEY1_SERVICE_VALIDATOR);
		F beanValidator = (F) get(conf,KEY1_BEAN_VALIDATOR);
		
		G g = (G) scan.t(new Object[]{javaRoot,"java"});
		
		List list = new ArrayList();
		map.put("java-files",list);
		
		Map beans = new HashMap();
		map.put(KEY0_BEANS,beans);
		
		Map services = new HashMap();
		map.put(KEY0_SERVICES,services);
		
		Map daos = new HashMap();
		map.put(KEY0_DAOS,daos);
		
		Map mappings = new HashMap();
		map.put(KEY0_MAPPINGS,mappings);
		
		File f = (File) g.g();
		while(f!=null)
		{
			list.add(f);
			String src = (String) readFile.t(f);
			
			extractDAO(daos,src,f);
			extractService(services,src,f,serviceValidator);
			
			String beanName = extractBean(beans,src,f,beanValidator);
			extractURLMappings(mappings,src,f,beanName);
			
			f = (File) g.g();
		}
	}
	
	
	
	private void extractDAO(Map map, String src, File f) throws Exception
	{
		if(!src.contains("@Repository")) return;
		String name = fileToName0(f);
		
		String table = extractOne(src,"TABLE_NAME = \"*\"");
		
		Map m = new HashMap();
		
		m.put("name",name);
		if(table!=null) m.put("table",table);
		m.put("javaFile",f);
		
		if(map.containsKey(name)) throw new Exception("DAO name is used many times: "+name);
		map.put(name,m);
	}
	
	
	
	
	private String extractService(Map map, String src, File f, F validator) throws Exception
	{
		String name = extractOne(src,"@Service(*)");
		if(name==null) return null;
		name = findValue(src,name);
		
		if(validator!=null)
		{
			if(!validator.f(new Object[]{f,name})) 
				throw new Exception("Invalid java file name for service: "+f.getName());
		}
		else if(!Objects.equals(name,fileToName0c(f)))
			throw new Exception("Invalid java file name for service: "+f.getName());
		
		Map m = new HashMap();
		m.put("name",name);
		m.put("javaFile",f);
			
		if(map.containsKey(name)) throw new Exception("service name is used many times: "+name);
		map.put(name,m);
		
		return name;
	}
	
	
	
	private String extractBean(Map map, String src, File f, F validator) throws Exception
	{
		String name = extractOne(src,"@ManagedBean(name = *)");
		if(name==null) return null;
		name = findValue(src,name);
		
		if(validator!=null)
		{
			if(!validator.f(new Object[]{f,name})) 
				throw new Exception("Invalid java file name for bean: "+f.getName());
		}
		else if(!Objects.equals(name,fileToName0c(f)))
			throw new Exception("Invalid java file name for bean: "+f.getName());
		
		Map m = new HashMap();
		m.put("name",name);
		m.put("javaFile",f);
			
		if(map.containsKey(name)) throw new Exception("bean name is used many times: "+name);
		map.put(name,m);
		
		return name;
	}
	
	
	
	
	private void extractURLMappings(Map map, String src, File f, String beanName) throws Exception
	{
		try
		{
			List l = extractAll(src,"@URLMapping(*)");
			if(!l.isEmpty() && beanName==null) throw new Exception("URLMapping found inside non bean java file: "+f);
			
			for(int i=0;i<l.size();i++)
			{
				String el = (String) l.get(i);
				String[] nn = el.split(",");
				
				Map m = new HashMap();
				
				for(String n : nn)
				{
					String[] kk = n.split("=",2);
					String key = kk[0].trim();
					String value = findValue(src,kk[1].trim());
					
					if(value!=null) m.put(key,value);
				}
				
				checkKey(m,"id");
				checkKey(m,"pattern");
				checkKey(m,"viewId");
				
				
				String pattern = (String) m.get("pattern");
				String id = (String) m.get("id");
				
				pattern = pattern.replace("/\\\\\\\\d+/","");
				List params = extractAll(pattern,"#{*}");
				
				if(!params.isEmpty()) m.put("params",params);
				
				m.put("javaFile",f);
				m.put("beanName",beanName);
				
				if(map.containsKey(id)) throw new Exception("URL mapping id used many times: "+id);
				map.put(id,m);
			}
		}
		catch(Exception e)
		{
			String message = "Failed to extract url mappings for beanName="+beanName+" (file:"+f+")";
			throw new Exception(message,e);
		}
	}
	
	
	
	private List extractAll(String src, String rule) throws Exception
	{return (List) extractAll.t(new String[]{src,rule});}
	
	private String extractOne(String src, String rule) throws Exception
	{return (String) extractOne.t(new String[]{src,rule});}
	
	private void checkKey(Map m, String key) throws Exception
	{if(!m.containsKey(key)) throw new Exception("Key not found inside map: "+key);}
	
	
	private String fileToName0c(File file) throws Exception
	{
		String s = fileToName0(file);
		return s.substring(0,1).toLowerCase() + s.substring(1);
	}
	
	private String fileToName0(File file) throws Exception
	{
		return (String) name0.t(file);
	}
	
	
	private String findValue(String src, String value) throws Exception
	{
		if(value==null) return null;
		
		if(value.startsWith("\"") && value.endsWith("\""))
			return value.substring(1,value.length()-1);
			
		String[] nn = value.split("\\.");
		String constV = nn[nn.length-1];
		
		return extractOne(src,"public static final String "+constV+" = \"*\";");
	}
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
	
	private Object get1(Map m, String key) throws Exception
	{
		if(m.containsKey(key)) return m.get(key);
		throw new Exception("Key not found: "+key);
	}
}