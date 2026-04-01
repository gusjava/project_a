package a.entity.gus06.sys.parser3.analyzer1.op.bool.invert;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160811";}


	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String op = (String) map.get("operator");
		Object content = map.get("content");
		
		if(op.equals("!"))	return content;
		if(op.equals("!="))	return build("==",content);
		if(op.equals("=="))	return build("!=",content);
		if(op.equals(">"))	return build("<=",content);
		if(op.equals("<"))	return build(">=",content);
		if(op.equals(">="))	return build("<",content);
		if(op.equals("<="))	return build(">",content);
		
		if(op.equals("group1"))	return t(content);
		
		return null;
	}
	
	
	private Map build(String operator, Object content)
	{
		Map map = new HashMap();
		map.put("operator",operator);
		map.put("content",content);
		return map;
	}
}
