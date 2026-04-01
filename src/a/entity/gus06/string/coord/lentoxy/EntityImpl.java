package a.entity.gus06.string.coord.lentoxy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String data = (String) o[0];
		int len = toInt(o[1]);
		
		if(data.length()<len) return null;
		
		String[] lines = data.split("\n");
		for(int i=0;i<lines.length;i++)
		{
			int len1 = lines[i].length();
			if(len<len1) return new int[]{i,len};
			len -= len1;
		}
		return null;
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}