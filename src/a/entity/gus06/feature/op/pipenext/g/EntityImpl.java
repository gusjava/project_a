package a.entity.gus06.feature.op.pipenext.g;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service ge;
	private Service gf;
	private Service gh;
	private Service gmap;
	private Service gp;
	private Service gr;
	private Service gt;
	
	public EntityImpl() throws Exception
	{
		ge = Outside.service(this,"gus06.feature.wrap.ge.g");
		gf = Outside.service(this,"gus06.feature.wrap.gf.g");
		gh = Outside.service(this,"gus06.feature.wrap.gh.g");
		gmap = Outside.service(this,"gus06.feature.wrap.gmap.g");
		gp = Outside.service(this,"gus06.feature.wrap.gp.e");
		gr = Outside.service(this,"gus06.feature.wrap.gr.g");
		gt = Outside.service(this,"gus06.feature.wrap.gt.g");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		
		if(o[1] instanceof E) return ge.t(o);
		if(o[1] instanceof F) return gf.t(o);
		if(o[1] instanceof H) return gh.t(o);
		if(o[1] instanceof Map) return gmap.t(o);
		if(o[1] instanceof P) return gp.t(o);
		if(o[1] instanceof R) return gr.t(o);
		if(o[1] instanceof T) return gt.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
