package a.entity.gus06.sys.xhtmlparser1.parser.split;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170226";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		List list = new ArrayList();
		
		boolean inString = false;
		boolean inComment = false;
		boolean inHeader = false;
		boolean inJavascript = false;
		boolean inTag = false;
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c=='"')
			{
				if(inTag) inString = !inString;
				b.append(c);
			}
			else if(c=='<')
			{
				if(inString)
				{
					b.append(c);
				}
				else if(inComment)
				{
					b.append(c);
				}
				else if(inHeader)
				{
					throw new Exception("Invalid caracter inside header: <");
				}
				else if(inJavascript)
				{
					b.append(c);
				}
				else if(isCommentStart(s,i))
				{
					b.append(c);
					inComment = true;
				}
				else if(isHeaderStart(s,i))
				{
					b.append(c);
					inHeader = true;
				}
				else if(isJavascriptStart(s,i))
				{
					b.append(c);
					inJavascript = true;
				}
				else
				{
					inTag = true;
					list.add(b.toString());
					list.add(""+c);
					b = new StringBuffer();
				}
			}
			else if(c=='>')
			{
				if(inString)
				{
					b.append(c);
				}
				else if(inComment)
				{
					b.append(c);
					if(isCommentEnd(s,i)) inComment = false;
				}
				else if(inHeader)
				{
					b.append(c);
					inHeader = false;
				}
				else if(inJavascript)
				{
					b.append(c);
					if(isJavascriptEnd(s,i)) inJavascript = false;
				}
				else
				{
					inTag = false;
					list.add(b.toString());
					list.add(""+c);
					b = new StringBuffer();
				}
			}
			else b.append(c);
		}
		
		list.add(b.toString());
		return list;
	}
	
	
	
	
	
	private boolean isCommentStart(String s, int i)
	{
		if(i>s.length()-4) return false;
		return is(s,i,'<') && is(s,i+1,'!') && is(s,i+2,'-') && is(s,i+3,'-');
	}
	
	private boolean isHeaderStart(String s, int i)
	{
		if(i>s.length()-2) return false;
		return is(s,i,'<') && is(s,i+1,'!');
	}
	
	private boolean isCommentEnd(String s, int i)
	{
		if(i<3) return false;
		return is(s,i,'>') && is(s,i-1,'-') && is(s,i-2,'-');
	}
	
	
	
	
	private boolean isJavascriptStart(String s, int i)
	{
		if(i>s.length()-8) return false;
		return is(s,i,'<') 
			&& is(s,i+1,'s') 
			&& is(s,i+2,'c') 
			&& is(s,i+3,'r') 
			&& is(s,i+4,'i') 
			&& is(s,i+5,'p') 
			&& is(s,i+6,'t') 
			&& isOf(s,i+7," >");
	}
	
	private boolean isJavascriptEnd(String s, int i)
	{
		if(i<9) return false;
		return is(s,i,'>') 
			&& is(s,i-1,'t') 
			&& is(s,i-2,'p') 
			&& is(s,i-3,'i') 
			&& is(s,i-4,'r') 
			&& is(s,i-5,'c') 
			&& is(s,i-6,'s') 
			&& is(s,i-7,'/') 
			&& is(s,i-8,'<');
	}
	
	
	
	
	
	private boolean is(String s, int i, char c)
	{return s.charAt(i)==c;}
	
	
	private boolean isOf(String s, int i, String c)
	{
		for(int k=0;k<c.length();k++)
		if(is(s,i,c.charAt(k))) return true;
		return false;
	}
}