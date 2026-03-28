package a.entity.gus.y.stringcase1.splitcase;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240714";}
	
	
	public Object t(Object obj) throws Exception
	{
		return splitCase((String) obj);
	}
	
	private String[] splitCase(String s)
	{
		if(s==null) return new String[]{};
		if(isCamelCase(s) || isPascalCase(s)) return splitCamelCase(s);
		return s.split("[_\\. -]",-1);
	}
	
	private String[] splitCamelCase(String s)
	{
		List<String> l = new ArrayList<>();
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++)
		{
			char c = s.charAt(i);
			if(Character.isUpperCase(c) && sb.length()>0)
			{
				l.add(sb.toString());
				sb = new StringBuilder();
			}
			sb.append(c);
		}
		if(sb.length()>0) l.add(sb.toString());
		String[] array = new String[l.size()];
		return l.toArray(array);
	}
	
	
	private boolean isCamelCase(String s)
	{return s.matches("[a-z][a-zA-Z0-9]*");}
	
	
	private boolean isPascalCase(String s)
	{return s.matches("[A-Z][a-zA-Z0-9]*");}
}