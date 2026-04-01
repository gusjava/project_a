package a.entity.gus06.sys.filetool.ext.library1.perform.paste3.files;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220602";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service genAlphanum;
	private Service fileDispay;

	public EntityImpl() throws Exception
	{
		genAlphanum = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		fileDispay = Outside.service(this,"gus06.file.getdisplay");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List list = (List) o[1];
		
		if(list.isEmpty()) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) struct = "";
		
		for(int i=0;i<list.size();i++)
		{
			File file = (File) list.get(i);
			
			String[] nn = struct.split(";");
		
			String key0 = (String) genAlphanum.g();
			while(indexOf(nn,key0)!=-1) key0 = (String) genAlphanum.g();
			
			String path0 = file.getAbsolutePath();
			String display0 = (String) fileDispay.t(file);
			
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