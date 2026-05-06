package a.entity.gus06.java.srccode.extract.imports1;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140915";}

	private Pattern p = Pattern.compile("import +([^;]+);");
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.java.srccode.toarray");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		
		Set set = new HashSet();
		for(String line:lines) if(line.trim().startsWith("import "))
		{
			String value = extract(line).trim();
			if(value.startsWith("static ")) value = value.substring(7);
			set.add(value.trim());
		}
		return set;
	}
	
	
	
	private String extract(String line) throws Exception
	{
		Matcher m = p.matcher(line);
		if(!m.find()) throw new Exception("Import extraction failed for line: "+line);
		return m.group(1);
	}
}
