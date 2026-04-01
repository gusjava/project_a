package a.entity.gus06.feature.op.transform2.sum;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160111";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List tt = (List) o[0];
		T sumT = (T) o[1];
		
		return new T1(tt,sumT);
	}
	
	
	private class T1 implements T
	{
		private List tt;
		private T sumT;
		
		public T1(List tt, T sumT)
		{
			this.tt = tt;
			this.sumT = sumT;
		}
		
		public Object t(Object obj) throws Exception
		{
			Object[] r = new Object[tt.size()];
			for(int i=0;i<tt.size();i++) r[i] = ((T) tt.get(i)).t(obj);
			return sumT.t(r);
		}
	}
}
