package a.entity.gus06.filter.string.build.oneofthem_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service normalize;
	private Service cuttingMethod;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		cuttingMethod = Outside.service(this,"gus06.string.split.method1");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}

	
	public Object t(Object obj) throws Exception
	{
		String s = normalize((String)obj);
		String[] words = (String[]) cuttingMethod.t(s);
		return new F_oneOfThem(words);
	}
	
	
	
	private class F_oneOfThem implements F
	{
		private String[] words;
		public F_oneOfThem(String[] words)
		{this.words = words;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = normalize((String) toString.t(obj));
			for(int i=0;i<words.length;i++)
				if(str.contains(words[i])) return true;
			return false;
		}
	}
}
