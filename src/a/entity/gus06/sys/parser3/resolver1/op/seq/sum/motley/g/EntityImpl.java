package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170215";}


	private Service wrapG;
	private Service sumG;
	
	public EntityImpl() throws Exception
	{
		wrapG = Outside.service(this,"gus06.feature.wrap.o.g");
		sumG = Outside.service(this,"gus06.feature.op.sum.g");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] oo = (Object[]) o[0];
		T sumT = (T) o[1];
		
		G[] gg = new G[oo.length];
		for(int i=0;i<oo.length;i++) gg[i] = toG(oo[i]);
		return sumG.t(new Object[]{gg,sumT});
	}
	
	private G toG(Object o) throws Exception
	{
		if(o==null) throw new Exception("Invalid null value");
		if(o instanceof G) return (G) o;
		return (G) wrapG.t(o);
	}
}
