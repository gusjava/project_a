package a.entity.gus06.appli.entityaccess.api.build.md5list;

import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150815";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		int count = n.length-1;
		String header = n[0].trim();
		
		if(!header.equals("md5:"+count)) throw new Exception("Invalid header: "+header);
		
		ArrayList list = new ArrayList();
		for(int i=0;i<count;i++) list.add(n[i+1].trim());
		return list;
	}
}
