package a.entity.gus06.data.string.collectline;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170202";}
	
	public static final String DELIM = "\n";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		StringBuffer input = (StringBuffer) o[0];
		T t = (T) o[1];
		
		String[] n = input.toString().split(DELIM,-1);
		int nb = n.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String s = (String) t.t(n[i]);
			b.append(s+DELIM);
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		
		input.delete(0,input.length());
		input.append(b);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		T t = (T) o[1];
		
		String[] n = input.split(DELIM,-1);
		int nb = n.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String s = (String) t.t(n[i]);
			b.append(s+DELIM);
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
