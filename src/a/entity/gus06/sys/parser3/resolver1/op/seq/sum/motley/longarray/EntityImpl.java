package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180112";}


	private Service toDoubleArray;
	private Service toLongArray;

	public EntityImpl() throws Exception
	{
		toDoubleArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray.todoublearray");
		toLongArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray.tolongarray");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isLongResult(oo)) return toLongArray.t(oo);
		return toDoubleArray.t(oo);
	}
	
	private boolean isLongResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Long || oo[i] instanceof long[])) return false;
		return true;
	}
}
