package a.entity.gus06.filter.string.build.oneofthem.lines1;

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
		String text = (String) obj;
		String[] lines = text.toLowerCase().split("\n");
		return new Filter(lines);
	}
	
	
	private class Filter implements F
	{
		private String[] lines;
		public Filter(String[] lines)
		{this.lines = lines;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = ((String) toString.t(obj)).toLowerCase();
			for(int i=0;i<lines.length;i++)
			if(match(lines[i],str)) return true;
			return false;
		}
	}
	
	
	
	
	private boolean match(String line, String s)
	{
		if(line.startsWith("*") && line.endsWith("*"))
			return s.contains(line.replace("*",""));
		
		if(line.endsWith("*"))
			return s.startsWith(line.replace("*",""));
		
		if(line.startsWith("*"))
			return s.endsWith(line.replace("*",""));
		
		return s.equals(line);
	}
}
