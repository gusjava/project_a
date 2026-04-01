package a.entity.gus06.sys.git1.tool.commit.build.filter;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230227";}
	
	public static final String KEY_AUTHOR = "author";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_SRC = "src";
	

	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.git1.tool.commit.build.filter2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Object[] data = (Object[]) build.t(obj);
		if(data==null) return null;
		if(data.length!=2) throw new Exception("Wrong data number: "+data.length);
		
		return new Filter((F) data[0], (String) data[1]);
	}
	
	
	private class Filter implements F
	{
		private F filter;
		private boolean filterAuthor;
		private boolean filterMessage;
		private boolean filterSrc;
		
		public Filter(F filter, String options) throws Exception
		{
			this.filter = filter;
			filterAuthor = options.contains("@");
			filterMessage = options.contains(":");
			filterSrc = options.contains(">");
		}
		
		public boolean f(Object obj) throws Exception
		{
			Map m = (Map) obj;
			
			if(filterAuthor)
			{
				String author = (String) m.get(KEY_AUTHOR);
				if(filter.f(author)) return true;
			}
			if(filterMessage)
			{
				String message = (String) m.get(KEY_MESSAGE);
				if(filter.f(message)) return true;
			}
			if(filterSrc)
			{
				String src = (String) m.get(KEY_SRC);
				if(filter.f(src)) return true;
			}
			return false;
		}
	}
}