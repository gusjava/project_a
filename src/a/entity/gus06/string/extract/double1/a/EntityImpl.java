package a.entity.gus06.string.extract.double1.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161121";}
	
	public static final String REGEX = "\\d+(\\.\\d+)?";


	private Pattern p;

	public EntityImpl() throws Exception
	{
		p = Pattern.compile(REGEX);
	}



	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Matcher m = p.matcher(text.toLowerCase());
		
		List list = new ArrayList();
		while(m.find()) list.add(Double.valueOf(m.group()));
		return list;
	}
}
