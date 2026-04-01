package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.intmatrix;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170121";}


	
	private Service toDoubleMatrix;
	private Service toIntMatrix;

	public EntityImpl() throws Exception
	{
		toDoubleMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intmatrix.todoublematrix");
		toIntMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intmatrix.tointmatrix");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isIntResult(oo)) return toIntMatrix.t(oo);
		return toDoubleMatrix.t(oo);
	}
	
	private boolean isIntResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Integer 
			|| oo[i] instanceof int[]
			|| oo[i] instanceof int[][])) return false;
		return true;
	}
}
