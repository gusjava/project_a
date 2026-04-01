package a.entity.gus06.sys.parser3.resolver1.op.seq.or;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}

	
	private Service or_f;
	private Service or_bool;
	
	private Service toArray_f;
	private Service toArray_bool;


	public EntityImpl() throws Exception
	{
		or_f = Outside.service(this,"gus06.feature.op.filter.or");
		or_bool = Outside.service(this,"gus06.math.tabboolean.or");
		
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
			if(ff==null) throw new Exception("Invalid operation: OR");
			return or_f.t(ff);
		}
		
		if(oo[0] instanceof Boolean)
		{
			if(oo[0].equals(Boolean.TRUE)) return Boolean.TRUE;
			for(int i=1;i<cut.size();i++)
			{
				oo[i] = t.t(cut.get(i));
				if(oo[i].equals(Boolean.TRUE)) return Boolean.TRUE;
			}
			
			boolean[] bb = (boolean[]) toArray_bool.t(oo);
			if(bb==null) throw new Exception("Invalid operation: OR");
			return or_bool.t(bb);
		}
		
		throw new Exception("Invalid operation: OR");
	}
}
