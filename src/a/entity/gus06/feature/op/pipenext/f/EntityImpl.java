package a.entity.gus06.feature.op.pipenext.f;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service fc;
	private Service fcc;
	
	private Service fe;
	private Service fee;
	
	private Service fg;
	private Service fgg;
	
	private Service fh;
	private Service fhh;
	
	private Service fi;
	private Service fii;
	
	private Service fmap;
	private Service fmapmap;
	
	private Service fp;
	private Service fpp;
	
	private Service fr;
	private Service frr;
	
	private Service ft;
	private Service ftt;
	
	private Service foo;
	
	
	public EntityImpl() throws Exception
	{
		fc = Outside.service(this,"gus06.feature.wrap.fc.p");
		fcc = Outside.service(this,"gus06.feature.wrap.fcc.p");
		
		fe = Outside.service(this,"gus06.feature.wrap.fe.f");
		fee = Outside.service(this,"gus06.feature.wrap.fee.p");
		
		fg = Outside.service(this,"gus06.feature.wrap.fg.t");
		fgg = Outside.service(this,"gus06.feature.wrap.fgg.t");
		
		fh = Outside.service(this,"gus06.feature.wrap.fh.h");
		fhh = Outside.service(this,"gus06.feature.wrap.fhh.h");
		
		fi = Outside.service(this,"gus06.feature.wrap.fi.t");
		fii = Outside.service(this,"gus06.feature.wrap.fii.t");
		
		fmap = Outside.service(this,"gus06.feature.wrap.fmap.t");
		fmapmap = Outside.service(this,"gus06.feature.wrap.fmapmap.t");
		
		fp = Outside.service(this,"gus06.feature.wrap.fp.p");
		fpp = Outside.service(this,"gus06.feature.wrap.fpp.p");
		
		fr = Outside.service(this,"gus06.feature.wrap.fr.r");
		frr = Outside.service(this,"gus06.feature.wrap.frr.r");
		
		ft = Outside.service(this,"gus06.feature.wrap.ft.t");
		ftt = Outside.service(this,"gus06.feature.wrap.ftt.t");
		
		foo = Outside.service(this,"gus06.feature.wrap.foo.t");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		
		if(o[1] instanceof Collection)		return fc.t(o);
		if(o[1] instanceof Collection[])	return fcc.t(tab(o));
		if(o[1] instanceof List[])		return fcc.t(tab(o));
		if(o[1] instanceof Set[])		return fcc.t(tab(o));
		
		if(o[1] instanceof E)			return fe.t(o);
		if(o[1] instanceof E[])			return fee.t(tab(o));
		
		if(o[1] instanceof G)			return fg.t(o);
		if(o[1] instanceof G[])			return fgg.t(tab(o));
		
		if(o[1] instanceof H)			return fh.t(o);
		if(o[1] instanceof H[])			return fhh.t(tab(o));
		
		if(o[1] instanceof I)			return fi.t(o);
		if(o[1] instanceof I[])			return fii.t(tab(o));
		
		if(o[1] instanceof Map)			return fmap.t(o);
		if(o[1] instanceof Map[])		return fmapmap.t(o);
		
		if(o[1] instanceof P)			return fp.t(o);
		if(o[1] instanceof P[])			return fpp.t(tab(o));
		
		if(o[1] instanceof R)			return fr.t(o);
		if(o[1] instanceof R[])			return frr.t(tab(o));
		
		if(o[1] instanceof T)			return ft.t(o);
		if(o[1] instanceof T[])			return ftt.t(tab(o));
		
		if(o[1] instanceof Object[])		return foo.t(tab(o));
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
	
	
	
	private Object[] tab(Object[] o) throws Exception
	{
		F f = (F) o[0];
		Object[] k = (Object[]) o[1];
		
		if(k.length==1) return new Object[]{f,k[0],null};
		if(k.length==2) return new Object[]{f,k[0],k[1]};
		
		throw new Exception("Wrong data number: "+k.length);
	}
}
