package a.entity.gus06.dir.listing0.timestamped.mostrecent;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150727";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] ff = dir.listFiles();
		
		Map m1 = new HashMap();
		Map m2 = new HashMap();
		
		for(File f:ff)
		{
			String[] n = analyzeName(f.getName());
			if(n==null) continue;
			String timeStamp = n[0];
			String key = n[1];
			
			if(isMostRecent(m1,key,timeStamp))
			{
				m1.put(key,timeStamp);
				m2.put(key,f);
			}
		}
		
		List list = new ArrayList();
		Iterator it = m2.keySet().iterator();
		
		while(it.hasNext())
		list.add(m2.get(it.next()));
		
		return list;
	}
	
	
	
	private boolean isMostRecent(Map m1, String key, String timeStamp)
	{
		if(!m1.containsKey(key)) return true;
		String timeStamp0 = (String) m1.get(key);
		return timeStamp.compareTo(timeStamp0)>0;
	}
	
	
	
	// format de fichier attendu :
	// 20150711_091626_AUZALIAM.txt
	
	private String[] analyzeName(String name)
	{
		String[] n = name.split("_",3);
		
		if(n.length!=3) return null;
		if(n[0].length()!=8) return null;
		if(n[1].length()!=6) return null;
		if(!isInt(n[0])) return null;
		if(!isInt(n[1])) return null;
		
		return new String[]{n[0]+"_"+n[1],n[2]};
	}
	
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);}
		catch(NumberFormatException e){return false;}
		return true;
	}
}
