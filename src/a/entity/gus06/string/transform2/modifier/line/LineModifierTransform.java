package a.entity.gus06.string.transform2.modifier.line;

import a.framework.*;


public class LineModifierTransform implements T {

	
	
	public final static String OCC_EXP = "$";
	public final static String INDEX_EXP = "<i>";
	
	
	
	private String rule;
	
	public LineModifierTransform(String rule)
	{this.rule = rule;}


	
	
	
	public Object t(Object obj) throws Exception
	{
		if(rule.equals("")) return obj;
		if(rule.contains("\n"))return obj;
		
		String[] lines = obj.toString().split("[\n\r]+",-1);
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
