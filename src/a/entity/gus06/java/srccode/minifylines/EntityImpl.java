package a.entity.gus06.java.srccode.minifylines;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200421";}


	private Service split;
	
	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.lines1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		
		String[] lines = (String[]) split.t(s);
		List lines1 = new ArrayList();
		for(String line : lines)
		{
			String line_ = line.trim();
			if(!line_.equals(""))
			lines1.add(line_.replaceAll("\\s+"," "));
		}
		return lines1;
	}
}
