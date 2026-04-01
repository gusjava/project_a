package a.entity.gus06.filter.string.build.oneofthem;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service cuttingMethod;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		cuttingMethod = Outside.service(this,"gus06.string.split.method1");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] words = (String[]) cuttingMethod.t(obj);
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
				if(str.contains(words[i])) return true;
			return false;
		}
	}
}
