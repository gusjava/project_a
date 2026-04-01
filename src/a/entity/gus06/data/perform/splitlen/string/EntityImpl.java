package a.entity.gus06.data.perform.splitlen.string;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180108";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		int len = toInt(o[1]);
		
		if(len>0) return split(input,len);
		if(len<0) return splitNeg(input,-len);
		throw new Exception("Invalid splitting length: 0");
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	
	private String[] split(String s, int len)
	{
		int length = s.length();
		int nb = length/len;
		if(length%len>0) nb++;
		
		String[] n = new String[nb];
		for(int i=0;i<nb;i++)
		{
			int start = i*len;
			int end = start+len;
			n[i] = s.substring(start,Math.min(end,length));
		}
		return n;
	}
	
	
	private String[] splitNeg(String s, int len)
	{
		int length = s.length();
		int nb = length/len;
		int offset = length%len;
		
		if(offset==0)
		{
			String[] n = new String[nb];
			for(int i=0;i<nb;i++)
			{
				int start = i*len;
				int end = start+len;
				n[i] = s.substring(start,Math.min(end,length));
			}
			return n;
		}
		
		String[] n = new String[nb+1];
		n[0] = s.substring(0,offset);
		
		for(int i=0;i<nb;i++)
		{
			int start = offset+i*len;
			int end = start+len;
			n[i+1] = s.substring(start,Math.min(end,length));
		}
		return n;
	}
}
