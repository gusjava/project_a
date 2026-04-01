package a.entity.gus06.string.transform.character.display.intvalue;

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
			b.append(toString(c)+"\t"+intValue(c)+"\n");
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
	
	private int intValue(char c)
	{return (int) c;}
}
