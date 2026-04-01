package a.entity.gus06.sys.filetool.ext.library1.perform.first;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230117";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys0 = (List) o[1];
		
		if(keys0==null || keys0.isEmpty()) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) return false;
		
		String[] nn = struct.split(";");
		int nb = nn.length;
		
		String[] nn1 = new String[nb];
		for(int i=0;i<keys0.size();i++)
		{
			String key0 = (String) keys0.get(i);
			nn1[i] = key0;
		}
		
		int k = keys0.size();
		for(int i=0;i<nn.length;i++)
		if(!keys0.contains(nn[i]))
		{
			nn1[k] = nn[i];
			k++;
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			b.append(nn1[i]);
			if(i<nb-1) b.append(";");
		}
		map.put(STRUCT,b.toString());
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