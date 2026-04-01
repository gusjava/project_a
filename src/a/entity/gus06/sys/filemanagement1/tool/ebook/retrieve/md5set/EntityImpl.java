package a.entity.gus06.sys.filemanagement1.tool.ebook.retrieve.md5set;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201103";}


	private Service buildFileMap;
	private Service walkThrough;
	private Service scannable;

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
		scannable = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.scannable.md5size");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map fileMap = (Map) buildFileMap.t(obj);
		Handler handler = new Handler(obj);
		walkThrough.p(new Object[]{fileMap,handler});
		
		return handler.md5Set;
	}
	
	
	
	private class Handler implements P
	{
		private Set md5Set = new HashSet();
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
				if(isEbook(mime)) md5Set.add(md5);
			}
		}
		
		private boolean isEbook(String mime)
		{
			return mime.equals("application/epub+zip") 
			|| mime.equals("application/x-mobipocket-ebook");
		}
	}
}