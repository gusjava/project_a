package a.entity.gus06.filter.string.simple2.all;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160505";}

	
	
	public Object t(Object obj) throws Exception
	{return new Filter((String) obj);}
	
	
	private class Filter implements F
	{
		private String[] n;
		public Filter(String s)
		{
			if(s==null || s.equals("")) return;
			n = s.toLowerCase().split(" +");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(n==null) return true;
			
			String s = ((String) obj).toLowerCase();
			for(int i=0;i<n.length;i++)
				if(!s.contains(n[i])) return false;
			return true;
		}
	}
}
