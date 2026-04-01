package a.entity.gus06.sys.expression1.apply.op._tolongarray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service find;
	
	public EntityImpl() throws Exception
	{find = Outside.service(this,"gus06.find.longarray2");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		return find.t(obj);
	}
}
