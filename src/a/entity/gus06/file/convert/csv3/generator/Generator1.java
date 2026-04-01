package a.entity.gus06.file.convert.csv3.generator;

public class Generator1 {

	
	private char delim;
	
	public Generator1(char delim)
	{
		this.delim = delim;
	}
	
	
	public String generate(String[][] table)
	{
		StringBuffer b = new StringBuffer();
		
		int x = table.length;
		if(x==0)return "";
		int y = table[0].length;
		if(y==0)return "";
		
		  
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		{
			String value = formatValue(table[i][j]);
			if(j==y-1)b.append(value+"\n");
			else b.append(value+delim);
		}
		return b.toString();
	}
	

	
	
	private String formatValue(String value)
	{
		if(value.contains("\"") || value.contains("\n") || value.contains(""+delim))
		{
			value = value.replace("\"","\"\"");
			return "\""+value+"\"";
		}
		return value;
	}
}
