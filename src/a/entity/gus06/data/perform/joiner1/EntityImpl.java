package a.entity.gus06.data.perform.joiner1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160921";}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length<1 && o.length>3) throw new Exception("Wrong data number: "+o.length);
		
		String glue = o[0];
		String prefix = o.length>1?o[1]:null;
		String suffix = o.length>2?o[2]:null;
		
		return new Joiner(glue,prefix,suffix);
	}
	
	
	
	private class Joiner implements G, P
	{
		private String glue;
		private String prefix;
		private String suffix;
		
		private List list;
		
		public Joiner(String glue, String prefix, String suffix)
		{
			this.glue = glue;
			this.prefix = prefix;
			this.suffix = suffix;
			
			list = new ArrayList();
		}
		
		public void p(Object obj) throws Exception
		{
			add(obj);
		}
		
		
		private void add(Object obj)
		{
			if(obj==null) return;
			
			if(obj instanceof String && !obj.equals(""))
			{list.add(obj);return;}
			
			if(obj instanceof Number)
			{list.add(""+obj);return;}
			
			if(obj instanceof Boolean)
			{list.add(""+obj);return;}
			
			if(obj instanceof List)
			{
				List l = (List) obj;
				for(Object o:l) add(o);
				return;
			}
			
			if(obj instanceof Object[])
			{
				Object[] l = (Object[]) obj;
				for(Object o:l) add(o);
				return;
			}
		}
		
		public Object g() throws Exception
		{
			if(list.isEmpty()) return "";
			
			StringBuffer b = new StringBuffer();
			if(prefix!=null) b.append(prefix);
			
			int nb = list.size();
			for(int i=0;i<nb;i++)
			{
				String element = (String) list.get(i);
				b.append(element);
				if(i<nb-1) b.append(glue);
			}
			
			if(suffix!=null) b.append(suffix);
			return b.toString();
		}
	}
}
