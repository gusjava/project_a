package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.floatarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service toDoubleArray;
	private Service toFloatArray;

	public EntityImpl() throws Exception
	{
		toDoubleArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatarray.todoublearray");
		toFloatArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatarray.tofloatarray");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isFloatResult(oo)) return toFloatArray.t(oo);
		return toDoubleArray.t(oo);
	}
	
	private boolean isFloatResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Float || oo[i] instanceof float[])) return false;
		return true;
	}
}
