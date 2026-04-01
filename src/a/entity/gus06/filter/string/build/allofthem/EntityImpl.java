package a.entity.gus06.filter.string.build.allofthem;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}
	
	
	private Service splitMethod;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		splitMethod = Outside.service(this,"gus06.string.split.method2");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] words = (String[]) splitMethod.t(obj);
		return new Filter(words);
	}
	
	
	private class Filter implements F
	{
		private String[] words;
		public Filter(String[] words)
		{this.words = words;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			for(int i=0;i<words.length;i++)
				if(!str.contains(words[i])) return false;
			return true;
		}
	}
}
