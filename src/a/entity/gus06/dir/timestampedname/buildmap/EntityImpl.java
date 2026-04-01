package a.entity.gus06.dir.timestampedname.buildmap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170109";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File)
			return buildMap((File) obj);
		
		Object[] o = (Object[]) obj;
		if(o.length==2) 
		{
			File dir = (File) o[0];
			String value = (String) o[1];
			return buildMap(dir,value);
		}
		if(o.length==3) 
		{
			File dir = (File) o[0];
			String value1 = (String) o[1];
			String value2 = (String) o[2];
			return buildMap(dir,value1,value2);
		}
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	private Map buildMap(File dir)
	{
		String regex = "(\\d\\d\\d\\d\\d\\d\\d\\d_\\d\\d\\d\\d\\d\\d)_.+";
		return buildFromRegex(dir,regex);
	}
	
	private Map buildMap(File dir, String value)
	{
		String regex = "(\\d\\d\\d\\d\\d\\d\\d\\d_\\d\\d\\d\\d\\d\\d)_\\Q"+value+"\\E";
		return buildFromRegex(dir,regex);
	}
	
	private Map buildMap(File dir, String value1, String value2)
	{
		String regex = "\\Q"+value1+"\\E_(\\d\\d\\d\\d\\d\\d\\d\\d_\\d\\d\\d\\d\\d\\d)_\\Q"+value2+"\\E";
		return buildFromRegex(dir,regex);
	}
	
	
	private Map buildFromRegex(File dir, String regex)
	{
		Pattern p = Pattern.compile(regex);
		File[] ff = dir.listFiles();
		Map map = new HashMap();
		
		if(ff!=null) for(File f:ff)
		{
			String name = f.getName();
			Matcher m = p.matcher(name);
			if(m.find())
			{
				String timeStamp = m.group(1);
				map.put(timeStamp,f);
			}
		}
		return map;
	}
}
