package a.entity.gus06.sys.parser3.resolver1.op.seq.pipe;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.pipe");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object[] oo = new Object[cut.size()];
		for(int i=0;i<cut.size();i++) oo[i] = t.t(cut.get(i));
		
		return perform.t(oo);
	}
}
