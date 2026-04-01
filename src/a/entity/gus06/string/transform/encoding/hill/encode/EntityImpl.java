package a.entity.gus06.string.transform.encoding.hill.encode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}

	private final static int modulo = 10000;

	private final static int a = 27;
	private final static int b = 10;
	private final static int c = 197;
	private final static int d = 73;
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		StringBuffer out = new StringBuffer();
		StringBuffer in = new StringBuffer(s);
		encode(in,out);
		
		return out.toString();
	}
	
	
	
	private void encode(StringBuffer in, StringBuffer out)
	{
		while(in.length()>1)
		{
			char c1 = in.charAt(0);
			char c2 = in.charAt(1);
			in.delete(0,2);
			
			int x = a*(int)c1+b*(int)c2;
			int y = c*(int)c1+d*(int)c2;
			
			x = x%modulo;
			y = y%modulo;
			
			out.append((char)x);
			out.append((char)y);
		}
		if(in.length()==1)
		{
			char c = in.charAt(0);
			int x = (int)c+1000;
			out.append((char)x);
		}
	}
}
