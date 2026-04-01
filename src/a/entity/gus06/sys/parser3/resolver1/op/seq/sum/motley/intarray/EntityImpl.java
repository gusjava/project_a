package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.intarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170106";}


	private Service toDoubleArray;
	private Service toIntArray;

	public EntityImpl() throws Exception
	{
		toDoubleArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intarray.todoublearray");
		toIntArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intarray.tointarray");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isIntResult(oo)) return toIntArray.t(oo);
		return toDoubleArray.t(oo);
	}
	
	private boolean isIntResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Integer || oo[i] instanceof int[])) return false;
		return true;
	}
}
