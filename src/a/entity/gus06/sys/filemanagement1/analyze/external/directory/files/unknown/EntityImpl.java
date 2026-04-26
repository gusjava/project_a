package a.entity.gus06.sys.filemanagement1.analyze.external.directory.files.unknown;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230326";}


	private Service clipboard;
	private Service dirToMd5Map;
	private Service buildFileMap;
	private Service walkThrough;
	private Service scannable;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.y.clipboard1.files");
		dirToMd5Map = Outside.service(this,"gus06.dir.listing.dir1tomap.md5_files");
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
		scannable = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.scannable.md5size");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		List files = (List) clipboard.g();
		if(files==null)
		{
			p.println("No files or directories inside clipboard");
			return;
		}
		p.println("Root number: "+files.size());
		
		p.println("Scanning roots...");
		Map md5Map = (Map) dirToMd5Map.t(files);
		p.println("Files detected: "+md5Map.size());
		
		p.println("Looking for unknown files...");
		p.println("__________________________");
		
		Map fileMap = (Map) buildFileMap.t(engine);
		Handler handler = new Handler(engine, md5Map, p);
		walkThrough.p(new Object[]{fileMap,handler});
		
		Iterator it1 = md5Map.keySet().iterator();
		while(it1.hasNext())
		{
			String md5 = (String) it1.next();
			Set set = (Set) md5Map.get(md5);
			Iterator it2 = set.iterator();
			while(it2.hasNext())
			{
				File f = (File) it2.next();
				p.println(f.getAbsolutePath());
			}
		}
	}
	
	
	private class Handler implements P
	{
		private Object engine;
		private Map md5Map;
		private PrintStream p;
		
		public Handler(Object engine, Map md5Map, PrintStream p)
		{
			this.engine = engine;
			this.md5Map = md5Map;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{
			String[] row = (String[]) obj;
			
			String rootName = row[0];
			String location = row[1];
			String fileName = row[2];
			String size = row[3];
			String modified = row[4];
			String md5 = row[5];
			String mime = row[6];
			
			if(scannable.f(new Object[]{engine,md5,size}))
			{
				if(md5Map.containsKey(md5))
				{
					md5Map.remove(md5);
					if(md5Map.isEmpty()) return;
				}
			}
		}
	}
}