package a.entity.gus06.sys.parser1.process.split;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150828";}

	
	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	
	private class T1 implements T
	{
		private String info;
		private char meta;
		
		public T1(String info) throws Exception
		{
			this.info = info;
			if(info.length()<2) throw new Exception("Invalid info: "+info);
			meta = info.charAt(0);
		}
		
		private boolean isDelim(char c)
		{return info.indexOf(c)>0;}
		
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			
			List list = new ArrayList();
			boolean isEscape = false;
			
			StringBuffer b = new StringBuffer();
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(isDelim(c))
				{
					if(isEscape)
					{
						b.append(c);
						isEscape = false;
					}
					else
					{
						list.add(b.toString());
						list.add(""+c);
						b = new StringBuffer();
					}
				}
				else if(c==meta)
				{
					if(isEscape)
					{
						b.append(c);
						isEscape = false;
					}
					else
					{
						isEscape = true;
					}
				}
				else
				{
					if(isEscape)
					{
						//LENIENT WAY
						//b.append(meta);
						//b.append(c);
						//isEscape = false;
						
						//STRICT WAY
						throw new Exception("Invalid syntax (escap character found alone)");
					}
					else
					{
						b.append(c);
					}
				}
				
			}
			
			list.add(b.toString());
			return list;
		}
	}
}
