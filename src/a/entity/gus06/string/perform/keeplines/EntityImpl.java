package a.entity.gus06.string.perform.keeplines;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190420";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) o[0];
		F f = (F) o[1];
		
		String[] lines = s.split("\n",-1);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			if(f.f(line)) b.append(line+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
