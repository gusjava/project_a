package a.entity.gus06.sys.filemanagement1.analyze.stats.error.prop;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201014";}


	private Service buildFileMap;
	private Service walkThrough;
	private Service scannable;
	private Service mapToString;

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
		scannable = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.scannable.md5size");
		mapToString = Outside.service(this,"gus06.tostring.map.tn");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		Handler handler = new Handler((R) engine);
		
		Map fileMap = (Map) buildFileMap.t(engine);
		walkThrough.p(new Object[]{fileMap,handler});
		
		String summary = (String) mapToString.t(handler.map);
		p.println(summary);
	}
	
	
	
	private class Handler implements P
	{
		private R engine;
		private Map map;
		
		public Handler(R engine)
		{
			this.engine = engine;
			map = new HashMap();
		}
		
		public void p(Object obj) throws Exception
		{
			String[] row = (String[]) obj;
			
			String rootName = row[0];
			String location = row[1];
			String fileName = row[2];
			String size = row[3];
			String md5 = row[5];
			
			if(scannable.f(new Object[]{engine,md5,size}))
			{
				Map prop = (Map) engine.r("prop:"+md5);
				if(prop!=null)
				{
					Iterator it = prop.keySet().iterator();
					while(it.hasNext())
					{
						String key = (String) it.next();
						if(key.endsWith(".error"))
						increase(key);
					}
				}
			}
		}
		
		private void increase(String key)
		{
			if(!map.containsKey(key)) map.put(key,Integer.valueOf(1));
			else
			{
				Integer n = (Integer) map.get(key);
				map.put(key,Integer.valueOf(n.intValue()+1));
			}
		}
	}
}