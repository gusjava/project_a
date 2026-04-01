package a.entity.gus06.sys.expression1.apply.op._chk_one;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Set) return handleSet((Set) obj);
		if(obj instanceof Map) return handleMap((Map) obj);
		if(obj instanceof Number) return handleNumber((Number) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private List handleList(List list) throws Exception
	{
		if(list.size()!=1) throw new Exception("Invalid list size: "+list.size()+" (1 expected)");
		return list;
	}
	
	private Set handleSet(Set set) throws Exception
	{
		if(set.size()!=1) throw new Exception("Invalid set size: "+set.size()+" (1 expected)");
		return set;
	}
	
	private Map handleMap(Map map) throws Exception
	{
		if(map.size()!=1) throw new Exception("Invalid map size: "+map.size()+" (1 expected)");
		return map;
	}
	
	private Number handleNumber(Number number) throws Exception
	{
		if(number.doubleValue()!=1.0) throw new Exception("Invalid numer: "+number+" (1 expected)");
		return number;
	}
	
}
