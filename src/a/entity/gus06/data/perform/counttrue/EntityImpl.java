package a.entity.gus06.data.perform.counttrue;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180111";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)		return Integer.valueOf(count((List) obj));
		if(obj instanceof Set)		return Integer.valueOf(count((Set) obj));
		
		if(obj instanceof Object[][])	return Integer.valueOf(count((Object[][]) obj));
		if(obj instanceof boolean[][])	return Integer.valueOf(count((boolean[][]) obj));
		
		if(obj instanceof Object[])	return Integer.valueOf(count((Object[]) obj));
		if(obj instanceof boolean[])	return Integer.valueOf(count((boolean[]) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int count(boolean[] bb)
	{
		int count = 0;
		for(boolean b : bb) if(b) count++;
		return count;
	}
	
	private int count(boolean[][] bbb)
	{
		int count = 0;
		for(boolean[] bb : bbb)
		for(boolean b : bb)
		if(b) count++;
		return count;
	}
	
	private int count(Object[] bb)
	{
		int count = 0;
		for(Object b : bb) if(isTrue(b)) count++;
		return count;
	}
	
	private int count(Object[][] bbb)
	{
		int count = 0;
		for(Object[] bb : bbb)
		for(Object b : bb)
		if(isTrue(b)) count++;
		return count;
	}
	
	private int count(List l)
	{
		int count = 0;
		for(Object b : l) if(isTrue(b)) count++;
		return count;
	}
	
	private int count(Set l)
	{
		int count = 0;
		for(Object b : l) if(isTrue(b)) count++;
		return count;
	}
	
	
	private boolean isTrue(Object obj)
	{
		if(obj==null) return false;
		if(!(obj instanceof Boolean)) return false;
		return ((Boolean) obj).booleanValue();
	}
}
