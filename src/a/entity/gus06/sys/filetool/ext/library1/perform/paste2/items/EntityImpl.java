package a.entity.gus06.sys.filetool.ext.library1.perform.paste2.items;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201231";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys0 = (List) o[1];
		String items = (String) o[2];
		
		if(items.equals("")) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) struct = "";
		
		String[] lines = items.split("\n");
		
		int nb = Math.min(keys0.size(),lines.length);
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys0.get(i);
			String display0 = get0(map,DISPLAY+"."+key);
			String display1 = lines[i].split("\t")[0];
			
			String newDisplay = buildDisplay(display0, display1);
			map.put(DISPLAY+"."+key,newDisplay);
		}
		return true;
	}
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private String buildDisplay(String display0, String display1)
	{
		String[] nn0 = display0.split("#",2);
		String iconKey0 = nn0.length==2 ? nn0[0] : null;
		String fileName0 = nn0.length==2 ? nn0[1] : nn0[0];
		
		String[] nn1 = display1.split("#",2);
		String iconKey1 = nn1.length==2 ? nn1[0] : null;
		String fileName1 = nn1.length==2 ? nn1[1] : nn1[0];
		
		StringBuffer b = new StringBuffer();
		if(iconKey1!=null) b.append(iconKey1+"#");
		if(fileName1.contains(".")) b.append(fileName0);
		else b.append(name0(fileName0));
		
		return b.toString();
	}
	
	
	private String name0(String name)
	{
		if(!name.contains(".")) return name;
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		return name.substring(0,name.length()-ext.length()-1);
	}
}