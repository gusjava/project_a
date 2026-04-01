package a.entity.gus06.env.windows.find.tasklist.handlerow;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190522";}
	
	public static final String KEY_IMAGE_NAME = "image_name";
	public static final String KEY_PID = "pid";
	public static final String KEY_SESSION_NAME = "session_name";
	public static final String KEY_SESSION_NUMBER = "session_number";
	public static final String KEY_MEM_USAGE = "mem_usage";
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] infos = (String[]) obj;
		if(infos.length!=2) throw new Exception("Wrong data number: "+infos.length);
		
		String[] delim = infos[0].split(" ");
		String line = infos[1];
		
		String[] row = buildRow(line,delim);
			
		Map map = new HashMap();
		map.put(KEY_IMAGE_NAME,		row[0].trim());
		map.put(KEY_PID,		extractInt(row[1]));
		map.put(KEY_SESSION_NAME,	row[2].trim());
		map.put(KEY_SESSION_NUMBER,	extractInt(row[3]));
		map.put(KEY_MEM_USAGE,		extractLong(row[4]));
		return map;
	}
	
	
	private String[] buildRow(String line, String[] delim)
	{
		String[] row = new String[delim.length];
		int offset = 0;
		for(int i=0;i<delim.length;i++)
		{
			int len = delim[i].length();
			row[i] = line.substring(offset,offset+len);
			offset += len+1;
		}
		return row;
	}
	
	
	
	private Integer extractInt(String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(isDigit(c)) b.append(c);
		}
		return Integer.valueOf(b.toString());
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
