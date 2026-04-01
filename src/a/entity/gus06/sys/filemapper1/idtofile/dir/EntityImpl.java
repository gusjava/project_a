package a.entity.gus06.sys.filemapper1.idtofile.dir;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}

	public static final String S = File.separator;
	
	public static final String KEY_SRC = "src";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map root = (Map) o[0];
		String main = (String) o[1];
		String id = format((String) o[2]);
		
		File src = (File) get1(root,KEY_SRC);
		return idToFile(src,main,id);
	}
	
	
	
	private File idToFile(File root, String main, String id) throws Exception
	{
		if(root==null || !root.isDirectory()) throw new Exception("Root undefined: " + root);
		if(id==null) throw new Exception("Id undefined: null");
		
		if(main==null) return new File(root,id);
		if(id.startsWith(S)) return new File(root,id);
		
		String path1 = id.replace(".",S)+S+main;
		return new File(root,path1);
	}
	
	
	private String format(String s)
	{return s.replace("\\",S).replace("/",S);}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
}