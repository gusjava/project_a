package a.entity.gus06.java.srccode.extract.entity.calls.service;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140827";}

	private Pattern p = Pattern.compile("Outside\\.service\\(this,\"([^\"]+)\"");
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.java.srccode.toarray");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		List list = new ArrayList();
		
		String[] lines = (String[]) toArray.t(obj);
		for(String line:lines)
		{
			Matcher m = p.matcher(line);
			if(m.find()) list.add(m.group(1));
		}
		return list;
	}
}
