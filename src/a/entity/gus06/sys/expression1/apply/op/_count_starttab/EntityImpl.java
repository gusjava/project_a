package a.entity.gus06.sys.expression1.apply.op._count_starttab;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160822";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return number((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Integer number(String s)
	{
		int count = 0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='\t') count++;
			else break;
		}
		return Integer.valueOf(count);
	}
}
