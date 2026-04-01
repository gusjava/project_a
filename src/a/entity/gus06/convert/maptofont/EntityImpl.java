package a.entity.gus06.convert.maptofont;

import a.framework.*;
import java.awt.Font;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}


	public EntityImpl() throws Exception
	{
	}


	public Object t(Object obj) throws Exception
	{return mapToFont((Map) obj);}
	
	
	private Font mapToFont(Map map) throws Exception
 	{
		String name = (String) get(map,"name");
		int style = findStyle(get(map,"style"));
		int size = findSize(get(map,"size"));
        
		return new Font(name,style,size);
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
    

    
	private int findStyle(Object obj) throws Exception
	{
		if(obj==null) return 0;
		
		if(obj instanceof String)
		{
			String info = (String) obj;
			if(info.toLowerCase().equals("plain")) return 0;
			if(info.toLowerCase().equals("bold")) return 1;
			if(info.toLowerCase().equals("italic")) return 2;
			if(info.toLowerCase().equals("bold|italic")) return 3;
			if(info.toLowerCase().equals("italic|bold")) return 3;
			return Integer.parseInt(info);
		}
		if(obj instanceof Integer)
		{
			return ((Integer) obj).intValue();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int findSize(Object obj) throws Exception
	{
		if(obj==null) return 12;
		
		if(obj instanceof String)
		{
			return Integer.parseInt((String) obj);
		}
		if(obj instanceof Integer)
		{
			return ((Integer) obj).intValue();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
