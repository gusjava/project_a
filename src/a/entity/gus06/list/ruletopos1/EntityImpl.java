package a.entity.gus06.list.ruletopos1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220522";}

	private Service findSize;
	private Service find;

	public EntityImpl() throws Exception
	{
		findSize = Outside.service(this,"gus.x.find.size");
		find = Outside.service(this,"gus06.list.ruletopos.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer size = findSize(o[0]);
		Object rule = o[1];
		
		if(size==0) return 0;
		int pos = findPos(size, rule);
		if(pos>size) return Integer.valueOf(size);
		return Integer.valueOf(pos);
	}
	
	private Integer findSize(Object obj) throws Exception
	{return (Integer) findSize.t(obj);}
	
	private int findPos(int size, Object rule) throws Exception
	{return (Integer) find.t(new Object[]{size, rule});}
}