package a.entity.gus06.sys.parser3.analyzer1.op.num.check;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160812";}


	
	
	public boolean f(Object obj) throws Exception
	{return check(obj);}
	
	
	private boolean check(Object obj) throws Exception
	{
		if(obj instanceof Map) return checkMap((Map) obj);
		if(obj instanceof List) return checkList((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private boolean checkMap(Map map) throws Exception
	{
		String op = (String) map.get("operator");
		Object content = map.get("content");
		
		if(op.equals("!"))		return false;
		if(op.equals("!="))		return false;
		if(op.equals("=="))		return false;
		if(op.equals(">"))		return false;
		if(op.equals("<"))		return false;
		if(op.equals(">="))		return false;
		if(op.equals("<="))		return false;
		if(op.equals("string"))		return false;
		
		if(op.equals("int"))		return true;
		if(op.equals("double"))		return true;
		if(op.equals("element"))	return true;
		
		if(op.equals("-"))		return check(content);
		if(op.equals("+"))		return check(content);
		if(op.equals("*"))		return check(content);
		if(op.equals("group1"))		return check(content);
		
		throw new Exception("Operator not supported yet: "+op);
	}
	
	
	private boolean checkList(List list) throws Exception
	{
		for(Object o:list) if(!check(o)) return false;
		return true;
	}
}
