package a.entity.gus06.sys.filetool.ext.library1.perform.paste1.items;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201229";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service genAlphanum;

	public EntityImpl() throws Exception
	{
		genAlphanum = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String items = (String) o[1];
		
		if(items.equals("")) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) struct = "";
		
		String[] lines = items.split("\n");
		for(String line : lines) if(line.contains("\t"))
		{
			String[] k = line.split("\t");
			String display0 = k[0];
			String path0 = k[1];
			
			String[] nn = struct.split(";");
		
			String key0 = (String) genAlphanum.g();
			while(indexOf(nn,key0)!=-1) key0 = (String) genAlphanum.g();
			
			map.put(CONTENT+"."+key0,path0);
			map.put(DISPLAY+"."+key0,display0);
			
			struct = struct.equals("") ? key0 : (key0+";"+struct);
		}
		map.put(STRUCT,struct);
		return true;
	}
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private int indexOf(String[] nn, String s)
	{
		if(nn==null || s==null) return -1;
		for(int i=0;i<nn.length;i++) if(nn[i].equals(s)) return i;
		return -1;
	}
}