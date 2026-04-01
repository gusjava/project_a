package a.entity.gus06.sys.filemapper1.holder;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T, R, V, F {

	public String creationDate() {return "20160212";}
	
	public static final String KEY_TYPE = "type";

	private Service buildRoot;
	private Service mapping;
	private Service fileToId;
	private Service idToFile;
	private Service idToScript;
	private Service idToData;
	
	private Map root;
	private Map map;
	private String main;


	public EntityImpl() throws Exception
	{
		buildRoot = Outside.service(this,"gus06.sys.filemapper1.buildroot");
		mapping = Outside.service(this,"gus06.sys.filemapper1.mapping");
		fileToId = Outside.service(this,"gus06.sys.filemapper1.filetoid");
		idToFile = Outside.service(this,"gus06.sys.filemapper1.idtofile");
		idToScript = Outside.service(this,"gus06.sys.filemapper1.idtoscript");
		idToData = Outside.service(this,"gus06.sys.filemapper1.idtodata");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.startsWith("script:")) return idToScript(key.substring(7));
		if(key.startsWith("file:")) return idToFile(key.substring(5));
		if(key.startsWith("data:")) return idToData(key.substring(5));
		
		if(key.equals("type")) return type();
		if(key.equals("root")) return root;
		if(key.equals("map")) return map;
		if(key.equals("main")) return main;
		
		if(key.equals("keys")) return new String[]{"type","root","map","main"};
		
		throw new Exception("Unknown key: " + key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root"))		{root = (Map) buildRoot.t(obj);return;}
		if(key.equals("map"))		{map = (Map) obj;return;}
		if(key.equals("main"))		{main = (String) obj;return;}
		
		throw new Exception("Unknown key: " + key);
	}

	
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof File) return fileToId((File) obj)!=null;
		if(obj instanceof String) return idToData((String) obj)!=null;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return fileToId((File) obj);
		if(obj instanceof String) return idToFile((String) obj);
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String key = (String) o[1];
		
		String id = fileToId(file);
		String id1 = mapping(id,key);
		return idToData(id1);
	}
	
	
	
	private String mapping(String id, String key) throws Exception
	{
		try
		{
			return (String) mapping.t(new Object[]{map,id,key});
		}
		catch(Exception e)
		{
			String message = "failed to resolve mapping for id="+id+" and key="+key;
			throw new Exception(message,e);
		}
	}
	
	
	private String type()
	{
		if(root==null) return "";
		return (String) root.get(KEY_TYPE);
	}
	
	
	private String fileToId(File file) throws Exception
	{return (String) fileToId.t(new Object[]{root,main,file});}
	
	
	private File idToFile(String id) throws Exception
	{return (File) idToFile.t(new Object[]{root,main,id});}
	
	
	private String idToScript(String id) throws Exception
	{return (String) idToScript.t(new Object[]{root,main,id});}
	
	
	private Object idToData(String id) throws Exception
	{return idToData.t(new Object[]{root,main,id});}
}