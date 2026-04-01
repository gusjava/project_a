package a.entity.gus06.sys.textbuilder1.parser.matchtext;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String text = (String) o[0];
		String rule = (String) o[1];
		Map formats = (Map) o[2];
		
		Map map = new HashMap();
		
		String[] nn = rule.split("[\\[\\]]");
		for(int i=0;i<nn.length;i++)
		{
			String n = nn[i];
			if(i%2==0)
			{
				if(!text.startsWith(n)) return null;
				text = text.substring(n.length());
			}
			else
			{
				if(!formats.containsKey(n)) return null;
				String format = (String) formats.get(n);
				
				if(format.startsWith(">"))
				{
					format = format.substring(1);
					if(!formats.containsKey(format))
						throw new Exception("Format definition not found: "+format);
					format = (String) formats.get(format);
				}
				
				String data = extractData(text,format);
				if(data==null) return null;
				
				text = text.substring(data.length());
				map.put(n,data);
			}
		}
		
		if(!text.equals("")) return null;
		return map;
	}
	
	
	
	
	
	private String extractData(String text, String format) throws Exception
	{
		if(format.startsWith("$"))
		{
			format = format.substring(1);
			if(format.equals("int+")) return extractIntPos(text);
			throw new Exception("Unknown data format: "+format);
		}
		if(format.startsWith(":"))
		{
			String regex = format.substring(1);
			return extractRegex(text,regex);
		}
		
		String[] kk = format.split("\\|");
		for(String k:kk)
		if(text.startsWith(k)) return k;
		
		return null;
	}
	
	
	
	private String extractIntPos(String text)
	{
		StringBuffer in = new StringBuffer(text);
		StringBuffer b = new StringBuffer();
		
		while(in.length()>0 && isDigit(in.charAt(0)))
		{
			b.append(in.charAt(0));
			in.deleteCharAt(0);
		}
		return b.toString();
	}
	
	
	
	private String extractRegex(String text, String regex)
	{
		//TODO
		return null;
	}
	
	
	private boolean isDigit(char c)
	{
		int code = (int) c;
		return code>47 && code<58;
	}
}
