package a.entity.gus06.data.transform.string.sequence.parser;

import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150902";}

	public static final char CHAR_ECHAP = '\\';
	

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.length()!=1) throw new Exception("Invalid delim: "+s);
		return new T1(s.charAt(0));
	}
	
	
	
	private class T1 implements T
	{
		private char delim;
		public T1(char delim) {this.delim = delim;}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			ArrayList list = new ArrayList();
			if(!s.equals("")) parse(s,delim,list);
			return list;
		}
	}
	
	
	
	private void parse(String s, char delim, ArrayList list)
	{
		StringBuffer b = new StringBuffer();
		boolean meta = false;
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(meta)
			{
				if(c!=CHAR_ECHAP && c!=delim)
					b.append(CHAR_ECHAP);
				b.append(c);
				meta = false;
			}
			else
			{
				if(c==delim)
				{
					list.add(b.toString());
					b = new StringBuffer();
				}
				else if(c==CHAR_ECHAP) meta = true;
				else b.append(c);
			}
		}
		list.add(b.toString());
	}
}
