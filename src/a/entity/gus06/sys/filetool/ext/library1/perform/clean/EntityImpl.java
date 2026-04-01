package a.entity.gus06.sys.filetool.ext.library1.perform.clean;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230117";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service confirm;
	private Service fileProvider;

	public EntityImpl() throws Exception
	{
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		fileProvider = Outside.service(this,"m102.r.fileprovider");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String struct = get0(map,STRUCT);
		if(struct==null) return false;
		
		boolean ok = confirm.f("You are about to clean the library. Continue ?");
		if(!ok) return false;
		
		String[] nn = struct.split(";");
		int nb = nn.length;
		
		Set done = new HashSet();
		StringBuffer b = new StringBuffer();
		boolean removed = false;
		
		for(int i=0;i<nb;i++)
		{
			String key = nn[i];
			String path = (String) map.get(CONTENT+"."+key);
			if(fileFound(path) && !done.contains(path))
			{
				done.add(path);
				b.append(nn[i]+";");
			}
			else
			{
				map.remove(DISPLAY+"."+key);
				map.remove(CONTENT+"."+key);
				removed = true;
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		map.put(STRUCT,b.toString());
		
		return removed;
	}
	

	private File file(String path) throws Exception
	{return (File) fileProvider.r(path);}
	
	
	private boolean fileFound(String path) throws Exception
	{
		File file = file(path);
		return file!=null && file.exists();
	}
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}