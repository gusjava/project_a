package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.longmatrix;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180112";}


	
	private Service toDoubleMatrix;
	private Service toLongMatrix;

	public EntityImpl() throws Exception
	{
		toDoubleMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longmatrix.todoublematrix");
		toLongMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longmatrix.tolongmatrix");
		
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		if(isLongResult(oo)) return toLongMatrix.t(oo);
		return toDoubleMatrix.t(oo);
	}
	
	private boolean isLongResult(Object[] oo)
	{
		for(int i=1;i<oo.length;i++)
			if(!(oo[i] instanceof Long 
			|| oo[i] instanceof long[]
			|| oo[i] instanceof long[][])) return false;
		return true;
	}
}
