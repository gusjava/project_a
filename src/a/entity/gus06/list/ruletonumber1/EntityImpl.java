package a.entity.gus06.list.ruletonumber1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220522";}
	

	private Service findSize;
	private Service find;

	public EntityImpl() throws Exception
	{
		findSize = Outside.service(this,"gus06.find.size");
		find = Outside.service(this,"gus06.list.ruletonumber.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer size = findSize(o[0]);
		Object rule = o[1];
		
		if(size==0) return null;
		int number = findNumber(size, rule);
		if(number>size) return Integer.valueOf(size);
		return Integer.valueOf(number);
	}
	
	private Integer findSize(Object obj) throws Exception
	{return (Integer) findSize.t(obj);}
	
	private int findNumber(int size, Object rule) throws Exception
	{return (Integer) find.t(new Object[]{size, rule});}
}