package a.entity.gus06.math.matrixdim.build.square;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}


	private Service toDim;
	
	public EntityImpl() throws Exception
	{
		toDim = Outside.service(this,"gus06.math.matrixdim.build");
	}

	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) toDim.t(obj);
		if(n[0]!=n[1]) throw new Exception("Invalid square dim value: ["+n[0]+"-"+n[1]+"]");
		return n;
	}
}
