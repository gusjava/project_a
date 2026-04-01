package a.entity.gus06.string.extract.match.full.g2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220222";}




	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String text = (String) o[0];
		Pattern p = (Pattern) o[1];
		
		Matcher m = p.matcher(text);
		
		List list = new ArrayList();
		while(m.find())
		{
			Map map = new HashMap();
			map.put("start",m.start(2));
			map.put("end",m.end(2));
			map.put("group",m.group(2));
			
			list.add(map);
		}
		return list;
	}
}