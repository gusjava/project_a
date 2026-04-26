package a.entity.gus06.list.ruletoindex1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160714";}


	private Service findSize;
	private Service find;

	public EntityImpl() throws Exception
	{
		findSize = Outside.service(this,"gus.x.find.size");
		find = Outside.service(this,"gus06.list.ruletoindex.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer size = findSize(o[0]);
		Object rule = o[1];
		
		if(size==0) return null;
		int index = findIndex(size, rule);
		if(index>=size) return Integer.valueOf(size-1);
		return Integer.valueOf(index);
	}
	
	private Integer findSize(Object obj) throws Exception
	{return (Integer) findSize.t(obj);}
	
	private int findIndex(int size, Object rule) throws Exception
	{return (Integer) find.t(new Object[]{size, rule});}
}