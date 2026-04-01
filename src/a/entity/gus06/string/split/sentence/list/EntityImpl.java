package a.entity.gus06.string.split.sentence.list;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250507";}
	
	private Pattern pattern = Pattern.compile(".*?[.!?](?=\\s+[A-Z������])", Pattern.DOTALL);
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Matcher matcher = pattern.matcher(s);
		
		List list = new ArrayList();
		while (matcher.find())
		{
			String sentence = matcher.group();
			list.add(sentence.trim());
		}
		return list;
	}
}