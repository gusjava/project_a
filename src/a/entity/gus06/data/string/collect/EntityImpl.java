package a.entity.gus06.data.string.collect;

import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161215";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		StringBuffer input = (StringBuffer) o[0];
		T t = (T) o[1];
		
		StringBuffer b = new StringBuffer();
		int nb = input.length();
		
		for(int i=0;i<nb;i++)
		{
			char c = input.charAt(i);
			String s = (String) t.t(""+c);
			b.append(s);
		}
		
		input.delete(0,input.length());
		input.append(b);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		T t = (T) o[1];
		
		StringBuffer b = new StringBuffer();
		int nb = input.length();
		
		for(int i=0;i<nb;i++)
		{
			char c = input.charAt(i);
			String s = (String) t.t(""+c);
			b.append(s);
		}
		return b.toString();
	}
}
