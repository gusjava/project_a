package a.entity.gus06.sys.expression1.apply.op._set2_set3;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161207";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.set.set2toset3");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Set) return new T1((Set) obj);
		if(obj instanceof Set[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Set value;
		public T1(Set value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Set[]{value,(Set) obj});}
	}
}
