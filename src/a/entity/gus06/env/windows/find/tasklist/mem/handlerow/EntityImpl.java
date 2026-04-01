package a.entity.gus06.env.windows.find.tasklist.mem.handlerow;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190607";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] infos = (String[]) obj;
		if(infos.length!=2) throw new Exception("Wrong data number: "+infos.length);
		
		String delim = infos[0];
		String line = infos[1];
		
		try
		{
			String[] delimArray = delim.split(" ");
			String[] row = buildRow(line,delimArray);
			return extractLong(row[4]);
		}
		catch(Exception e)
		{
			String message = "Failed to parse line: ["+line+"] with delim: ["+delim+"]";
			throw new Exception(message,e);
		}
	}
	
	
	private String[] buildRow(String line, String[] delim) throws Exception
	{
		int length = line.length();
		String[] row = new String[delim.length];
		int offset = 0;
		for(int i=0;i<delim.length;i++)
		{
			int len = delim[i].length();
			int end = offset+len;
			if(end>length) throw new Exception("Invalid offset: "+offset);
			row[i] = line.substring(offset, end);
			offset += len+1;
		}
		return row;
	}
	
	
	private Long extractLong(String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(isDigit(c)) b.append(c);
		}
		return Long.valueOf(b.toString());
	}
	
	private boolean isDigit(char c)
	{
		int code = (int) c;
		return code>47 && code<58;
	}
}