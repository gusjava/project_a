package a.entity.gus06.file.buildfilter.txtcontentfilter;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220208";}


	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.sys.textfile1.read");
	}

	
	public Object t(Object obj) throws Exception
	{
		return new Filter((F) obj);
	}
	
	private class Filter implements F
	{
		private F filter;
		public Filter(F filter) {this.filter = filter;}
		
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			if(!file.isFile()) return false;
			
			String content = (String) readFile.t(file);
			if(content==null) return false;
			return filter.f(content);
		}
	}
}