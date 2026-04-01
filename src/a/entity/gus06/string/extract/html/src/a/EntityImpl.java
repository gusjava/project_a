package a.entity.gus06.string.extract.html.src.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170111";}

	private Pattern p1;
	private Pattern p2;

	public EntityImpl() throws Exception
	{
		p1 = Pattern.compile("src *= *\"([^\"]+)\"");
		p2 = Pattern.compile("src *= *\'([^\']+)\'");
	}



	public Object t(Object obj) throws Exception
	{
		String text = ((String) obj).toLowerCase();
		
		List list = new ArrayList();
		
		Matcher m1 = p1.matcher(text);
		while(m1.find()) list.add(m1.group(1));
		
		Matcher m2 = p2.matcher(text);
		while(m2.find()) list.add(m2.group(1));
		
		return list;
	}
}
