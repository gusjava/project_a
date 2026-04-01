package a.entity.gus06.string.extract.match.all;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170328";}




	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String text = (String) o[0];
		Pattern p = (Pattern) o[1];
		
		Matcher m = p.matcher(text);
		
		List list = new ArrayList();
		while(m.find()) list.add(m.group());
		return list;
	}
}
