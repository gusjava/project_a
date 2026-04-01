package a.entity.gus06.dir.runtask.text.display.filelinks;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190516";}

	// CODE A REVOIR !!!!

	private Service buildMap;
	private Service isText;
	private Service readFile;
	private Service showText;
	private Service mapLinksToString;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.dir.listing.dirtomap.frelpath_file");
		isText = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		showText = Outside.service(this,"gus06.swing.frame.show.text");
		mapLinksToString = Outside.service(this,"gus06.tostring.maplinks");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Map map = (Map) buildMap.t(dir);
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		Map map1 = new HashMap();
		
		if(progress!=null) ((V)progress).v("size",""+keys.size());
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			handle(key,map,map1);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		String text = (String) mapLinksToString.t(map1);
		showText.p(text);
	}
	
	
	private void handle(String k0, Map map, Map map1) throws Exception
	{
		File f = (File) map.get(k0);
		if(!isText.f(f)) return;
		
		String text = (String) readFile.t(f);
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String k1 = (String) it.next();
			String k2 = k1.replace("\\","/");
			
			//cette condition n'est pas suffisante
			//il faut prendre en compte les chemins relatifs par rapport
			//au r�pertoire du fichier f
			
			if(text.contains("\""+k1+"\"") 
			|| text.contains("\""+k2+"\"") 
			|| text.contains("'"+k1+"'") 
			|| text.contains("'"+k2+"'"))
			{
				addToMap(map1,k0,k1);
			}
		}
	}
	
	
	private void addToMap(Map m, String k0, String k1)
	{
		if(!m.containsKey(k0)) m.put(k0,new HashSet());
		((Set) m.get(k0)).add(k1);
	}
}
