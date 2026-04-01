package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}


	private Service findE;
	private Service sumE;
	
	public EntityImpl() throws Exception
	{
		findE = Outside.service(this,"gus06.find.execute");
		sumE = Outside.service(this,"gus06.feature.op.sum.e");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		E[] ee = new E[oo.length];
		for(int i=0;i<oo.length;i++) ee[i] = toE(oo[i]);
		return sumE.t(ee);
	}
	
	private E toE(Object o) throws Exception
	{
		if(o==null) throw new Exception("Invalid null value");
		return (E) findE.t(o);
	}
}
