package a.entity.gus06.sys.parser3.resolver1.op.seq.and;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service and_f;
	private Service and_bool;
	
	private Service toArray_f;
	private Service toArray_bool;


	public EntityImpl() throws Exception
	{
		and_f = Outside.service(this,"gus06.feature.op.filter.and");
		and_bool = Outside.service(this,"gus06.math.tabboolean.and");
		
		toArray_f = Outside.service(this,"gus06.convert.objarraytofarray.strict");
		toArray_bool = Outside.service(this,"gus06.convert.objarraytoboolarray.strict");
	}
	
		
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object[] oo = new Object[cut.size()];
		oo[0] = t.t(cut.get(0));
		
		if(oo[0] instanceof F)
		{
			for(int i=1;i<cut.size();i++)
			oo[i] = t.t(cut.get(i));
			
			F[] ff = (F[]) toArray_f.t(oo);
			if(ff==null) throw new Exception("Invalid operation: AND");
			return and_f.t(ff);
		}
		
		if(oo[0] instanceof Boolean)
		{
			if(oo[0].equals(Boolean.FALSE)) return Boolean.FALSE;
			for(int i=1;i<cut.size();i++)
			{
				oo[i] = t.t(cut.get(i));
				if(oo[i].equals(Boolean.FALSE)) return Boolean.FALSE;
			}
			
			boolean[] bb = (boolean[]) toArray_bool.t(oo);
			if(bb==null) throw new Exception("Invalid operation: AND");
			return and_bool.t(bb);
		}
		
		throw new Exception("Invalid operation: AND");
	}
}
