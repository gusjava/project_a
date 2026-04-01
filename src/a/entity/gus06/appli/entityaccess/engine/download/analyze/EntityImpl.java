package a.entity.gus06.appli.entityaccess.engine.download.analyze;

import a.framework.*;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150302";}


	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_FILENUMBER = "filenumber";
	public static final String KEY_FILENAME = "filename";
	public static final String KEY_FILECONTENT = "filecontent";
	
	public static final Pattern P_CLASS = Pattern.compile("class ([^ ]+)");
	public static final Pattern P_INTERFACE = Pattern.compile("interface ([^ ]+)");
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		
		Map map = new HashMap();
		String[] lines = src.split("\n",-1);
		
		String packageName = null;
		
		int index = -1;
		String cName = null;
		StringBuffer cContent = null;
		
		for(String line:lines)
		{
			if(line.startsWith("package "))
			{
				if(packageName==null) packageName = line.trim();
				else if(!packageName.equals(line.trim())) throw new Exception("Invalid entity src: different package names found");
				
				if(cContent!=null)
				{
					if(cName==null) throw new Exception("Invalid entity src: Class name not found (1)");
					map.put(KEY_FILENAME+":"+index,cName);
					map.put(KEY_FILECONTENT+":"+index,cContent.toString().trim());
				}
				
				cName = null;
				cContent = new StringBuffer();
				cContent.append(line+"\n");
				
				index++;
				continue;
			}
			
			if(cContent==null) throw new Exception("Invalid entity src: lines found before first package declaration");
			cContent.append(line+"\n");
			
			
			
			if(line.startsWith("class ") && cName==null)
			{
				Matcher matcher = P_CLASS.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
			
			if(line.startsWith("abstract class ") && cName==null)
			{
				Matcher matcher = P_CLASS.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
			
			if(line.startsWith("public class ") && cName==null)
			{
				Matcher matcher = P_CLASS.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
			
			if(line.startsWith("public abstract class ") && cName==null)
			{
				Matcher matcher = P_CLASS.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
			
			
			
			
			if(line.startsWith("interface ") && cName==null)
			{
				Matcher matcher = P_INTERFACE.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
			
			if(line.startsWith("public interface ") && cName==null)
			{
				Matcher matcher = P_INTERFACE.matcher(line);
				if(matcher.find()) cName = matcher.group(1)+".java";
				continue;
			}
		}
		
		
		if(cContent==null) throw new Exception("Invalid entity src: Package declaration not found");
		if(cName==null) throw new Exception("Invalid entity src: Class name not found (2)");
		
		map.put(KEY_FILENAME+":"+index,cName);
		map.put(KEY_FILECONTENT+":"+index,cContent.toString().trim());
		
		
		String entityName = packageName.substring(21,packageName.length()-1);
		
		map.put(KEY_ENTITYNAME,entityName);
		map.put(KEY_FILENUMBER,""+(index+1));
		
		return map;
	}
}
