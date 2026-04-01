package a.entity.gus06.sys.filemanagement1.analyze.stats.prop.filetype;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201103";}

	public static final String KEY = "mime";
	public static final String NOT_FOUND = "NOT_FOUND";

	private Service mapToString;
	private Service buildFileMap;
	private Service walkThrough;
	private Service scannable;
	private Service mimeToFileType;

	public EntityImpl() throws Exception
	{
		mapToString = Outside.service(this,"gus06.tostring.map.tn");
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
		scannable = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.scannable.md5size");
		mimeToFileType = Outside.service(this,"gus06.file.mime.tofiletype1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		Handler handler = new Handler(engine);
		Map fileMap = (Map) buildFileMap.t(engine);
		walkThrough.p(new Object[]{fileMap,handler});
		
		String summary = (String) mapToString.t(handler.map);
		p.println(summary);
	}
	
	
	
	private class Handler implements P
	{
		private Map map = new HashMap();
		private Object engine;
		
		public Handler(Object engine)
		{this.engine = engine;}
		
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
				String fileType = (String) mimeToFileType.t(mime);
				increase(fileType);
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