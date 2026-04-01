package a.entity.gus06.string.extract.html.tag.a.classes;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}


	private Service extractTags;
	
	private Pattern p1;
	private Pattern p2;


	public EntityImpl() throws Exception
	{
		extractTags = Outside.service(this,"gus06.string.extract.html.tag.a");
		
		p1 = Pattern.compile("(?si)class *= *\"([^\"]+)\"");
		p2 = Pattern.compile("(?si)class *= *\'([^\']+)\'");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) extractTags.t(obj);
		List output = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			String tag = (String) list.get(i);
			
			Matcher m1 = p1.matcher(tag);
			while(m1.find()) output.addAll(toList(m1.group(1)));
			
			Matcher m2 = p2.matcher(tag);
			while(m2.find()) output.addAll(toList(m2.group(1)));
		}
		
		return output;
	}
	
	
	private List toList(String s)
	{
		String[] nn = s.trim().split(" +");
		return Arrays.asList(nn);
	}
}
