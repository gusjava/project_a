package a.entity.gus06.string.transform2.replace.regexp;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20151102";}
	
	public final static String OCC_EXP = "$";
	public final static String INDEX_EXP = "<i>";


	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	public class T1 implements T
	{
		private String regexp;
		private String rep;
	
		public T1(String info)
		{
			String[] n = info.split("\n",-1);
			regexp = n[0];
			rep = n.length>1?n[1]:"";
		}
	
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			if(rep.contains(OCC_EXP) || rep.contains(INDEX_EXP))
				return replaceBackRef(s);
			return s.replaceAll(regexp,rep);
		}
	
		private String replaceBackRef(String in)
		{
			Pattern p = Pattern.compile(regexp);
			Matcher m = p.matcher(in);
		
			StringBuffer b = new StringBuffer();
			int end0 = 0;
		
			int index = 0;
			while(m.find())
			{
				index++;
				String occ = m.group();
				String rep1 = rep.replace(OCC_EXP,occ).replace(INDEX_EXP,""+index);
			
				b.append(in.substring(end0,m.start()));
				b.append(rep1);
				end0 = m.end();
			}
			b.append(in.substring(end0,in.length()));
			return b.toString();
		}
	}
}
