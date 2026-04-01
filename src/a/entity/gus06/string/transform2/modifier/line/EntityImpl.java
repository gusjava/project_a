package a.entity.gus06.string.transform2.modifier.line;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20151103";}
	
	public final static String OCC_EXP = "$";
	public final static String INDEX_EXP = "<i>";


	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	public class T1 implements T
	{
		private String rule;
		public T1(String rule) {this.rule = rule;}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			
			if(rule.equals("")) return obj;
			if(rule.contains("\n"))return obj;
			
			String[] lines = s.split("\n",-1);
			StringBuffer b = new StringBuffer();
			for(int i=0;i<lines.length;i++)
			{
				String line = transformLine(lines[i],i+1);
				b.append(line+"\n");
			}
			if(b.length()>0)
			b.deleteCharAt(b.length()-1);
			return b.toString();
		}
		
		private String transformLine(String in, int index)
		{
			String s = rule;
			String[] cells = splitLine(in);
			int n = cells.length;
			
			if(n>1)
			{
				for(int i=0;i<n;i++) s = s.replace(OCC_EXP+i,cells[i]);
				for(int i=0;i<n;i++) s = s.replace(OCC_EXP+"-"+(i+1),cells[n-i-1]);
			}
			
			return s.replace(OCC_EXP,in).replace(INDEX_EXP,""+index);
		}
		
		private String[] splitLine(String line)
		{
			if(line.contains("\t")) return line.split("\t",-1);
			if(line.contains(" ")) return line.split(" ",-1);
			if(line.contains(".")) return line.split("\\.",-1);
			return line.split(".",-1);
		}
	}
}
