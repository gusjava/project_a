package a.entity.gus06.tostring.desc.map;

import a.framework.*;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}
	
	
	private Service short1;
	
	public EntityImpl() throws Exception
	{short1 = Outside.service(this,"gus06.tostring.desc.short1");}



	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		StringBuffer b = new StringBuffer();
		b.append(short1.t(map)+"\n");
		
		List list = new ArrayList(map.keySet());
		
		try{Collections.sort(list);}
		catch(Exception e){}
		
		for(int i=0;i<list.size();i++)
		{
			Object key = list.get(i);
			Object value = map.get(key);
			
			b.append(short1.t(key)+"="+short1.t(value)+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
