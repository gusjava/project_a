package a.entity.gus06.sys.parser2.engine1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150902";}

	
	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	private class T1 implements T
	{
		private char open;
		private char close;
		
		public T1(String info) throws Exception
		{
			if(info.length()!=2) throw new Exception("Invalid info: "+info);
			open = info.charAt(0);
			close = info.charAt(1);
		}
		
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			
			List list = new ArrayList();
			StringBuffer b = new StringBuffer();
			int level = 0;
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c==open)
				{
					if(level==0)
					{
						if(b.length()>0) list.add(b.toString());
						b = new StringBuffer();
					}
					
					level++;
					b.append(c);
				}
				else if(c==close)
				{
					level--;
					b.append(c);
					
					if(level==0)
					{
						if(b.length()>0) list.add(b.toString());
						b = new StringBuffer();
					}
				}
				else b.append(c);
			}
			
			if(level>0) throw new Exception("Invalid syntax");
			
			if(b.length()>0) list.add(b.toString());
			return list;
		}
	}
}
