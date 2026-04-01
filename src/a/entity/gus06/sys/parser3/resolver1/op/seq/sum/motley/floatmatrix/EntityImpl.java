package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.floatmatrix;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	
	private Service toDoubleMatrix;
	private Service toFloatMatrix;

	public EntityImpl() throws Exception
	{
		toDoubleMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatmatrix.todoublematrix");
		toFloatMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatmatrix.tofloatmatrix");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isFloatResult(oo)) return toFloatMatrix.t(oo);
		return toDoubleMatrix.t(oo);
	}
	
	private boolean isFloatResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Float 
			|| oo[i] instanceof float[]
			|| oo[i] instanceof float[][])) return false;
		return true;
	}
}
