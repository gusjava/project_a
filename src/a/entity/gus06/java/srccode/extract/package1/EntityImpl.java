package a.entity.gus06.java.srccode.extract.package1;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140725";}

	private Pattern p = Pattern.compile("package +([^;]+);");
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.java.srccode.toarray");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		for(String line:lines)
		{
			if(line.trim().startsWith("package "))
			return extract(line);
		}
		return null;
	}
	
	
	
	private String extract(String line) throws Exception
	{
		Matcher m = p.matcher(line);
		if(!m.find()) throw new Exception("Package extraction failed for line: "+line);
		return m.group(1);
	}
}
