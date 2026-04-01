package a.entity.gus06.sys.expression1.apply.op._todouble_or_null;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}


	private Service find;
	
	public EntityImpl() throws Exception
	{find = Outside.service(this,"gus06.find.double1");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj.equals("")) return null;
		
		try{return find.t(obj);}
		catch(Exception e){}
		return null;
	}
}