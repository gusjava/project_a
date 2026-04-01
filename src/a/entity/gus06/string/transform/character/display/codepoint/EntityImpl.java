package a.entity.gus06.string.transform.character.display.codepoint;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			b.append(toString(c)+"\t"+codePoint(c)+"\n");
		}
		return b.toString();
	}
	
	private String toString(char c)
	{
		if(c=='\n')return "\\n";
		if(c=='\r')return "\\r";
		if(c=='\t')return "\\t";
		return ""+c;
	}
	
	private String codePoint(char c)
	{
		int codePoint = Character.codePointAt(new char[]{c},0);
		return codePoint+"\t\\u"+hexa(codePoint);
	}
	
	private String hexa(int n)
	{
		String v = ""+Integer.toHexString(n);
		while(v.length()<4) v="0"+v;
		return v;
	}
}
