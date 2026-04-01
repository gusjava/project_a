package a.entity.gus06.string.extract.date.fr.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170211";}
	

	private Service regex;

	public EntityImpl() throws Exception
	{
		regex = Outside.service(this,"gus06.string.extract.date.fr.regex");
	}

	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		
		Pattern p = (Pattern) regex.g();
		Matcher m = p.matcher(text.toLowerCase());
		
		List list = new ArrayList();
		while(m.find())	list.add(m.group());
		return list;
	}
}