package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	private Service findH;
	private Service sumH;
	
	public EntityImpl() throws Exception
	{
		findH = Outside.service(this,"gus06.find.h");
		sumH = Outside.service(this,"gus06.feature.op.sum.h");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		H[] hh = new H[oo.length];
		for(int i=0;i<oo.length;i++) hh[i] = toH(oo[i]);
		return sumH.t(hh);
	}
	
	private H toH(Object o) throws Exception
	{
		if(o==null) throw new Exception("Invalid null value");
		return (H) findH.t(o);
	}
}
