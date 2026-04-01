package a.entity.gus06.sys.parser3.resolver1.op.seq.sum;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151028";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.perform");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		int nb = cut.size();
		Object[] oo = new Object[nb];
		for(int i=0;i<nb;i++) oo[i] = t.t(cut.get(i));
		
		return perform.t(oo);
	}
}
