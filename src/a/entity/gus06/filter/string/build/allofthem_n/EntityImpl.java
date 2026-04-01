package a.entity.gus06.filter.string.build.allofthem_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}
	
	
	private Service normalize;
	private Service splitMethod;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		splitMethod = Outside.service(this,"gus06.string.split.method2");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = normalize((String)obj);
		String[] words = (String[]) splitMethod.t(s);
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
			String str = normalize((String) toString.t(obj));
			for(int i=0;i<words.length;i++)
				if(!str.contains(words[i])) return false;
			return true;
		}
	}
}
